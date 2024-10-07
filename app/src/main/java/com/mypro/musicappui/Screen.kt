package com.mypro.musicappui

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {

    sealed class ButtomScreen(val btitle: String,val bRoute: String, @DrawableRes val icon: Int)
        :Screen(btitle,bRoute){
        object Home : ButtomScreen(
            "Home",
            "home",
            R.drawable.baseline_music_video_24
        )
        object library:ButtomScreen(
            "Library",
            "library",
            R.drawable.baseline_video_library_24
        )
        object Browse:ButtomScreen(
            "Browse",
            "browse",
            R.drawable.baseline_apps_24
        )

    }
    sealed class DrawerSceen(val dtitle:String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dtitle,dRoute){
            object Account: DrawerSceen(
                "Account",
                "account",
                R.drawable.ic_account
            )
            object Subscription: DrawerSceen(
                "Subscription",
                "subscription",
                R.drawable.ic_subscription
            )
            object AddAcount: DrawerSceen(
                "Addaccount",
                "addacount",
                R.drawable.ic_addacount
            )

    }
}
val screenInBottom= listOf(
    Screen.ButtomScreen.Home,
    Screen.ButtomScreen.Browse,
    Screen.ButtomScreen.library
)
val screenInDrawer= listOf(
    Screen.DrawerSceen.Account,
    Screen.DrawerSceen.Subscription,
    Screen.DrawerSceen.AddAcount

)