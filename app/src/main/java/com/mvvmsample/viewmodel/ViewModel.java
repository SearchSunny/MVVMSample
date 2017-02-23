package com.mvvmsample.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.mvvmsample.R;
import com.mvvmsample.databinding.DialogProgressBinding;
import com.mvvmsample.model.MainModelBean;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 页面上所显示内容的数据抽象
 */

public abstract class ViewModel {

    private ProgressDialog mProgressDialog;// 加载进度 对话框

    private Context mContext;
    public ViewModel(Context context) {
        mContext = context;
    }

    /**
     * 显示等待框
     *
     * @param message
     * @param isCancel
     */
    public void showProgress(String message, boolean isCancel) {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                return;
            }
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.show();
            DialogProgressBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_progress, null, false);
            mProgressDialog.setContentView(binding.getRoot());
            mProgressDialog.setCancelable(isCancel);
            if (TextUtils.isEmpty(message)) {
                binding.setIsShow(View.GONE);
            } else {
                binding.setIsShow(View.VISIBLE);
                binding.setMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消等待框
     */
    public void dismissProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            try {
                mProgressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public abstract Object showData();
}
