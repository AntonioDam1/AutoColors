package com.example.autocolorsprueba

import HttpClient
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class HttpClientTest : HttpClient.HttpClientListener {

    private var cochesReceived: List<HttpClient.Coches>? = null

    /**
     * Test para probar la conexión entre la app y el servidor. Falla porque la clase AsyncTask no está diseñada para JUnit, habría que crear una interfaz para cambiar su comportamiento
     * interface AsyncTaskExecutor {
     *     fun <Params, Progress, Result> execute(task: AsyncTask<Params, Progress, Result>, vararg params: Params)
     * }
     *
     * class RealAsyncTaskExecutor : AsyncTaskExecutor {
     *     override fun <Params, Progress, Result> execute(task: AsyncTask<Params, Progress, Result>, vararg params: Params) {
     *         task.execute(*params)
     *     }
     * }
     *
     * class TestAsyncTaskExecutor : AsyncTaskExecutor {
     *     override fun <Params, Progress, Result> execute(task: AsyncTask<Params, Progress, Result>, vararg params: Params) {
     *         // Ejecuta la tarea de forma sincrónica en el hilo actual para las pruebas
     *         task.execute(*params).get()
     *     }
     * }
     *
     * Nos daba este error:
     *
     *Method execute in android.os.AsyncTask not mocked. See https://developer.android.com/r/studio-ui/build/not-mocked for details.
     * java.lang.RuntimeException: Method execute in android.os.AsyncTask not mocked. See https://developer.android.com/r/studio-ui/build/not-mocked for details.
     * 	at android.os.AsyncTask.execute(AsyncTask.java)
     * 	at HttpClient.executeGetRequest(HttpClient.kt:48)
     * 	at com.example.autocolorsprueba.HttpClientTest.testExecuteGetRequest(HttpClientTest.kt:21)
     * 	...
     */
    @Test
    fun testExecuteGetRequest() {
        // URL de prueba y parámetros
        val serverUrl = "https://ccd3-176-12-82-226.ngrok-free.app/endpoint"
        val params = mapOf("color" to "f44336", "match" to "97")

        // Crea un objeto HttpClient y configura esta clase como el listener
        val httpClient = HttpClient(this)

        // Ejecuta la solicitud GET
        httpClient.executeGetRequest(serverUrl, params)

        // Espera un tiempo razonable para que la solicitud se complete (ajusta según sea necesario)
        Thread.sleep(5000)

        // Verifica que se hayan recibido los coches
        assertThat(cochesReceived).isNotNull()
        assertThat(cochesReceived).isNotEmpty()
        // Aquí puedes agregar más aserciones según la respuesta esperada del servidor
    }

    /**
     * Prueba para ver si se ha recibido algo
     */
    override fun onCochesReceived(cochesList: List<HttpClient.Coches>) {
        // Almacena la lista de coches recibida para su verificación en el test
        cochesReceived = cochesList
    }
}
