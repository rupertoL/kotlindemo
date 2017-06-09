package com.project.lp.tabviewpager.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.project.lp.tabviewpager.R;

import java.util.List;

/**
 * fragment页面的基类
 */
// (2)继承FrameLayout原因：一，是view对象可返回 二，可添加四种状态作为子布局
// 三，代码量少 轻量级
public abstract class ContentPage extends FrameLayout {
	private View loadingView;// [1]加载中的View
	private View errorView;// [2]加载失败的View
	private View emptyView;// [3]加载数据为空的View
	private View sucessView;// [4]加载成功的View
	// 是否从网络加载过一次数据（用于延缓Viewpager预加载）
	private  Context context ;
	public boolean mHasLoadedOnce = false;
	// 默认的初始化状态,默认是加载中的状态
	public PageState mState = PageState.STATE_LOADING;

	public PageState getmState() {
		return mState;
	}

	public void setmState(PageState mState) {
		this.mState = mState;
	}

	// (1)定义页面状态常量类(枚举)
	public enum PageState {
		STATE_LOADING(0), // [0]加载中
		STATE_SUCCESS(1), // [1]加载成功
		STATE_ERROR(2), // [2]加载失败
		STATE_EMPTY(3);// [3]加载数据为空

		private int value;

		PageState(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public ContentPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context= context;
		initContentPage();
	}

	public ContentPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context= context;
		initContentPage();
	}

	public ContentPage(Context context) {

		super(context);
		this.context= context;
		initContentPage();
	}

	private void initContentPage() {
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		// [1]添加加载中的view
		if (loadingView == null) {
			loadingView = View.inflate(getContext(), R.layout.page_loading,
					null);
		}
		addView(loadingView, layoutParams);
		// [2]添加加载失败的view
		if (errorView == null) {
			errorView = View.inflate(getContext(), R.layout.page_error, null);
			Button btnReload = (Button) errorView
					.findViewById(R.id.btn_error_reload);
			btnReload.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// 1.先显示加载界面
					mState = PageState.STATE_LOADING;
					showPage();
					// 2.然后去加载数据，并更新UI
					loadDataAndRefreshView();
				}
			});
		}
		addView(errorView, layoutParams);
		// [3]添加加载数据为空的view
		if (emptyView == null) {
			emptyView = View.inflate(getContext(), R.layout.page_empty, null);

		}
		addView(emptyView, layoutParams);
		// [4]添加加载成功的view
		if (sucessView == null) {
			sucessView = createSucessView();
		}
		if (sucessView != null) {
			addView(sucessView, layoutParams);
		} else {
			throw new IllegalArgumentException(
					"The method createSuccessView() can not return null!！");
		}
		// (4)根据当前的mState显示一下View
		showPage();// 显示加载中的页面
		// (5)紧接着加载数据，然后刷新View
		// loadDataAndRefreshView();
	}

	// (4)根据mState显示不同的view
	public void showPage() {
		loadingView
				.setVisibility(mState == PageState.STATE_LOADING ? View.VISIBLE
						: View.INVISIBLE);
		errorView.setVisibility(mState == PageState.STATE_ERROR ? View.VISIBLE
				: View.INVISIBLE);
		emptyView.setVisibility(mState == PageState.STATE_EMPTY ? View.VISIBLE
				: View.INVISIBLE);
		sucessView
				.setVisibility(mState == PageState.STATE_SUCCESS ? View.VISIBLE
						: View.INVISIBLE);
	}

	// (5)加载数据然后刷新View
	public void loadDataAndRefreshView() {
		// 注意:请求数据的操作放在子线程
		new Thread() {
			public void run() {
				super.run();
				// 注意:此三个步骤必须放在一块，原因：
				// 1.获取加载完成之后的数据
				Object result = loadData();
				// 2.根据数据判断更新对应的状态
				mState = checkData(result);
				// 3.根据最新状态更新View
				if(null != context){
				((Activity)context).runOnUiThread(new Runnable() {

					@Override
					public void run() {
						showPage();
					}
				});
			}}
		}.start();
	}

	// (5)根据对应的数据判断对应的状态
	@SuppressWarnings("rawtypes")
	protected PageState checkData(Object result) {
		// 加载数据失败(断网或服务器原因)
		if (result instanceof List) {
			List list = (List) result;
			if (list.size() == 0) {// 返回数据为空
				return PageState.STATE_EMPTY;
			} else {
				return PageState.STATE_SUCCESS;
			}
		} else {
			if (TextUtils.isEmpty((String) result)) {
				return PageState.STATE_ERROR;
			} else {
				return PageState.STATE_SUCCESS;
			}
		}

	}

	// (3)由于 每个fragment的成功View都不一样，那么这个View应该由每个fragment自己提供
	public abstract View createSucessView();

	// (5)我不关心每个fragment加载数据的过程，每个fragment只需要给我返回它加载完成之后的数据就行了,我只须根据结果做出判断，进而做出相应操作
	public abstract Object loadData();

}
