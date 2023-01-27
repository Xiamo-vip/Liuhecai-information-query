package com.xiamo.baodian.function

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

object Api_Get_Result {
    var data : String = ""
    var stats : Boolean = false
    private fun build(){
        Thread{
            val client : OkHttpClient = OkHttpClient().newBuilder().build();
            val request: okhttp3.Request = okhttp3.Request.Builder()
                .url("http://sz1open.oss-cn-shenzhen.aliyuncs.com/lhc_result.js")
                .method("GET", null)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    data = ""
                    stats = true
                    println("[Api_Get_Result.kt] data get failure] $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    var stA = response.body?.string().toString()
                    data = stA
                    stats = true
                    println("[Api_Get_Result.kt] data get success $data")

                }
            })

        }.start()

    }

    fun get() : String{
        build()
        while(true){
            if (stats.toString() == "true") { //别问我我什么这样写，我也很奇怪，直接if(stats)会出BUG
                return data
                break
            }
        }

    }
}