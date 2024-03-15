package com.example.autocolorsprueba

import HttpClient
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner

/**
 * Clase para probar HttpClient, se prueba si el servidor envía datos
 */
@RunWith(RobolectricTestRunner::class)
//@RunWith(MockitoJUnitRunner::class)
class HttpClientTest : HttpClient.HttpClientListener{
    @RelaxedMockK
    private var cochesReceived: List<HttpClient.Coches>? = null
//    @Mock
//    lateinit var httpClientListener: HttpClient.HttpClientListener
    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    /**
     * Se comprueba la conexión con el servidor y que devuelva datos
     */
    @Test
    fun testExecuteGetRequest()  = runBlocking{
        // URL de prueba y parámetros
        val serverUrl = "https://ccd3-176-12-82-226.ngrok-free.app/endpoint"
        val params = mapOf("color" to "f44336", "match" to "97")

        val httpClient = HttpClient(this@HttpClientTest)

        httpClient.executeGetRequest(serverUrl, params)
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
        TODO("Not yet implemented")
        cochesReceived = cochesList

    }
}
