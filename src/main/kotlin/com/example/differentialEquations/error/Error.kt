package com.example.differentialEquations.error

import com.example.differentialEquations.abstractions.Computable
import com.example.differentialEquations.methods.Method
import com.example.differentialEquations.solutions.ParticularSolution
import kotlin.math.abs

abstract class Error<T: Method>(val method: T, val particularSolution: ParticularSolution) {
    fun localTruncationError(i: Int): Double =
        if (i == 0) .0
        else method.computeWithPrevious(i, particularSolution.compute(i - 1, method.step, method.initial)) -
            particularSolution.compute(i, method.step, method.initial)
    fun globalTruncationError(i: Int): Double =
        abs(method.compute(i) - particularSolution.compute(i * method.step + method.initial.x))
}