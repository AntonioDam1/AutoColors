import com.example.autocolorsprueba.Filtrar
import io.mockk.impl.annotations.RelaxedMockK
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import java.util.HashMap

/**
 * Test para probar la clase de Filtrar, he puesto que corre con RobolectricTestRunner porque sino el Looper da error
 *
 */
@RunWith(RobolectricTestRunner::class)
class FiltrarTest {
    @RelaxedMockK
    lateinit var filtrar: Filtrar

    /**
     * Se supone que esto es para inicializar la clase
     */
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        filtrar = Filtrar()

    }

    /**
     * Validación de colores hexadecimales
     */
    @Test
    fun testValidColorHex() {
        var validHexColor = "f44336"
        val invalidHexColor = "f44336"
        val result = filtrar.isValidColorHex(validHexColor)

        assertThat(result).isTrue()
    }

    /**
     * Validacion de marcas
     */
    @Test
    fun testValidMarca() {
        val validMarca = "BMW"
        val invalidMarca = "Toyota"
        assertThat(filtrar.isValidMarca(validMarca)).isTrue()
        assertThat(filtrar.isValidMarca(invalidMarca)).isFalse()
    }
//
    /**
     * Función que conprueba la validación de los años
     */
    @Test
    fun testValidYear() {
        val validYear = "2024"
        val invalidYear = "24"
    assertThat(filtrar.isValidMarca(validYear)).isTrue()
    assertThat(filtrar.isValidMarca(invalidYear)).isFalse()
    }
//
    /**
     * Comprobar que el macth este dentro de 0 y 100
     */
    @Test
    fun testValidMatch() {
        val validMatch = "50"
        val invalidMatch = "101"
    assertThat(filtrar.isValidMarca(validMatch)).isTrue()
    assertThat(filtrar.isValidMarca(invalidMatch)).isFalse()
    }

    @Test
    fun testBuscarColor() {
        // Configurar los parámetros de prueba
        filtrar.hexadecimal.setText("f44336")
        filtrar.marca.setText("BMW")
        filtrar.year.setText("2024")
        filtrar.match.setText("50")

        // Ejecutar la función de prueba
        filtrar.buscarColor()

        // Verificar que se haya llamado a executeGetRequest del HttpClient con los parámetros adecuados
        assertEquals("https://ccd3-176-12-82-226.ngrok-free.app/endpoint", filtrar.serverUrl)
        val expectedParams = hashMapOf(
            "color" to "f44336",
            "marca" to "BMW",
            "anio" to "2024",
            "match" to "50"
        )
        assertEquals(expectedParams, filtrar.params)
    }
}
