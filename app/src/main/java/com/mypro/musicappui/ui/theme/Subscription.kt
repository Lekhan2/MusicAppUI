package com.mypro.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Subscription(){
    Column(
        modifier = Modifier.height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
       Text("Manage Subscription")

        Card (
            modifier = Modifier.padding(8.dp),
            elevation = 4.dp

        ){
            Column (
                modifier = Modifier.padding(8.dp)
            ){
               Column {
                   Text("Musical")
                   Row (
                       modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                   ){
                       Text("Free tire")
                       TextButton(onClick = {
                           //Toodoo
                       }) {
                           Row{
                               Text("See all plans")
                               Icon(Icons.Default.KeyboardArrowRight,
                                   null)
                           }
                       }
                   }

               }
                Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

                Row (
                    modifier = Modifier.padding(vertical = 16.dp)
                ){
                    Icon(Icons.Default.AccountBox,"",Modifier.padding(end = 4.dp))
                    Text("get a plan")
                }

            }

        }
    }
}
