package me.plony.differentialEquations.error

import me.plony.differentialEquations.abstractions.Computable
import me.plony.differentialEquations.methods.Method
import me.plony.differentialEquations.solutions.ParticularSolution
import kotlin.math.abs

abstract class Error<T: Method>(val method: T, val particularSolution: ParticularSolution) {
    fun localTruncationError(i: Int): Double = abs(
        if (i == 0) .0
        else method.computeWithPrevious(i, particularSolution.compute(i - 1, method.step, method.initial)) -
            particularSolution.compute(i, method.step, method.initial)
    )
    fun globalTruncationError(i: Int): Double =
        abs(method.compute(i) - particularSolution.compute(i * method.step + method.initial.x))
}