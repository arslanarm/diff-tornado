package com.example.series

import com.example.differentialEquations.solutions.GeneralSolution
import com.example.differentialEquations.utils.Point
import com.example.implementation.GeneralSolutionImpl
import javafx.beans.property.ReadOnlyProperty
import tornadofx.nonNullObjectBinding

class ExactSeries(
    yProperty: ReadOnlyProperty<Double>,
    minXProperty: ReadOnlyProperty<Double>,
    maxXProperty: ReadOnlyProperty<Double>,
    NProperty: ReadOnlyProperty<Int>
) : Series(
    0,
    name = "Exact",
    fProperty = nonNullObjectBinding(minXProperty, yProperty, maxXProperty, NProperty) {

        val solution = GeneralSolutionImpl()
            .particular(listOf(Point(minXProperty.value, yProperty.value)))
        val step = (maxXProperty.value - minXProperty.value) / NProperty.value
        return@nonNullObjectBinding { input: Int ->
            solution.compute(input.toDouble() * step + minXProperty.value)
        }
    },
    minXProperty = minXProperty,
    maxXProperty = maxXProperty,
    NProperty = NProperty
)