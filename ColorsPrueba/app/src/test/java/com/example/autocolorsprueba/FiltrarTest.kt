import com.example.autocolorsprueba.Filtrar
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

import java.util.HashMap

class FiltrarTest {

    lateinit var filtrar: Filtrar

    @Before
    fun setUp() {
        filtrar = Filtrar()

    }

    @Test
    fun testValidColorHex() {
        val validHexColor = "#f44336"
        val invalidHexColor = "f44336"
        assertTrue(filtrar.isValidColorHex(validHexColor))
        assertFalse(filtrar.isValidColorHex(invalidHexColor))
    }

    @Test
    fun testValidMarca() {
        val validMarca = "BMW"
        val invalidMarca = "Toyota"
        assertTrue(filtrar.isValidMarca(validMarca))
        assertFalse(filtrar.isValidMarca(invalidMarca))
    }

    @Test
    fun testValidYear() {
        val validYear = "2024"
        val invalidYear = "24"
        assertTrue(filtrar.isValidYear(validYear))
        assertFalse(filtrar.isValidYear(invalidYear))
    }

    @Test
    fun testValidMatch() {
        val validMatch = "50"
        val invalidMatch = "101"
        assertTrue(filtrar.isValidMatch(validMatch))
        assertFalse(filtrar.isValidMatch(invalidMatch))
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
