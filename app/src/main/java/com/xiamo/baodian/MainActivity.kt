package com.xiamo.baodian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.xiamo.baodian.stage.ImageView
import com.xiamo.baodian.stage.WelcomeScreen
import com.xiamo.baodian.stage.MainScreen


import com.xiamo.baodian.ui.theme.BaodianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavContent();
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaodianTheme {
        Greeting("Android")
    }
}

@Composable
fun NavContent() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = "WELCOME_PAGE") {
        composable("WELCOME_PAGE") { WelcomeScreen(navHostController= navHostController) }
        composable("MAIN_PAGE"){ MainScreen(navHostController = navHostController).MainPage() }
        composable("IMAGEVIEW_PAGE/{point}", arguments = listOf(navArgument("point"){})){
            ImageView(navHostController = navHostController,it.arguments?.getString("point"))


        }

    }




}