package me.plony.series

import me.plony.differentialEquations.methods.RungeKuttaMethod
import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.utils.Point
import javafx.beans.property.ReadOnlyProperty
import me.plony.implementation.GeneralSolutionImpl
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
            GeneralSolutionImpl().particular(listOf(Point(minXProperty.value, yProperty.value))),
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