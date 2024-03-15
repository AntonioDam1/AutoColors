//[app](../../../index.md)/[com.example.autocolorsprueba](../index.md)/[MainActivity](index.md)/[onContextItemSelected](on-context-item-selected.md)

# onContextItemSelected

[androidJvm]\
open override fun [onContextItemSelected](on-context-item-selected.md)(item: [MenuItem](https://developer.android.com/reference/kotlin/android/view/MenuItem.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Método para manejar las acciones del menú del alphaTileView. Si le damos a agregar a favoritos, nos inserta el color en la base de datos de los colores favoritos Si le damos a copiar hexadecimal nos copiará el hexadecimal al portapapeles Si le damos a comparar, nos comparará el color con los de la base de datos Cada acción tiene un toast, que mostrará un mensaje

#### Return

boolean. Devolvemos true si ha ido bien

#### Parameters

androidJvm

| | |
|---|---|
| item | el item para realizar la acción |
