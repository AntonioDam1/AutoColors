package com.example.autocolorsprueba.httpClient

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class HttpClient(private val listener: HttpClientListener) {

    fun executeGetRequest(serverUrl: String, params: Map<String, String>) {
        val encodedParams = StringBuilder()
        params.forEach { (key, value) ->
            encodedParams.append(URLEncoder.encode(key, "UTF-8"))
            encodedParams.append("=")
            encodedParams.append(URLEncoder.encode(value, "UTF-8"))
            encodedParams.append("&")
        }
        if (encodedParams.isNotEmpty()) {
            encodedParams.deleteCharAt(encodedParams.length - 1)
        }

        val url = URL("$serverUrl?$encodedParams")
        HttpGetTask(listener).execute(url)
    }

    private class HttpGetTask(private val listener: HttpClientListener) :
        AsyncTask<URL, Void, String>() {

        override fun doInBackground(vararg urls: URL): String {
            val url = urls[0]
            val connection = url.openConnection() as HttpURLConnection
            try {
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000 // 5 seconds
                connection.readTimeout = 5000 // 5 seconds

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line).append('\n')
                    }
                    reader.close()
                    return response.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
            return ""
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            listener.onResponseReceived(result)
        }
    }

    interface HttpClientListener {
        fun onResponseReceived(response: String)
    }
}
