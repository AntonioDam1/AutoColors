import android.os.AsyncTask
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        HttpGetTask().execute(url)
    }


    private inner class HttpGetTask : AsyncTask<URL, Void, String>() {


        override fun doInBackground(vararg urls: URL): String {
            val url = urls[0]
            val connection = url.openConnection() as HttpURLConnection
            val response = StringBuilder()
            try {
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000 // 5 seconds
                connection.readTimeout = 5000 // 5 seconds


                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line).append('\n')
                    }
                    reader.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
            return response.toString()
        }


        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            val cochesList = Gson().fromJson<List<Coches>>(result, object : TypeToken<List<Coches>>() {}.type)
            listener.onCochesReceived(cochesList)
//            super.onPostExecute(result)
//            listener.onCochesReceived(result)
        }
    }


    interface HttpClientListener {
        fun onCochesReceived(cochesList: List<Coches>)
    }


    data class Coches(
        val id: Long,
        val year: Int,
        val maker: String,
        val model: String,
        val paintColorName: String,
        val code: String,
        val catalogueURL: String,
        val hexadecimal: String,
        val red: Int,
        val green: Int,
        val blue: Int,
        val colorSampleURL: String,
        val matchPercentage: Double?
    )
}
