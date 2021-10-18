package me.plony.series

import me.plony.differentialEquations.methods.EulerMethod
import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.utils.Point
import javafx.beans.property.ReadOnlyProperty
import tornadofx.nonNullObjectBinding

class EulerSeries(
    derivative: Derivative,
    yProperty: ReadOnlyProperty<Double>,
    minXProperty: ReadOnlyProperty<Double>,
    maxXProperty: ReadOnlyProperty<Double>,
    NProperty: ReadOnlyProperty<Int>
) : XSeries(
    1,
    name = "Euler",
    fProperty = nonNullObjectBinding(minXProperty, yProperty, maxXProperty, NProperty) {
        val method = me.plony.differentialEquations.methods.EulerMethod(
            initial = Point(minXProperty.value, yProperty.value),
            derivative = derivative,
            step = (maxXProperty.value - minXProperty.value) / NProperty.value
        )
        return@nonNullObjectBinding { input: Int ->
            method.compute(input)
        }
    },
    minXProperty = minXProperty,
    maxXProperty = maxXProperty,
    NProperty = NProperty
) {
}