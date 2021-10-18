package com.example.series

import javafx.beans.property.ReadOnlyProperty
import javafx.beans.value.ObservableObjectValue
import javafx.scene.chart.XYChart
import tornadofx.*

open class Series(
    val order: Int,
    val name: String,
    val fProperty: ObservableObjectValue<(Int) -> Double>,
    val minXProperty: ReadOnlyProperty<Double>,
    val maxXProperty: ReadOnlyProperty<Double>,
    val NProperty: ReadOnlyProperty<Int>
) {

    val f by fProperty
    val minX by minXProperty
    val maxX by maxXProperty
    val N by NProperty
    val pointsProperty = objectBinding(
        fProperty
    ) {
        val xs = List(N + 1) { XYChart.Data<Number, Number>(it.toDouble() * ((maxX - minX) / N) + minX, f(it)) }

        if (xs.any { it.yValue in listOf(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN) }) observableListOf(xs.map { XYChart.Data<Number, Number>(0, 0) })
        else observableListOf(xs)
    }
}