package ru.bslab.test.util.rx

import io.reactivex.Completable
import io.reactivex.Observable

fun <T> Completable.toDefaultObservable(defaultValue: T): Observable<T> {
    return toSingleDefault(defaultValue).toObservable()
}