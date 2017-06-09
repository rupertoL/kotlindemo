package com.project.lp.tabviewpager.ViewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	protected ContentPage contentPage;

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (contentPage == null) {
			contentPage = new ContentPage(getActivity()) {

				@Override
				public View createSucessView() {

					return getSucessView();
				}

				@Override
				public Object loadData() {
					return BaseFragment.this.loadData();
				}
			};
		} else {
			CommonUtil.removeSelfFromParent(contentPage);
		}

		if (getUserVisibleHint()) {

			setUserVisibleHint(getUserVisibleHint());
		}

		return contentPage;
	}

	protected abstract Object loadData();

	// (3)Fragment可见时,才加载网络数据（用于延缓Viewpager预加载）
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		if (contentPage != null) {
			if (isVisibleToUser && !contentPage.mHasLoadedOnce) {
				contentPage.mHasLoadedOnce = true;
				// 加载网络数据
				contentPage.loadDataAndRefreshView();
			}
		}
		super.setUserVisibleHint(isVisibleToUser);
	}

	// (2)子类去实现获取加载成功的View
	protected abstract View getSucessView();

	/**
	 * 运行在UI线程中
	 * 
	 * @param runnable
	 */
	protected void runOnUIThread(Runnable runnable) {
		getActivity().runOnUiThread(runnable);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
