package com.example.view

import com.example.NumberStringConverter
import com.example.differentialEquations.error.EulerError
import com.example.differentialEquations.error.ImprovedEulerError
import com.example.differentialEquations.error.RungeKuttaError
import com.example.differentialEquations.methods.EulerMethod
import com.example.differentialEquations.methods.ImprovedEulerMethod
import com.example.differentialEquations.methods.RungeKuttaMethod
import com.example.differentialEquations.utils.Point
import com.example.implementation.DerivativeImpl
import com.example.implementation.GeneralSolutionImpl
import com.example.series.*
import javafx.scene.Parent
import javafx.scene.control.ToggleGroup
import tornadofx.*

class MainView : View("Title") {
    val xProperty = property(1.0)
    var x by xProperty
    val yProperty = property(0.0)
    var y by yProperty
    val maxXProperty = property(20.0)
    var maxX by maxXProperty
    val NProperty = property(50)
    var N by NProperty
    val generalSolution = GeneralSolutionImpl()
    val exactSeries = ExactSeries(yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val eulerSeries = EulerSeries(generalSolution.derivative(), yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val improvedEulerSeries = ImprovedEulerSeries(generalSolution.derivative(), yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val rungeKuttaSeries = RungeKuttaSeries(generalSolution.derivative(), yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val eulerErrorProvider = {
        EulerError(
            EulerMethod(Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / N),
            GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val improvedEulerErrorProvider = {
        ImprovedEulerError(
            ImprovedEulerMethod(Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / N),
            GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val rungeKuttaErrorProvider = {
        RungeKuttaError(
            RungeKuttaMethod(Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / N),
            GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val eulerLocalError = LocalTruncatedError(eulerErrorProvider, 0, "Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val improvedEulerLocalError = LocalTruncatedError(improvedEulerErrorProvider, 1, "Improved Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val rungeKuttaLocalError = LocalTruncatedError(rungeKuttaErrorProvider, 2, "Runge Kutta", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val eulerGlobalError = GlobalTruncatedError(eulerErrorProvider, 3, "Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val improvedEulerGlobalError = GlobalTruncatedError(improvedEulerErrorProvider, 4, "Improved Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val rungeKuttaGlobalError = GlobalTruncatedError(rungeKuttaErrorProvider, 5, "Runge Kutta", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)

    val seriesMap = mapOf(
        "exact" to Triple(exactSeries, null, null) ,
        "euler" to Triple(eulerSeries, eulerLocalError, eulerGlobalError),
        "improvedEuler" to Triple(improvedEulerSeries, improvedEulerLocalError, improvedEulerGlobalError),
        "rungeKutta" to Triple(rungeKuttaSeries, rungeKuttaLocalError, rungeKuttaGlobalError)
    )
    val seriesProperty = property(seriesMap.values.toList())
    var series by seriesProperty

    override val root: Parent = vbox {
        hbox {
            vbox {
                hbox {
                    text("X0: ")
                    textfield().textProperty()
                        .bindBidirectional(xProperty.fxProperty, NumberStringConverter(x) { it.toDoubleOrNull() })
                }
                hbox {
                    text("Y0: ")
                    textfield().textProperty()
                        .bindBidirectional(yProperty.fxProperty, NumberStringConverter(y) { it.toDoubleOrNull() })
                }
                hbox {
                    text("X: ")
                    textfield().textProperty()
                        .bindBidirectional(maxXProperty.fxProperty, NumberStringConverter(maxX) { it.toDoubleOrNull() })
                }
                hbox {
                    text("N: ")
                    textfield().textProperty().bindBidirectional(
                        NProperty.fxProperty,
                        NumberStringConverter(N) { it.toIntOrNull()?.let { if (it == 0) 1 else it } })
                }
            }
            vbox {
                radiobutton("Exact",) {
                    isSelected = true
                    setOnAction {
                        series = if (seriesMap["exact"] !in series) (series + seriesMap["exact"]!!)
                        else (series - seriesMap["exact"]!!)
                    }
                }
                radiobutton("Euler",) {
                    isSelected = true
                    setOnAction {
                        series = if (seriesMap["euler"] !in series) series + seriesMap["euler"]!!
                        else series - seriesMap["euler"]!!
                    }
                }
                radiobutton("Improved Euler",) {
                    isSelected = true
                    setOnAction {
                        series = if (seriesMap["improvedEuler"] !in series) series + seriesMap["improvedEuler"]!!
                        else series - seriesMap["improvedEuler"]!!
                    }
                }
                radiobutton("Runge Kutta",) {
                    isSelected = true
                    setOnAction {
                        series = if (seriesMap["rungeKutta"] !in series) series + seriesMap["rungeKutta"]!!
                        else series - seriesMap["rungeKutta"]!!
                    }
                }
            }
        }
        val plot = Plot("Values", nonNullObjectBinding(seriesProperty.fxProperty) {
            value.map { it.first }.sortedBy { it.order }
        })
        val localErrors = Plot("Local Errors", nonNullObjectBinding(seriesProperty.fxProperty) {
            value.mapNotNull { it.second }.sortedBy { it.order }
        })
        val globalErrors = Plot("Global Errors", nonNullObjectBinding(seriesProperty.fxProperty) {
            value.mapNotNull { it.third }.sortedBy { it.order }
        })
        add(plot)
        add(localErrors)
        add(globalErrors)

    }
}