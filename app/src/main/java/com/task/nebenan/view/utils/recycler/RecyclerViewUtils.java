package com.task.nebenan.view.utils.recycler;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.task.nebenan.view.utils.AppLog;


class RecyclerViewUtils {

    private static final String TAG = "RecyclerViewUtils";

    public static void setVerticalLinearLayout(RecyclerView rv) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(rv.getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                 AppLog.INSTANCE.e(TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        };
        rv.setLayoutManager(layoutManager);
    }

    public static void setGridLayout(RecyclerView rv, int spanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rv.getContext(), spanCount, GridLayoutManager.VERTICAL, false) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                 AppLog.INSTANCE.e(TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        };
        rv.setLayoutManager(gridLayoutManager);
    }

    public static void setStaggeredGridLayoutManager(RecyclerView rv, int spanCount) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                 AppLog.INSTANCE.e(RecyclerViewUtils.TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        };
        rv.setLayoutManager(staggeredGridLayoutManager);
    }
}
