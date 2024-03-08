import android.content.Context
import android.os.AsyncTask
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class HttpClient(private val serverUrl: String, private val params: Map<String, String>, private val context : Context) {

    fun executeGetRequest() {
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
            onResponseReceived(result)
        }
    }

    private fun onResponseReceived(response: String) {

        // Parse the JSON string into a list of Coches objects using Gson

        val gson = Gson()

        val cochesList = gson.fromJson(response, Array<ColorCoche>::class.java).toList()


        // Insert the Coches objects into a Room database

        val database = CochesRoomDatabase.getInstance(context)

        GlobalScope.launch {

            database.colorCocheDao().insertAll(*cochesList.toTypedArray())

        }

    }
}

