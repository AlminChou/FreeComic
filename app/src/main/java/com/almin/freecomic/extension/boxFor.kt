package com.almin.freecomic.extension

import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.Property
import io.objectbox.query.Query
import io.objectbox.query.QueryBuilder
import io.objectbox.relation.ToMany
import kotlin.reflect.KClass

inline fun <reified T> BoxStore.boxFor(): Box<T> = boxFor(T::class.java)

@Suppress("NOTHING_TO_INLINE")
inline fun <T : Any> BoxStore.boxFor(clazz: KClass<T>): Box<T> = boxFor(clazz.java)

/** An alias for the "in" method, which is a reserved keyword in Kotlin. */
inline fun <reified T> QueryBuilder<T>.inValues(property: Property<T>, values: LongArray): QueryBuilder<T>
        = `in`(property, values)

/** An alias for the "in" method, which is a reserved keyword in Kotlin. */
inline fun <reified T> QueryBuilder<T>.inValues(property: Property<T>, values: IntArray): QueryBuilder<T>
        = `in`(property, values)

/** An alias for the "in" method, which is a reserved keyword in Kotlin. */
inline fun <reified T> QueryBuilder<T>.inValues(property: Property<T>, values: Array<String>): QueryBuilder<T>
        = `in`(property, values)

/** An alias for the "in" method, which is a reserved keyword in Kotlin. */
inline fun <reified T> QueryBuilder<T>.inValues(property: Property<T>, values: Array<String>,
                                                stringOrder: QueryBuilder.StringOrder): QueryBuilder<T>
        = `in`(property, values, stringOrder)

/**
 * Allows building a query for this Box instance with a call to [build][QueryBuilder.build] to return a [Query] instance.
 * ```
 * val query = box.query {
 *     equal(Entity_.property, value)
 * }
 * ```
 */
inline fun <T> Box<T>.query(block: QueryBuilder<T>.() -> Unit) : Query<T> {
    val builder = query()
    block(builder)
    return builder.build()
}

/**
 * Allows making changes (adding and removing entities) to this ToMany with a call to
 * [apply][ToMany.applyChangesToDb] the changes to the database.
 * Can [reset][ToMany.reset] the ToMany before making changes.
 * ```
 * toMany.applyChangesToDb {
 *     add(entity)
 * }
 * ```
 */
inline fun <T> ToMany<T>.applyChangesToDb(resetFirst: Boolean = false, body: ToMany<T>.() -> Unit) {
    if (resetFirst) reset()
    body()
    applyChangesToDb()
}