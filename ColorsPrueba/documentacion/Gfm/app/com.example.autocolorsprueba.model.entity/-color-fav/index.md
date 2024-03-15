//[app](../../../index.md)/[com.example.autocolorsprueba.model.entity](../index.md)/[ColorFav](index.md)

# ColorFav

data class [ColorFav](index.md)(val uid: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), val anio: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?, val marca: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val modelo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val nombrePintura: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val codigo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val catalogueURL: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val hexadecimal: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val red: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val green: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val blue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val colorsampleURL: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val matchPercentage: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)?) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html)

Esta clase representa la entidad ColorFav en la base de datos. Contiene información sobre un color de coche marcado como favorito.

#### Parameters

androidJvm

| | |
|---|---|
| uid | El identificador único del color favorito del coche. |
| anio | El año del coche al que pertenece el color favorito. |
| marca | La marca del coche al que pertenece el color favorito. |
| modelo | El modelo del coche al que pertenece el color favorito. |
| nombrePintura | El nombre del color de pintura del coche favorito. |
| codigo | El código del color de pintura del coche favorito. |
| catalogueURL | La URL del catálogo del coche favorito. |
| hexadecimal | El valor hexadecimal del color de pintura del coche favorito. |
| red | El componente rojo del color de pintura del coche favorito. |
| green | El componente verde del color de pintura del coche favorito. |
| blue | El componente azul del color de pintura del coche favorito. |
| colorsampleURL | La URL de la muestra de color del coche favorito. |
| matchPercentage | El porcentaje de coincidencia del color de pintura del coche favorito, puede ser nulo. |

## Constructors

| | |
|---|---|
| [ColorFav](-color-fav.md) | [androidJvm]<br>constructor(uid: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), anio: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?, marca: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, modelo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, nombrePintura: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, codigo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, catalogueURL: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, hexadecimal: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, red: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), green: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), blue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), colorsampleURL: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, matchPercentage: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)?) |

## Properties

| Name | Summary |
|---|---|
| [anio](anio.md) | [androidJvm]<br>val [anio](anio.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? |
| [blue](blue.md) | [androidJvm]<br>val [blue](blue.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [catalogueURL](catalogue-u-r-l.md) | [androidJvm]<br>val [catalogueURL](catalogue-u-r-l.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [codigo](codigo.md) | [androidJvm]<br>val [codigo](codigo.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [colorsampleURL](colorsample-u-r-l.md) | [androidJvm]<br>val [colorsampleURL](colorsample-u-r-l.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [green](green.md) | [androidJvm]<br>val [green](green.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [hexadecimal](hexadecimal.md) | [androidJvm]<br>val [hexadecimal](hexadecimal.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [marca](marca.md) | [androidJvm]<br>val [marca](marca.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [matchPercentage](match-percentage.md) | [androidJvm]<br>val [matchPercentage](match-percentage.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? |
| [modelo](modelo.md) | [androidJvm]<br>val [modelo](modelo.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [nombrePintura](nombre-pintura.md) | [androidJvm]<br>val [nombrePintura](nombre-pintura.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [red](red.md) | [androidJvm]<br>val [red](red.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [uid](uid.md) | [androidJvm]<br>val [uid](uid.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
