package com.xiangxue.puremusic.bridge.data.login_register

// 保存登录信息的临时会话
data class Session constructor(val isLogin: Boolean, val loginRegisterResponse: LoginRegisterResponse?)