package me.plony.series

import me.plony.differentialEquations.error.Error
import me.plony.differentialEquations.methods.Method
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.value.ObservableValue
import javafx.collections.ObservableList
import javafx.scene.chart.XYChart
import tornadofx.getValue
import tornadofx.nonNullObjectBinding
import tornadofx.observableListOf

class NGlobalError<R: me.plony.differentialEquations.methods.Method, T: me.plony.differentialEquations.error.Error<R>>(
    errorProvider: (Int) -> T,
    override val order: Int,
    override val name: String,
    yProperty: ReadOnlyProperty<Double>,
    minXProperty: ReadOnlyProperty<Double>,
    maxXProperty: ReadOnlyProperty<Double>,
    minNProperty: ReadOnlyProperty<Int>,
    maxNProperty: ReadOnlyProperty<Int>
) : Series {

    override val pointsProperty = nonNullObjectBinding(minXProperty, yProperty, maxXProperty, minNProperty, maxNProperty) {
        val list = (minNProperty.value..maxNProperty.value).map {
            val error = errorProvider(it)
            it to List(it + 1) { error.globalTruncationError(it) }.maxOrNull()!!
        }
        if (list.any { it.second in listOf(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN) }) observableListOf(list.map { XYChart.Data<Number, Number>(0, 0) })
        else observableListOf(list.map { (x,y) -> XYChart.Data<Number, Number>(x, y) })
    }
}