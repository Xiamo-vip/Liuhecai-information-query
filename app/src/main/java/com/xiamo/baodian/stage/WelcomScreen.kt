package com.xiamo.baodian.stage

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.xiamo.baodian.Greeting
import com.xiamo.baodian.ui.theme.BaodianTheme
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.schedule



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    BaodianTheme() {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)) {
            var startAnimation by remember {
                mutableStateOf(false)
            }
            val anim_enter = AnimatedVisibility(
                visible = startAnimation,
                enter = slideInVertically() + fadeIn(initialAlpha = 0.1f),
                exit = scaleOut() + fadeOut(targetAlpha = 0.1f)
            ) { Screen()}

            LaunchedEffect(key1 = true ){
                startAnimation = true
                delay(1000)
                startAnimation = false
                delay(100)
                navHostController.popBackStack()
                navHostController.navigate("MAIN_PAGE")
            }

        }
    }



}

@Composable
fun Screen(){
    BaodianTheme(){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onSurface),
            contentAlignment = Alignment.Center
        ) {

            DrawLogo()
        }
    }


}

@Composable
fun DrawLogo(){


        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "W e l c o m e",
                    fontSize = 35.sp,
                    color = MaterialTheme.colors.onSecondary,
                );
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Thank for your use",
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .padding(top = 5.dp)


                );
            }
        }




}
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WelcomeScreenDarkPreview(){
    Screen()
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun WelcomeScreenLightPreview(){
    Screen()
}