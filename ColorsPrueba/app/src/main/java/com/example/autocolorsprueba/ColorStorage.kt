package com.example.autocolorsprueba

object ColorStorage {
    private var storedString: String? = null

    fun setString(value: String) {
        storedString = value
    }

    fun getString(): String? {
        return storedString
    }
}