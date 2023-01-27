package com.xiamo.baodian.stage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.xiamo.baodian.R
import com.xiamo.baodian.ui.theme.BaodianTheme
import com.xiamo.baodian.utils.GetImageURL
import com.xiamo.baodian.utils.decode


@Composable
fun ImageView(navHostController: NavHostController, point: String?){
    var url by remember {
        mutableStateOf("")
    }
    var text by remember {
        mutableStateOf("加载中")
    }


   Thread() {
       url = if (point == "1") {
           GetImageURL("1")
       } else GetImageURL("2")
       text = ""
   }.start()

         BaodianTheme() {
             Column(
                 modifier = Modifier
                     .fillMaxSize()
                     .background(MaterialTheme.colors.background)
             ) {
                 Row() {
                     IconButton(
                         onClick = { navHostController.popBackStack() }, modifier = Modifier
                             .size(32.dp, 32.dp)
                             .padding(top=15.dp)
                     ) {
                         Icon(
                             painter = painterResource(id = R.drawable.back),
                             tint = MaterialTheme.colors.onPrimary,
                             contentDescription = "返回"
                         )

                     }

                     Text(text = text, color = MaterialTheme.colors.onPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier
                         .padding(start = 50.dp).padding(top=15.dp)
                     )

                 }

                 AsyncImage(model = url, contentDescription = null, modifier =Modifier
                     .fillMaxSize(), placeholder = painterResource(id = R.drawable.loading))
//                 Image(
//                     modifier = Modifier.fillMaxSize(),
//                     painter = rememberImagePainter(
//                         data = url,
//                         builder = {
//                             placeholder(null)
//                             crossfade(true)
//                         }
//                     ),
//                     contentDescription = null,
//                 )
//
//
            }
         }


    
}


