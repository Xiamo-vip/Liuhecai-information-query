package com.xiamo.baodian.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.xiamo.baodian.function.Api_Get_Result

@Composable

fun DrawProcessedResult(){
    var data_json : String = Api_Get_Result.get()
    val parser = Parser.default()
    var stringParser = parser.parse(StringBuilder(data_json)) as JsonObject
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        var id : String = stringParser.int("id").toString()
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.size(15.dp))
            Column() {
                Text(text = "香港六合彩", color = MaterialTheme.colors.onSecondary, fontSize = 20.sp)
                Text(text = "第"+id +"期最新开奖结果", color = MaterialTheme.colors.background, fontSize = 15.sp)
            }

        }
        var data_ma : JsonArray<Any>? = stringParser.array<Any>("ma")
        if (data_ma != null) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {


                for(i in 0 until 21){
                    var stringid : String = data_ma[i].toString()
                    println(stringid)
                    if (stringid.matches("-?\\d+(\\.\\d+)?".toRegex())){
                        var color : Color = Color.Black
                        when(data_ma[i+2].toString()){
                            "red" -> color= Color.Red
                            "green" -> color = Color.Green
                            "blue" -> color = Color(135,206,235)
                        }
                        Spacer(modifier = Modifier.size(5.dp,5.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(contentAlignment = Alignment.Center,modifier = Modifier
                                .size(32.dp, 32.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(color = color)
                            ) {
                                Text(text = data_ma[i].toString(), fontWeight = FontWeight.Bold)
                            }
                            Text(text = data_ma[i+1].toString(), fontWeight = FontWeight.Bold, color=MaterialTheme.colors.onSecondary, modifier = Modifier
                                .padding(top = 2.dp)

                            )
                        }
                        Spacer(modifier = Modifier.size(5.dp,5.dp))
                    }

                }

                }
            }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            var year : String= stringParser.int("year").toString()+"年"
            var day: String = stringParser.string("day").toString()
            var time: String = stringParser.string("time").toString()
            var week : String= stringParser.string("week").toString()
            Text(text = "下期开奖时间：" + year + day + time +" "+ week, color = MaterialTheme.colors.onSecondary , fontSize = 10.sp,modifier = Modifier
                .padding(7.dp)


            )

        }



    }





}