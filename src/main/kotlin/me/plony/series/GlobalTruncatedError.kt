package me.plony.series

import me.plony.differentialEquations.error.Error
import me.plony.differentialEquations.methods.Method
import javafx.beans.property.ReadOnlyProperty
import tornadofx.nonNullObjectBinding

class GlobalTruncatedError<R: Method, T: Error<R>>(
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
            error.globalTruncationError(it)
        }
    },
    minXProperty,
    maxXProperty,
    NProperty
)