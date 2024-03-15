//[app](../../../index.md)/[com.example.autocolorsprueba.model.dao](../index.md)/[ColorCocheDao](index.md)

# ColorCocheDao

[androidJvm]\
interface [ColorCocheDao](index.md)

Interfaz que define las operaciones de acceso a datos para la entidad ColorCoche en la base de datos Room.

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract fun [delete](delete.md)(coche: [ColorCoche](../../com.example.autocolorsprueba.model.entity/-color-coche/index.md))<br>Elimina un color de coche de la base de datos. |
| [deleteAll](delete-all.md) | [androidJvm]<br>abstract fun [deleteAll](delete-all.md)()<br>Elimina todos los colores de coche de la base de datos. |
| [getAll](get-all.md) | [androidJvm]<br>abstract fun [getAll](get-all.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[ColorCoche](../../com.example.autocolorsprueba.model.entity/-color-coche/index.md)&gt;<br>Obtiene todos los colores de coche de la base de datos ordenados por porcentaje de coincidencia. |
| [insert](insert.md) | [androidJvm]<br>abstract fun [insert](insert.md)(coche: [ColorCoche](../../com.example.autocolorsprueba.model.entity/-color-coche/index.md))<br>Inserta un color de coche en la base de datos. |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract fun [insertAll](insert-all.md)(coches: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ColorCoche](../../com.example.autocolorsprueba.model.entity/-color-coche/index.md)&gt;)<br>Inserta una lista de colores de coche en la base de datos. |
| [loadAllByIds](load-all-by-ids.md) | [androidJvm]<br>abstract fun [loadAllByIds](load-all-by-ids.md)(userIds: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ColorCoche](../../com.example.autocolorsprueba.model.entity/-color-coche/index.md)&gt;<br>Carga los colores de coche por sus IDs. |
