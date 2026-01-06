package com.example.model.base

import android.os.Bundle
import androidx.core.os.bundleOf
import kotlin.collections.map

abstract class Abstract {

    interface UiObject

    interface DomainObject

    interface DataObject

    interface Mapper {

        interface Base<in S, out R> : Mapper {
            fun map(data: S, arg: Bundle = bundleOf()): R
            fun map(data: List<S>, arg: Bundle = bundleOf()) = data.map {
                map(it, arg)
            }
        }

        interface DataToDomain<S, R : DomainObject> : Base<S, R>

        interface DomainToUi<in S : DomainObject, out T : UiObject> : Base<S, T>

        interface DomainToData<in S : DomainObject, out T : DataObject> : Base<S, T>
    }
}