//[app](../../../../index.md)/[com.example.autocolorsprueba.httpClient](../../index.md)/[HttpClient](../index.md)/[CypherManagerAES](index.md)/[obtenerClave](obtener-clave.md)

# obtenerClave

[androidJvm]\
fun [obtenerClave](obtener-clave.md)(password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), longitud: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Key](https://developer.android.com/reference/kotlin/java/security/Key.html)

Obtiene una clave secreta para el cifrado y descifrado AES.

#### Return

Una instancia de la clase Key que representa la clave secreta.

#### Parameters

androidJvm

| | |
|---|---|
| password | La contraseña utilizada para generar la clave. |
| longitud | La longitud de la clave en bytes (16, 24 o 32). |
