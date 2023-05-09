package com.xiangxue.puremusic.bridge.data.repository.net

import android.content.Context
import com.xiangxue.puremusic.bridge.data.login_register.LoginRegisterResponseWrapper
import com.xiangxue.puremusic.ui.view.LoadingDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

// RxJava 自定义 操作符 简单的
// 拦截 自定义操作符，目的: 包装Bean 给拆成两份  如果成功  data--》UI     如果失败  meg--》UI
abstract class APIResponse<T>(val context: Context)  // 主
    // LoginRegisterResponseWrapper<T> == 图 包装Bean
    : Observer<LoginRegisterResponseWrapper<T>> {

    private var isShow: Boolean = true

    // 次构造
    constructor(context: Context, isShow: Boolean = false) : this(context) {
        this.isShow = isShow
    }

    // 成功 怎么办
    abstract fun success(data: T ?)

    // 失败 怎么办
    abstract fun failure(errorMsg: String ? )


    // todo +++++++++++++++++++++++++++++++++  RxJava 相关的函数

    // 启点分发的时候
    override fun onSubscribe(d: Disposable) {
        // 弹出 加载框
        if (isShow) {
            LoadingDialog.show(context)
        }
    }

    // 上游流下了的数据   我当前层 获取到了 上一层 流下来的 包装Bean == t: LoginRegisterResponseWrapper<T>
    override fun onNext(t: LoginRegisterResponseWrapper<T>) {

        if (t.data == null) {
            // 失败
            failure("登录失败了，请检查原因：msg:${t.errorMsg}")
        } else {
            // 成功
            success(t.data)
        }
    }

    // 上游流下了的错误
    override fun onError(e: Throwable) {
        // 取消加载
        LoadingDialog.cancel()

        failure(e.message)
    }

    // 停止
    override fun onComplete() {
        // 取消加载
        LoadingDialog.cancel()
    }
}