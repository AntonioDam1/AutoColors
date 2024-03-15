//[app](../../../../index.md)/[com.example.autocolorsprueba.httpClient](../../index.md)/[HttpClient](../index.md)/[CypherManagerAES](index.md)

# CypherManagerAES

[androidJvm]\
object [CypherManagerAES](index.md)

Objeto que maneja el cifrado y descifrado utilizando el algoritmo AES.

## Functions

| Name | Summary |
|---|---|
| [cifrar](cifrar.md) | [androidJvm]<br>fun [cifrar](cifrar.md)(textoEnClaro: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), key: [Key](https://developer.android.com/reference/kotlin/java/security/Key.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Cifra un texto utilizando el algoritmo AES. |
| [descifrar](descifrar.md) | [androidJvm]<br>fun [descifrar](descifrar.md)(textoCifrado: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), key: [Key](https://developer.android.com/reference/kotlin/java/security/Key.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Descifra un texto cifrado utilizando el algoritmo AES. |
| [obtenerClave](obtener-clave.md) | [androidJvm]<br>fun [obtenerClave](obtener-clave.md)(password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), longitud: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Key](https://developer.android.com/reference/kotlin/java/security/Key.html)<br>Obtiene una clave secreta para el cifrado y descifrado AES. |
