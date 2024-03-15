//[app](../../../index.md)/[com.example.autocolorsprueba.database](../index.md)/[CochesRoomDatabase](index.md)

# CochesRoomDatabase

[androidJvm]\
abstract class [CochesRoomDatabase](index.md) : [RoomDatabase](https://developer.android.com/reference/kotlin/androidx/room/RoomDatabase.html)

Clase que crea y representa la base de datos de la aplicación para almacenar colores e insertar a favoritos o a ColoresCoche Esta base de datos utiliza la arquitectura de Room, que es una capa de abstracción sobre SQLite. Proporciona métodos para acceder a las instancias de los DAOs para realizar operaciones CRUD en las tablas de la base de datos. Aquí se declara el nombre de las tablas y sus DAO

## Constructors

| | |
|---|---|
| [CochesRoomDatabase](-coches-room-database.md) | [androidJvm]<br>constructor() |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [backingFieldMap](index.md#155738858%2FProperties%2F-912451524) | [androidJvm]<br>val [backingFieldMap](index.md#155738858%2FProperties%2F-912451524): [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; |
| [invalidationTracker](index.md#-990093491%2FProperties%2F-912451524) | [androidJvm]<br>open val [invalidationTracker](index.md#-990093491%2FProperties%2F-912451524): [InvalidationTracker](https://developer.android.com/reference/kotlin/androidx/room/InvalidationTracker.html) |
| [isOpen](index.md#-277138657%2FProperties%2F-912451524) | [androidJvm]<br>open val [isOpen](index.md#-277138657%2FProperties%2F-912451524): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [openHelper](index.md#-1864821605%2FProperties%2F-912451524) | [androidJvm]<br>open val [openHelper](index.md#-1864821605%2FProperties%2F-912451524): [SupportSQLiteOpenHelper](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteOpenHelper.html) |
| [queryExecutor](index.md#-177284564%2FProperties%2F-912451524) | [androidJvm]<br>open val [queryExecutor](index.md#-177284564%2FProperties%2F-912451524): [Executor](https://developer.android.com/reference/kotlin/java/util/concurrent/Executor.html) |
| [suspendingTransactionId](index.md#1027959380%2FProperties%2F-912451524) | [androidJvm]<br>val [suspendingTransactionId](index.md#1027959380%2FProperties%2F-912451524): [ThreadLocal](https://developer.android.com/reference/kotlin/java/lang/ThreadLocal.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt; |
| [transactionExecutor](index.md#722320214%2FProperties%2F-912451524) | [androidJvm]<br>open val [transactionExecutor](index.md#722320214%2FProperties%2F-912451524): [Executor](https://developer.android.com/reference/kotlin/java/util/concurrent/Executor.html) |

## Functions

| Name | Summary |
|---|---|
| [assertNotMainThread](index.md#-917214377%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [assertNotMainThread](index.md#-917214377%2FFunctions%2F-912451524)() |
| [assertNotSuspendingTransaction](index.md#1166251624%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [assertNotSuspendingTransaction](index.md#1166251624%2FFunctions%2F-912451524)() |
| [beginTransaction](index.md#1020009182%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [~~beginTransaction~~](index.md#1020009182%2FFunctions%2F-912451524)() |
| [clearAllTables](index.md#404244410%2FFunctions%2F-912451524) | [androidJvm]<br>abstract fun [clearAllTables](index.md#404244410%2FFunctions%2F-912451524)() |
| [close](index.md#1674273423%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [close](index.md#1674273423%2FFunctions%2F-912451524)() |
| [colorCocheDao](color-coche-dao.md) | [androidJvm]<br>abstract fun [colorCocheDao](color-coche-dao.md)(): [ColorCocheDao](../../com.example.autocolorsprueba.model.dao/-color-coche-dao/index.md) |
| [colorFavDao](color-fav-dao.md) | [androidJvm]<br>abstract fun [colorFavDao](color-fav-dao.md)(): [ColorFavDao](../../com.example.autocolorsprueba.model.dao/-color-fav-dao/index.md) |
| [compileStatement](index.md#162913197%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [compileStatement](index.md#162913197%2FFunctions%2F-912451524)(sql: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SupportSQLiteStatement](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteStatement.html) |
| [endTransaction](index.md#622722960%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [~~endTransaction~~](index.md#622722960%2FFunctions%2F-912451524)() |
| [getAutoMigrations](index.md#178130989%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getAutoMigrations](index.md#178130989%2FFunctions%2F-912451524)(autoMigrationSpecs: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;out [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;, [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Migration](https://developer.android.com/reference/kotlin/androidx/room/migration/Migration.html)&gt; |
| [getRequiredAutoMigrationSpecs](index.md#1623281881%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getRequiredAutoMigrationSpecs](index.md#1623281881%2FFunctions%2F-912451524)(): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;out [AutoMigrationSpec](https://developer.android.com/reference/kotlin/androidx/room/migration/AutoMigrationSpec.html)&gt;&gt; |
| [getTypeConverter](index.md#-194849133%2FFunctions%2F-912451524) | [androidJvm]<br>open fun &lt;[T](index.md#-194849133%2FFunctions%2F-912451524)&gt; [getTypeConverter](index.md#-194849133%2FFunctions%2F-912451524)(klass: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](index.md#-194849133%2FFunctions%2F-912451524)&gt;): [T](index.md#-194849133%2FFunctions%2F-912451524)? |
| [init](index.md#1039887154%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [init](index.md#1039887154%2FFunctions%2F-912451524)(configuration: [DatabaseConfiguration](https://developer.android.com/reference/kotlin/androidx/room/DatabaseConfiguration.html)) |
| [inTransaction](index.md#-1889647314%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [inTransaction](index.md#-1889647314%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [query](index.md#604106995%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [query](index.md#604106995%2FFunctions%2F-912451524)(query: [SupportSQLiteQuery](https://developer.android.com/reference/kotlin/androidx/sqlite/db/SupportSQLiteQuery.html), signal: [CancellationSignal](https://developer.android.com/reference/kotlin/android/os/CancellationSignal.html)?): [Cursor](https://developer.android.com/reference/kotlin/android/database/Cursor.html)<br>open fun [query](index.md#-1376474873%2FFunctions%2F-912451524)(query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), args: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;out [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt;?): [Cursor](https://developer.android.com/reference/kotlin/android/database/Cursor.html) |
| [runInTransaction](index.md#1063989044%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [runInTransaction](index.md#1063989044%2FFunctions%2F-912451524)(body: [Runnable](https://developer.android.com/reference/kotlin/java/lang/Runnable.html))<br>open fun &lt;[V](index.md#-1842697888%2FFunctions%2F-912451524)&gt; [runInTransaction](index.md#-1842697888%2FFunctions%2F-912451524)(body: [Callable](https://developer.android.com/reference/kotlin/java/util/concurrent/Callable.html)&lt;[V](index.md#-1842697888%2FFunctions%2F-912451524)&gt;): [V](index.md#-1842697888%2FFunctions%2F-912451524) |
| [setTransactionSuccessful](index.md#954356125%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [~~setTransactionSuccessful~~](index.md#954356125%2FFunctions%2F-912451524)() |
