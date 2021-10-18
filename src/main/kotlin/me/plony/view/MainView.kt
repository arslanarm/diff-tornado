package me.plony.view

import me.plony.NumberStringConverter
import me.plony.differentialEquations.error.EulerError
import me.plony.differentialEquations.error.ImprovedEulerError
import me.plony.differentialEquations.error.RungeKuttaError
import me.plony.differentialEquations.methods.EulerMethod
import me.plony.differentialEquations.methods.ImprovedEulerMethod
import me.plony.differentialEquations.methods.RungeKuttaMethod
import me.plony.differentialEquations.utils.Point
import me.plony.implementation.DerivativeImpl
import me.plony.implementation.GeneralSolutionImpl
import me.plony.series.*
import javafx.scene.Parent
import javafx.scene.control.ToggleGroup
import tornadofx.*
import kotlin.math.ceil

data class Graphs(
    val values: Series,
    val localError: Series?,
    val globalError: Series?,
    val nGlobalError: Series?
)

class MainView : View("Title") {
    val xProperty = property(0.0)
    var x by xProperty
    val yProperty = property(1.0)
    var y by yProperty
    val maxXProperty = property(20.0)
    var maxX by maxXProperty
    val NProperty = property(50)
    var N by NProperty
    val minNProperty = property(30)
    var minN by NProperty
    val maxNProperty = property(100)
    var maxN by NProperty
    val generalSolution = GeneralSolutionImpl()
    val exactSeries = ExactSeries(yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val eulerSeries = EulerSeries(generalSolution.derivative(), yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val improvedEulerSeries = ImprovedEulerSeries(generalSolution.derivative(), yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val rungeKuttaSeries = RungeKuttaSeries(generalSolution.derivative(), yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val eulerErrorProvider = {
        me.plony.differentialEquations.error.EulerError(
            EulerMethod(
                Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / N
            ),
            GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val improvedEulerErrorProvider = {
        ImprovedEulerError(
            ImprovedEulerMethod(
                Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / N
            ),
            GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val rungeKuttaErrorProvider = {
        me.plony.differentialEquations.error.RungeKuttaError(
            RungeKuttaMethod(
                Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / N
            ),
            GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val eulerNGlobalProvider = { it: Int ->
        me.plony.differentialEquations.error.EulerError(
            EulerMethod(
                Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / it
            ), GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val improvedEulerNGlobalProvider = { it: Int ->
        ImprovedEulerError(
            ImprovedEulerMethod(
                Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / it
            ), GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val rungeKuttaNGlobalProvider = { it: Int ->
        me.plony.differentialEquations.error.RungeKuttaError(
            RungeKuttaMethod(
                Point(x, y),
                generalSolution.derivative(),
                (maxX - x) / it
            ), GeneralSolutionImpl().particular(listOf(Point(x, y)))
        )
    }
    val eulerLocalError = LocalTruncatedError(eulerErrorProvider, 0, "Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val improvedEulerLocalError = LocalTruncatedError(improvedEulerErrorProvider, 1, "Improved Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val rungeKuttaLocalError = LocalTruncatedError(rungeKuttaErrorProvider, 2, "Runge Kutta", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val eulerGlobalError = GlobalTruncatedError(eulerErrorProvider, 3, "Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val improvedEulerGlobalError = GlobalTruncatedError(improvedEulerErrorProvider, 4, "Improved Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val rungeKuttaGlobalError = GlobalTruncatedError(rungeKuttaErrorProvider, 5, "Runge Kutta", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, NProperty.fxProperty)
    val eulerNGlobalError = NGlobalError(eulerNGlobalProvider, 3, "Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, minNProperty.fxProperty, maxNProperty.fxProperty)
    val improvedEulerNGlobalError = NGlobalError(improvedEulerNGlobalProvider, 4, "Improved Euler", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, minNProperty.fxProperty, maxNProperty.fxProperty)
    val rungeKuttaNGlobalError = NGlobalError(rungeKuttaNGlobalProvider, 5, "Runge Kutta", yProperty.fxProperty, xProperty.fxProperty, maxXProperty.fxProperty, minNProperty.fxProperty, maxNProperty.fxProperty)

    val seriesMap = mapOf(
        "exact" to Graphs(exactSeries, null, null, null) ,
        "euler" to Graphs(eulerSeries, eulerLocalError, eulerGlobalError, eulerNGlobalError),
        "improvedEuler" to Graphs(improvedEulerSeries, improvedEulerLocalError, improvedEulerGlobalError, improvedEulerNGlobalError),
        "rungeKutta" to Graphs(rungeKuttaSeries, rungeKuttaLocalError, rungeKuttaGlobalError, rungeKuttaNGlobalError)
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
                        NumberStringConverter(N) { it.toIntOrNull()?.let { if (it <= ceil(maxX - x).toInt()) ceil(maxX - x).toInt() else it } })
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
            vbox {
                hbox {
                    text("Min N: ")
                    textfield().textProperty().bindBidirectional(
                        minNProperty.fxProperty,
                        NumberStringConverter(minN) { it.toIntOrNull()?.let { if (it <= ceil(maxX - x).toInt()) ceil(maxX - x).toInt() else it } })
                }
                hbox {
                    text("Max N: ")
                    textfield().textProperty().bindBidirectional(
                        maxNProperty.fxProperty,
                        NumberStringConverter(maxN) { it.toIntOrNull()?.let { if (it <= minN) minN else it } })
                }
            }
        }
        val plot = Plot("Values", nonNullObjectBinding(seriesProperty.fxProperty) {
            value.map { it.values }.sortedBy { it.order }
        })
        val localErrors = Plot("Local Errors", nonNullObjectBinding(seriesProperty.fxProperty) {
            value.mapNotNull { it.localError }.sortedBy { it.order }
        })
        val globalErrors = Plot("Global Errors", nonNullObjectBinding(seriesProperty.fxProperty) {
            value.mapNotNull { it.globalError }.sortedBy { it.order }
        })
        val nGlobalErrors = Plot("Global Error Relative to N", nonNullObjectBinding(seriesProperty.fxProperty) {
            value.mapNotNull { it.nGlobalError }.sortedBy { it.order }
        })
        add(plot)
        add(localErrors)
        add(globalErrors)
        add(nGlobalErrors)
    }
}