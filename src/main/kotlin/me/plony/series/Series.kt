package me.plony.series

import javafx.beans.value.ObservableValue
import javafx.collections.ObservableList
import javafx.scene.chart.XYChart

interface Series {
    val order: Int
    val name: String
    val pointsProperty: ObservableValue<ObservableList<XYChart.Data<Number, Number>>>
}