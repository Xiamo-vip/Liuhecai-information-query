package com.xiamo.baodian.function

import okhttp3.*
import java.io.IOException

class Api_Get_Image(point: Int) {
    var id : Int = point
    var data: String = ""
    var state: Boolean = false
    private fun build() {
        if(id == 1){
            Thread {
                val client: OkHttpClient = OkHttpClient.Builder().build()
                val request: okhttp3.Request = okhttp3.Request.Builder()
                    .url("https://tzjk.6hkcapi.com/paper/info?paperid=431&period=0")
                    .method("GET", null)
                    .addHeader(
                        "User-Agent",
                        "Mozilla/5.0 (ckbd/2.0.0) (Android; Android OS 10;zh) Mobile"
                    )
                    .addHeader("host_sign_code", "e721422b8ecdba33dbe0f318926baa03")
                    .addHeader("Host", "tzjk.6hkcapi.com")
                    .addHeader("Connection", "Keep-Alive")
                    .addHeader("Accept-Encoding", "gzip")
                    .build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("[Api_Get_Image.kt] data get failure] $e")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        data = response.body?.string().toString()
                        println("[Api_Get_Image.kt] data get success $id$data")

                        state = true

                    }


                })
            }.start()
        }if(id == 2){
             else{
            Thread {
                val client: OkHttpClient = OkHttpClient.Builder().build()
                val request: okhttp3.Request = okhttp3.Request.Builder()
                    .url("https://tzjk.6hkcapi.com/paper/info?paperid=386&period=0")
                    .method("GET", null)
                    .addHeader(
                        "User-Agent",
                        "Mozilla/5.0 (ckbd/2.0.0) (Android; Android OS 10;zh) Mobile"
                    )
                    .addHeader("host_sign_code", "e721422b8ecdba33dbe0f318926baa03")
                    .addHeader("Host", "tzjk.6hkcapi.com")
                    .addHeader("Connection", "Keep-Alive")
                    .addHeader("Accept-Encoding", "gzip")
                    .build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("[Api_Get_Image.kt] data get failure] $e")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        data = response.body?.string().toString()
                        println("[Api_Get_Image.kt] data get success $id$data")

                        state = true

                    }


                })
            }.start()
        }
        
        
        }

    }


    fun get(): String {
        build()
        while (true) {
            if (state.toString() == "true") { //别问我我什么这样写，我也很奇怪，直接if(stats)会出BUG
                return data
                break
            }
        }


    }

}
