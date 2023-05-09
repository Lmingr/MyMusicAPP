package com.xiangxue.puremusic.bridge.data.binding

import androidx.databinding.BindingAdapter
import com.xiangxue.puremusic.ui.view.PlayPauseView
import net.steamcrafted.materialiconlib.MaterialDrawableBuilder.IconValue
import net.steamcrafted.materialiconlib.MaterialIconView

/**
 * TODO 同学们一定要看哦，才能知道为什么，那么多同学一直编译不通过，各种错误，真正的原因是在这里哦，这里和布局建立绑定的呢
 * 注意：这个类的使用，居然是和fragment_player.xml里面的 setIcon / isPlaying 挂钩
 */
object IconBindingAdapter {

    // 调用 播放 暂停 功能的
    @JvmStatic
    @BindingAdapter(value = ["isPlaying"], requireAll = false)
    fun isPlaying(pauseView: PlayPauseView, isPlaying: Boolean) {
        if (isPlaying) {
            pauseView.play()
        } else {
            pauseView.pause()
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["mdIcon"], requireAll = false)
    fun setIcon(view: MaterialIconView, iconValue: IconValue?) {
        view.setIcon(iconValue)
    }
}