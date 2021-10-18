package com.example.series

import com.example.differentialEquations.methods.RungeKuttaMethod
import com.example.differentialEquations.solutions.Derivative
import com.example.differentialEquations.utils.Point
import javafx.beans.property.ReadOnlyProperty
import tornadofx.nonNullObjectBinding

class RungeKuttaSeries(
    derivative: Derivative,
    yProperty: ReadOnlyProperty<Double>,
    minXProperty: ReadOnlyProperty<Double>,
    maxXProperty: ReadOnlyProperty<Double>,
    NProperty: ReadOnlyProperty<Int>
)  : XSeries(
    3,
    "Runge Kutta",
    nonNullObjectBinding(minXProperty, yProperty, maxXProperty, NProperty) {
        val method = RungeKuttaMethod(
            initial = Point(minXProperty.value, yProperty.value),
            derivative = derivative,
            step = (maxXProperty.value - minXProperty.value) / NProperty.value
        )
        return@nonNullObjectBinding { input: Int ->
            method.compute(input)
        }
    },
    minXProperty,
    maxXProperty,
    NProperty
) {
}