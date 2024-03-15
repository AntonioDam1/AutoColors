//[app](../../../index.md)/[com.example.autocolorsprueba.adapter](../index.md)/[ColorCocheViewHolder](index.md)

# ColorCocheViewHolder

[androidJvm]\
class [ColorCocheViewHolder](index.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html)

Clase que modela el ViewHolder para un elemento de la lista de resultados colores de coches. Este ViewHolder se utiliza para mostrar la información de un color de coche en un elemento de la lista del RecyclerView.

## Constructors

| | |
|---|---|
| [ColorCocheViewHolder](-color-coche-view-holder.md) | [androidJvm]<br>constructor(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)) |

## Properties

| Name | Summary |
|---|---|
| [anio](anio.md) | [androidJvm]<br>val [anio](anio.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |
| [codigo](codigo.md) | [androidJvm]<br>val [codigo](codigo.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |
| [fondo](fondo.md) | [androidJvm]<br>val [fondo](fondo.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |
| [hexadecimal](hexadecimal.md) | [androidJvm]<br>val [hexadecimal](hexadecimal.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |
| [itemView](../-color-fav-view-holder/index.md#29975211%2FProperties%2F-912451524) | [androidJvm]<br>@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)<br>val [itemView](../-color-fav-view-holder/index.md#29975211%2FProperties%2F-912451524): [View](https://developer.android.com/reference/kotlin/android/view/View.html) |
| [marca](marca.md) | [androidJvm]<br>val [marca](marca.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |
| [match](match.md) | [androidJvm]<br>val [match](match.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |
| [nombrePintura](nombre-pintura.md) | [androidJvm]<br>val [nombrePintura](nombre-pintura.md): [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html) |

## Functions

| Name | Summary |
|---|---|
| [getAdapterPosition](../-color-fav-view-holder/index.md#644519777%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getAdapterPosition](../-color-fav-view-holder/index.md#644519777%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItemId](../-color-fav-view-holder/index.md#1378485811%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getItemId](../-color-fav-view-holder/index.md#1378485811%2FFunctions%2F-912451524)(): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getItemViewType](../-color-fav-view-holder/index.md#-1649344625%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getItemViewType](../-color-fav-view-holder/index.md#-1649344625%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getLayoutPosition](../-color-fav-view-holder/index.md#-1407255826%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getLayoutPosition](../-color-fav-view-holder/index.md#-1407255826%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getOldPosition](../-color-fav-view-holder/index.md#-1203059319%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getOldPosition](../-color-fav-view-holder/index.md#-1203059319%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getPosition](../-color-fav-view-holder/index.md#-1155470344%2FFunctions%2F-912451524) | [androidJvm]<br>fun [~~getPosition~~](../-color-fav-view-holder/index.md#-1155470344%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isRecyclable](../-color-fav-view-holder/index.md#-1703443315%2FFunctions%2F-912451524) | [androidJvm]<br>fun [isRecyclable](../-color-fav-view-holder/index.md#-1703443315%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [render](render.md) | [androidJvm]<br>fun [render](render.md)(colorCoche: [ColorCoche](../../com.example.autocolorsprueba.model.entity/-color-coche/index.md))<br>Método para establecer los datos del color del coche en la interfaz de usuario. |
| [setIsRecyclable](../-color-fav-view-holder/index.md#-1860912636%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setIsRecyclable](../-color-fav-view-holder/index.md#-1860912636%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [toString](../-color-fav-view-holder/index.md#-1200015593%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [toString](../-color-fav-view-holder/index.md#-1200015593%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
