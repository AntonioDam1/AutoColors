//[app](../../../index.md)/[com.example.autocolorsprueba.model.dao](../index.md)/[ColorFavDao](index.md)

# ColorFavDao

[androidJvm]\
interface [ColorFavDao](index.md)

Interfaz que define las operaciones de acceso a datos para la entidad ColorFav en la base de datos Room.

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract fun [delete](delete.md)(user: [ColorFav](../../com.example.autocolorsprueba.model.entity/-color-fav/index.md))<br>Elimina un color favorito de la base de datos. |
| [getAll](get-all.md) | [androidJvm]<br>abstract fun [getAll](get-all.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[ColorFav](../../com.example.autocolorsprueba.model.entity/-color-fav/index.md)&gt;<br>Obtiene todos los colores favoritos de la base de datos. |
| [insert](insert.md) | [androidJvm]<br>abstract fun [insert](insert.md)(vararg users: [ColorFav](../../com.example.autocolorsprueba.model.entity/-color-fav/index.md))<br>Inserta un color favorito en la base de datos. |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract fun [insertAll](insert-all.md)(vararg users: [ColorFav](../../com.example.autocolorsprueba.model.entity/-color-fav/index.md))<br>Inserta uno o más colores favoritos en la base de datos. |
| [loadAllByIds](load-all-by-ids.md) | [androidJvm]<br>abstract fun [loadAllByIds](load-all-by-ids.md)(userIds: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ColorFav](../../com.example.autocolorsprueba.model.entity/-color-fav/index.md)&gt;<br>Carga los colores favoritos por sus IDs. |
