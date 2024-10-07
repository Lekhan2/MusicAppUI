package com.mypro.musicappui.ui.theme

import androidx.annotation.DrawableRes
import com.mypro.musicappui.R

data class Lib(@DrawableRes val icon: Int, val name:String)

val libraries= listOf<Lib>(
    Lib(R.drawable.baseline_playlist_play_24,"Playlist"),
    Lib(R.drawable.baseline_mic_none_24,"Artists"),
    Lib(R.drawable.baseline_disc_full_24,"Album"),
    Lib(R.drawable.baseline_music_video_24,"Songs"),
    Lib(R.drawable.baseline_settings_24,"Gene")

)
