package com.xiamo.baodian.stage

import android.content.res.Resources
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.xiamo.baodian.R
import com.xiamo.baodian.ui.theme.BaodianTheme
import com.xiamo.baodian.utils.DrawProcessedResult
import com.xiamo.baodian.utils.GetImageURL
import com.xiamo.baodian.utils.encode
import kotlinx.coroutines.launch


class MainScreen(navHostController: NavHostController){
    var pagers = listOf("home","tools")
    var navHostController = navHostController

    @Composable
     fun MainPage() {
        DrawMainScreen()



    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun DrawMainScreen(){

        BaodianTheme() {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
            ) {
                var MiddleAnimation by remember {
                    mutableStateOf(false)
                }
                val anim_middle = AnimatedVisibility(
                    visible = MiddleAnimation,
                    enter = slideInVertically() + fadeIn(initialAlpha = 0.1f)
                ) { Middle()}
                LaunchedEffect(key1 = true ){
                    MiddleAnimation = true
                }

            }
        }
    }


    @Composable
    fun Top(){
        val res = Resources.getSystem()
        val displayMetrics = res.displayMetrics
        val designWidth = px2dip(displayMetrics.widthPixels.toFloat())
        val designHeight= px2dip(displayMetrics.heightPixels.toFloat())
        Row(modifier = Modifier
        ) {
            Column()
            {

                Text(text = "六合彩查询", fontSize = 18.sp, fontWeight = FontWeight.Bold,color = MaterialTheme.colors.onBackground ,modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 10.dp)
                )
                Text(text = "闻道梅花坼晓风，雪堆遍满四山中。", fontSize = 10.sp, color = MaterialTheme.colors.onBackground, modifier = Modifier
                    .padding(top = 3.dp)
                    .padding(horizontal = 10.dp)
                )
                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 1.dp, width = designWidth.dp)
                    .offset(0.dp, 6.dp),

                    elevation = 2.dp,

                    ) {
                    Divider(
                        color = MaterialTheme.colors.onBackground.copy(0.2f),
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .shadow(elevation = 1.dp, shape = RectangleShape)
                    )
                }

            }

        }


    }



    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun Middle(){
        val res = Resources.getSystem()
        val scope = rememberCoroutineScope()
        val displayMetrics = res.displayMetrics
        val designWidth = px2dip(displayMetrics.widthPixels.toFloat())
        val designHeight= px2dip(displayMetrics.heightPixels.toFloat())
        var state = rememberPagerState(initialPage = 0)
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            HorizontalPager(verticalAlignment = Alignment.Top,count = pagers.size, state = state, modifier = Modifier
                .background(MaterialTheme.colors.background)
                .size(designWidth.dp, (designHeight.dp - (designHeight.dp / 5)) - 1.dp)
            ){page ->
                when(page){
                    0 -> Page_home()
                    1 -> Page_tools()
                }


                }
            Divider(modifier = Modifier.size(1.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(modifier = Modifier
                    .size(designWidth.dp / 2,designHeight.dp / 5)
                    , onClick = {
                        scope.launch {
                            state.scrollToPage(page = 0)
                        }





                    }) {
                    Column() {
                        Icon(painter = painterResource(id = R.drawable.home), tint = MaterialTheme.colors.onPrimary, contentDescription = "Home", modifier = Modifier.size(32.dp,32.dp))
                    }

                }
                IconButton(modifier = Modifier
                    .size(designWidth.dp / 2, designHeight.dp / 5)
                    .background(color = MaterialTheme.colors.onSecondary.copy(0f))
                    , onClick = {
                        scope.launch {
                            state.scrollToPage(page = 1)
                        }





                    }) {
                    Column() {
                        Icon(painter = painterResource(id = R.drawable.tools), tint = MaterialTheme.colors.onPrimary, contentDescription = "Tools", modifier =  Modifier.size(32.dp,32.dp))
                    }

                }

            }







        }


    }


    @Composable
    fun Page_home(){
        Column() {
            val res = Resources.getSystem()
            val displayMetrics = res.displayMetrics
            val designWidth = px2dip(displayMetrics.widthPixels.toFloat())
            val designHeight= px2dip(displayMetrics.heightPixels.toFloat())
            var text by remember {
                mutableStateOf("sb")
            }
            Top()
            Spacer(modifier = Modifier.padding(top = 30.dp))
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)) {
                Surface(
                    modifier = Modifier
                        .width((designWidth.dp - 30.dp))
                        .height(200.dp),
                    shape = RoundedCornerShape(25.dp),
                    elevation = 10.dp,
                ) {
                    Column(modifier = Modifier
                        .size(width = (designWidth.dp - 80.dp), height = 200.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colors.surface.copy(0.7f))


                    ) {
                        Row(modifier = Modifier.padding(top = 20.dp) ) {
                            DrawProcessedResult()
                        }



                    }
                }
                4        }
        }



    }
    @Composable
    fun Page_tools(){
        val res = Resources.getSystem()
        val displayMetrics = res.displayMetrics
        val designWidth = px2dip(displayMetrics.widthPixels.toFloat())
        val designHeight= px2dip(displayMetrics.heightPixels.toFloat())
       BaodianTheme() {
           Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(designHeight.dp / 4))
               Text(text = "工具", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground, modifier = Modifier
                   .padding(start = 40.dp)
                   .padding(bottom = 35.dp)

               )

              Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {

                  Button(modifier = Modifier
                      .padding(start = 10.dp)
                      .width(width = (designWidth.dp / 3) + 30.dp)
                      .height(height = designHeight.dp / 15)
                      .clip(RoundedCornerShape(12.dp))
                      ,onClick = {
                          navHostController.navigate("IMAGEVIEW_PAGE/1}")


                      }) {
                      Text(text = "旧版跑狗图", color = MaterialTheme.colors.onSecondary, fontSize = 15.sp, modifier = Modifier



                      )
                  }
                  Button(modifier = Modifier
                      .padding(end = 10.dp)
                      .width(width = (designWidth.dp / 3) + 30.dp)
                      .height(height = designHeight.dp / 15)
                      .clip(RoundedCornerShape(12.dp))
                      ,onClick = {
                          navHostController.navigate("IMAGEVIEW_PAGE/2}")


                      }) {
                      Text(text = "新版跑狗图", color = MaterialTheme.colors.onSecondary, fontSize = 15.sp, modifier = Modifier


                      )
                  }
              }




           }

       }
    }
    @Composable
    fun Page_end(){
        Button(onClick = { /*TODO*/ }) {
            Text(text = "sbee")
        }
    }

    @Composable
    fun MainPagePreviewDark(){
        DrawMainScreen()
    }

    @Composable
    fun MainPagePreviewLight() {
        DrawMainScreen()
    }


    fun px2dip(pxValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


}



