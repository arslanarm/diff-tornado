package com.example.series

import com.example.differentialEquations.error.Error
import com.example.differentialEquations.methods.Method
import javafx.beans.property.ReadOnlyProperty
import tornadofx.nonNullObjectBinding

class LocalTruncatedError<R: Method, T: Error<R>>(
    errorProvider: () -> T,
    order: Int, name: String,
    yProperty: ReadOnlyProperty<Double>,
    minXProperty: ReadOnlyProperty<Double>,
    maxXProperty: ReadOnlyProperty<Double>,
    NProperty: ReadOnlyProperty<Int>
) : XSeries(
    order, name,
    nonNullObjectBinding(minXProperty, yProperty, maxXProperty, NProperty) {
        val error = errorProvider()
        return@nonNullObjectBinding{
            error.localTruncationError(it)
        }
    },
    minXProperty,
    maxXProperty,
    NProperty
)