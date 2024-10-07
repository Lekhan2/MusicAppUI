package com.mypro.musicappui.ui

import android.content.ClipData.Item
import android.util.Log
import android.view.View.OnClickListener
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mypro.musicappui.MainViewModel
import com.mypro.musicappui.R
import com.mypro.musicappui.Screen
import com.mypro.musicappui.screenInBottom
import com.mypro.musicappui.screenInDrawer
import com.mypro.musicappui.ui.theme.AccountDialog
import com.mypro.musicappui.ui.theme.AccountView
import com.mypro.musicappui.ui.theme.Browse
import com.mypro.musicappui.ui.theme.Home
import com.mypro.musicappui.ui.theme.Library
import com.mypro.musicappui.ui.theme.Subscription
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView(){

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()

    val viewModel:MainViewModel= viewModel()

    val dialogOpen= remember { mutableStateOf(false) }

    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()

    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier=if(isSheetFullScreen)Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    val currentroute=navBackStackEntry?.destination?.route

    val currentScreen= remember{
        viewModel.currentScreen.value
    }

    val modelSheetState= androidx.compose.material.rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {it != ModalBottomSheetValue.HalfExpanded}
    )
    val roundedCornerRadius=if(isSheetFullScreen) 0.dp else 12.dp


    val title= remember{
        mutableStateOf(currentScreen.title)
    }
    val bottombar: @Composable ()-> Unit={
        if (currentScreen is Screen.DrawerSceen || currentScreen == Screen.ButtomScreen.Home){
            BottomNavigation(Modifier.wrapContentSize()) {
                screenInBottom.forEach{
                        item->
                    val isSelected = currentroute == item.route
                    Log.d("Navigation","${item.btitle}, Current Route: ${currentroute}, Is Selectes")
                    val tint=if(isSelected)Color.White else Color.Black
                    BottomNavigationItem(selected = currentroute==item.bRoute,
                        onClick = { controller.navigate(item.bRoute)}, icon = {

                            Icon(painter = painterResource(id=item.icon),
                                null,
                                tint = tint
                            )
                        },
                        label = { Text(item.btitle, color = tint) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )

                }
            }
        }
    }
    @Composable
    fun moreBottomSheet(modifier: Modifier){
        Box(
            Modifier.fillMaxWidth().height(300.dp).background(MaterialTheme.colors.primarySurface)
        ){
            Column (
                modifier=Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween
            ){
                Row (
                    modifier = Modifier.padding(16.dp)
                ){
                    Icon(modifier = Modifier.padding(end = 8.dp),
                        painter = painterResource(id= R.drawable.baseline_settings_24),
                        contentDescription = "Settings"
                    )
                    Text("Settings", fontSize = 20.sp, color = Color.White)

                }
                Row (
                    modifier = Modifier.padding(16.dp)
                ){
                    Icon(modifier = Modifier.padding(end = 8.dp),
                        painter = painterResource(id= R.drawable.baseline_settings_24),
                        contentDescription = "Share"
                    )
                    Text("Share", fontSize = 20.sp, color = Color.White)

                }
                Row (
                    modifier = Modifier.padding(16.dp)
                ){
                    Icon(modifier = Modifier.padding(end = 8.dp),
                        painter = painterResource(id= R.drawable.baseline_settings_24),
                        contentDescription = "Help"
                    )
                    Text("Help", fontSize = 20.sp, color = Color.White)

                }
            }

        }

    }


    ModalBottomSheetLayout(
        sheetState=modelSheetState,
        sheetShape =RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
        moreBottomSheet(modifier= modifier)

    }) {
        Scaffold(
            bottomBar = bottombar,

            topBar = {
                TopAppBar(title = { Text(currentScreen.title)},
                    actions={
                      IconButton(onClick = {
                          scope.launch {
                              if (modelSheetState.isVisible){
                                  modelSheetState.hide()
                              }
                              else{
                                  modelSheetState.show()
                              }
                          }
                      }) {
                        Icon(imageVector = Icons.Default.MoreVert,"")
                      }
                    },
                    navigationIcon = { IconButton(onClick = {
                        // open the drawer
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
                    } })
            },
            scaffoldState=scaffoldState,
            drawerContent = {
                LazyColumn (Modifier.padding(16.dp)){
                    items(screenInDrawer){
                            item ->
                        DrawerItem(selected = currentroute == item.dRoute, item = item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if (item.dRoute=="addaccount"){
                                dialogOpen.value=true
                            }
                            else{
                                controller.navigate(item.dRoute)
                                title.value=item.dtitle
                            }
                        }
                    }
                }
            }
        ){
            Navigation(navController = controller,viewModel= viewModel,pd=it)
            AccountDialog(dialogOpen=dialogOpen)
        }
    }





}


//@Preview(showBackground = true)
//@Composable
//fun MainViewPreview() {
//    MainView()
//}

@Composable
fun DrawerItem(
    selected:Boolean,
    item: Screen.DrawerSceen,
    onDrawerItemClicked:()->Unit
){
val background = if(selected) Color.DarkGray else Color.White

    Row (
        Modifier.fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp).background(background)
            .clickable {
                onDrawerItemClicked()
            }
    ){
        Icon(
            painter = painterResource(item.icon),
            contentDescription = item.dRoute,
            Modifier.padding(end = 8.dp, top = 4.dp)

        )
        Text(
            text = item.dtitle,
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun Navigation(navController: NavController,viewModel: MainViewModel, pd:PaddingValues){
    NavHost(navController= navController as NavHostController,
        startDestination = Screen.DrawerSceen.Account.route,
        modifier = Modifier.padding(pd)) {

        composable(Screen.ButtomScreen.Home.bRoute){
            Home()
        }
        composable(Screen.ButtomScreen.Browse.bRoute){
            Browse()
        }
        composable(Screen.ButtomScreen.library.bRoute){
            Library()
        }
        composable(Screen.DrawerSceen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerSceen.Subscription.route){
            Subscription()
        }
    }
}
