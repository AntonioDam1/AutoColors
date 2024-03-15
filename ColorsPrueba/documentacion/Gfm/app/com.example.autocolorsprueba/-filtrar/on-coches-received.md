//[app](../../../index.md)/[com.example.autocolorsprueba](../index.md)/[Filtrar](index.md)/[onCochesReceived](on-coches-received.md)

# onCochesReceived

[androidJvm]\
open fun [onCochesReceived](on-coches-received.md)(cochesList: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;&lt;Error class: unknown class&gt;&gt;)

Función que se tiene que implementar de la interfaz HttpClientListener Nos creamos una instancia de la base de datos Este método trata los objetos del JSON que viene de la consulta con la base de datos. Primero borramos lo que haya en la base de datos, para así borrar los resultados de consultas prevías en un hilo aparte Después recorremos la lista para insertar el objeto en nuestra base de datos para los resultados de las consultas Si no existe ese color para una marca lo  insertamos en un hilo diferente, para así no repetir los mismos colores. Nos guardamos ese color para que al tocar un color de la lista lo comparemos con el original Por último nos manda a ConsultasActivity para ver el resultado de la consulta

#### Parameters

androidJvm

| | |
|---|---|
| cochesList | recibe la lista de coches que devuelve la base de datos |
