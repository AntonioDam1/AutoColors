//[app](../../../index.md)/[com.example.autocolorsprueba.httpClient](../index.md)/[HttpClient](index.md)

# HttpClient

class [HttpClient](index.md)(listener: [HttpClient.HttpClientListener](-http-client-listener/index.md))

Esta clase representa un cliente HTTP que realiza solicitudes al servidor y maneja las respuestas. Utiliza un HttpClientListener para notificar cuando se reciben los datos del servidor.

#### Parameters

androidJvm

| | |
|---|---|
| listener | El objeto que escucha las respuestas del servidor. |

## Constructors

| | |
|---|---|
| [HttpClient](-http-client.md) | [androidJvm]<br>constructor(listener: [HttpClient.HttpClientListener](-http-client-listener/index.md)) |

## Types

| Name | Summary |
|---|---|
| [Coches](-coches/index.md) | [androidJvm]<br>data class [Coches](-coches/index.md)(val id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val year: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val maker: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val model: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val paintColorName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val code: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val catalogueURL: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val hexadecimal: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val red: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val green: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val blue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val colorSampleURL: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val matchPercentage: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)?)<br>Clase que representa un objeto Coches que contiene información sobre un coche para pasarselo a los métodos. |
| [CypherManagerAES](-cypher-manager-a-e-s/index.md) | [androidJvm]<br>object [CypherManagerAES](-cypher-manager-a-e-s/index.md)<br>Objeto que maneja el cifrado y descifrado utilizando el algoritmo AES. |
| [HttpClientListener](-http-client-listener/index.md) | [androidJvm]<br>interface [HttpClientListener](-http-client-listener/index.md)<br>Interfaz que define un listener para recibir la lista de coches desde el cliente HTTP. |

## Functions

| Name | Summary |
|---|---|
| [executeGetRequest](execute-get-request.md) | [androidJvm]<br>fun [executeGetRequest](execute-get-request.md)(serverUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), params: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;)<br>Ejecuta una solicitud GET al servidor con los parámetros proporcionados y espera la respuesta. |
