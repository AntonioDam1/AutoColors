package com.example.autocolorsprueba

/**
 * Objeto singleton para almacenar una cadena de texto dentro de la aplicación.
 */
object ColorStorage {
    /**
     * Cadena de texto almacenada.
     */
    private var storedString: String? = null

    /**
     * Establece el valor de la cadena almacenada.
     *
     * @param value La cadena de texto que se va a almacenar.
     */
    fun setString(value: String) {
        storedString = value
    }

    /**
     * Obtiene el valor actual de la cadena almacenada.
     *
     * @return La cadena de texto almacenada, o null si no se ha establecido ninguna.
     */
    fun getString(): String? {
        return storedString
    }
}