package com.xiamo.baodian.utils

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.xiamo.baodian.function.Api_Get_Image
import com.xiamo.baodian.function.Api_Get_Result

fun GetImageURL(point : String) : String {
    var data : String
    if (point == "2"){
       data = Api_Get_Image(2).get()


    }else data = Api_Get_Image(1).get()

    var parser = Parser.default()
    println("sb$data")
    var stringparse = parser.parse(StringBuilder(data)) as JsonObject
    val jsonobj1 = stringparse.obj("Obj") as JsonObject
    val jsonobj2 = jsonobj1.obj("PaperPeriod") as JsonObject
    val jsonarry = jsonobj2.array<String>("ImgUrlList") as JsonArray
    return jsonarry[0]

}