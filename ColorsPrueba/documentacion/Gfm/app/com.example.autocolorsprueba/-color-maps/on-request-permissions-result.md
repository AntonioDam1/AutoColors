//[app](../../../index.md)/[com.example.autocolorsprueba](../index.md)/[ColorMaps](index.md)/[onRequestPermissionsResult](on-request-permissions-result.md)

# onRequestPermissionsResult

[androidJvm]\
open override fun [onRequestPermissionsResult](on-request-permissions-result.md)(requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), permissions: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, grantResults: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html))

Callback que se invoca cuando el usuario responde a la solicitud de permisos de la aplicación. Se verifica si los permisos de ubicación fueron otorgados. Si se otorgaron los permisos, se activa la ubicación del usuario en el mapa. Si los permisos no fueron otorgados, se muestra un mensaje explicativo al usuario.

#### Parameters

androidJvm

| | |
|---|---|
| requestCode | Código de solicitud de permisos. |
| permissions | Arreglo de permisos solicitados. |
| grantResults | Arreglo de resultados de la solicitud de permisos. |
