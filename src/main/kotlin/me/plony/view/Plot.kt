package me.plony.view

import com.example.series.Series
import com.example.series.XSeries
import javafx.beans.value.ObservableValue
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*

class Plot(val name: String, val series: ObservableValue<List<Series>>) : Fragment() {

    override val root = hbox {
        val chart = linechart(name, NumberAxis(), NumberAxis()) {
            createSymbols = false

            dataProperty().bind(series.objectBinding { list ->
                observableListOf(list!!.map {
                    XYChart.Series(it.name, it.pointsProperty.value).apply {
                        dataProperty().bind(it.pointsProperty)
                    }
                })
            })
        }
        chart.prefWidthProperty().bind(widthProperty())
    }
}
