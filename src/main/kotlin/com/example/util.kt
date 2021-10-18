package com.example


import javafx.util.StringConverter
import tornadofx.PropertyDelegate

class NumberStringConverter<T: Number>(var number: T, val f: (String) -> T?) : StringConverter<T>() {

    override fun toString(`object`: T?): String = `object`.toString().also { if (`object` != null) number = `object` }

    override fun fromString(string: String?): T = string?.let(f)?.also { number = it } ?: number
}

fun <T> PropertyDelegate<T>.bind(propertyDelegate: PropertyDelegate<T>) = fxProperty.bind(propertyDelegate.fxProperty)