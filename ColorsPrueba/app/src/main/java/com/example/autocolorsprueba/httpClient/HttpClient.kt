import android.os.AsyncTask
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.security.Key


/**
 * Esta clase representa un cliente HTTP que realiza solicitudes al servidor y maneja las respuestas.
 * Utiliza un HttpClientListener para notificar cuando se reciben los datos del servidor.
 *
 * @param listener El objeto que escucha las respuestas del servidor.
 */
class HttpClient(private val listener: HttpClientListener) {


    /**
     * Ejecuta una solicitud GET al servidor con los parámetros proporcionados y espera la respuesta.
     * @param serverUrl La URL del servidor al que se enviará la solicitud.
     * @param params Los parámetros de la solicitud, representados como un mapa de clave-valor.
     * @throws MalformedURLException Si la URL proporcionada no es válida.
     * @throws UnsupportedEncodingException Si ocurre un error al codificar los parámetros de la solicitud.
     */

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
        // Cifra los parámetros codificados antes de agregarlos a la URL
        val paramsCypherString : String = CypherManagerAES.cifrar(encodedParams.toString(),CypherManagerAES.obtenerClave("unodostrecuacin1",16))
        val url = URL("$serverUrl?$paramsCypherString")
        HttpGetTask().execute(url)
    }


    /**
     * Clase interna que realiza una solicitud GET a una URL proporcionada en segundo plano
     * y convierte la respuesta del servidor en una lista de objetos Coches.
     */
    private inner class HttpGetTask : AsyncTask<URL, Void, String>() {


        /**
         * Realiza la tarea en segundo plano para realizar una solicitud GET a la URL proporcionada.
         * @param urls Las URL a las que se va a realizar la solicitud GET.
         * @return Una cadena que representa la respuesta del servidor.
         */
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


        /**
         * Método llamado después de que se complete la tarea en segundo plano.
         * @param result El resultado de la tarea en segundo plano, es una cadena JSON
         * que representa la lista de coches.
         */
        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            // Convierte la cadena JSON en una lista de objetos Coches utilizando la biblioteca Gson.
            val cochesList = Gson().fromJson<List<Coches>>(result, object : TypeToken<List<Coches>>() {}.type)
            // Notifica al listener que se han recibido los coches.
            listener.onCochesReceived(cochesList)
        }
    }



    /**
     * Interfaz que define un listener para recibir la lista de coches desde el cliente HTTP.
     */
    interface HttpClientListener {

        /**
         * Método llamado cuando se reciben los coches del cliente HTTP.
         * @param cochesList La lista de coches recibida desde el cliente HTTP.
         */
        fun onCochesReceived(cochesList: List<Coches>)
    }

    /**
     * Clase que representa un objeto Coches que contiene información sobre un coche para pasarselo a los métodos.
     * @property id El identificador único del coche.
     * @property year El año del coche.
     * @property maker El fabricante del coche.
     * @property model El modelo del coche.
     * @property paintColorName El nombre del color de pintura del coche.
     * @property code El código del color de pintura del coche.
     * @property catalogueURL La URL del catálogo del coche.
     * @property hexadecimal El valor hexadecimal del color de pintura del coche.
     * @property red El componente rojo del color de pintura del coche.
     * @property green El componente verde del color de pintura del coche.
     * @property blue El componente azul del color de pintura del coche.
     * @property colorSampleURL La URL de la muestra de color del coche.
     * @property matchPercentage El porcentaje de coincidencia del color de pintura del coche, puede ser nulo.
     */

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

    /**
     * Objeto que maneja el cifrado y descifrado utilizando el algoritmo AES.
     */
    object CypherManagerAES {

        /**
         * Obtiene una clave secreta para el cifrado y descifrado AES.
         *
         * @param password La contraseña utilizada para generar la clave.
         * @param longitud La longitud de la clave en bytes (16, 24 o 32).
         * @return Una instancia de la clase Key que representa la clave secreta.
         */
        fun obtenerClave(password: String, longitud: Int): Key {
            // La longitud puede ser de 16, 24 o 32 bytes.
            return SecretKeySpec(password.toByteArray(), 0, longitud, "AES")
        }

        /**
         * Cifra un texto utilizando el algoritmo AES.
         *
         * @param textoEnClaro El texto que se desea cifrar.
         * @param key La clave secreta utilizada para el cifrado.
         * @return Una cadena de texto cifrada.
         */
        @Throws(Exception::class)
        fun cifrar(textoEnClaro: String, key: Key): String {
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val cipherText = cipher.doFinal(textoEnClaro.toByteArray())
            return Base64.getEncoder().encodeToString(cipherText)
        }

        /**
         * Descifra un texto cifrado utilizando el algoritmo AES.
         *
         * @param textoCifrado El texto cifrado que se desea descifrar.
         * @param key La clave secreta utilizada para el descifrado.
         * @return El texto descifrado.
         */
        @Throws(Exception::class)
        fun descifrar(textoCifrado: String, key: Key): String {
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, key)
            val plainText = cipher.doFinal(Base64.getDecoder().decode(textoCifrado))
            return String(plainText)
        }
    }
}
