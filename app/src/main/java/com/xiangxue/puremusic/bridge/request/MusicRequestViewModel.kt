package com.xiangxue.puremusic.bridge.request

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xiangxue.puremusic.bridge.data.repository.HttpRequestManager
import com.xiangxue.puremusic.bridge.data.bean.TestAlbum

/**
 * 音乐资源 请求 相关的 ViewModel（只负责 MainFragment）
 */
class MusicRequestViewModel : ViewModel() {

    // by lazy 我想手写出 这个效果
    // val age by lazy { 88 }

    var freeMusicesLiveData : MutableLiveData<TestAlbum> ? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
        private set

    fun requestFreeMusics() {
        HttpRequestManager.instance.getFreeMusic(freeMusicesLiveData)
    }

}