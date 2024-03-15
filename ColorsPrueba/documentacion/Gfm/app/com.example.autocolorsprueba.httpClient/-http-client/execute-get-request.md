//[app](../../../index.md)/[com.example.autocolorsprueba.httpClient](../index.md)/[HttpClient](index.md)/[executeGetRequest](execute-get-request.md)

# executeGetRequest

[androidJvm]\
fun [executeGetRequest](execute-get-request.md)(serverUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), params: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;)

Ejecuta una solicitud GET al servidor con los parámetros proporcionados y espera la respuesta.

#### Parameters

androidJvm

| | |
|---|---|
| serverUrl | La URL del servidor al que se enviará la solicitud. |
| params | Los parámetros de la solicitud, representados como un mapa de clave-valor. |

#### Throws

| | |
|---|---|
| MalformedURLException | Si la URL proporcionada no es válida. |
| UnsupportedEncodingException | Si ocurre un error al codificar los parámetros de la solicitud. |
