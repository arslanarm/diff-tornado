package me.plony.differentialEquations.error

import me.plony.differentialEquations.abstractions.Computable
import me.plony.differentialEquations.methods.Method
import me.plony.differentialEquations.solutions.ParticularSolution
import kotlin.math.abs

abstract class Error<T: Method>(val method: T, val particularSolution: ParticularSolution) {
    fun localTruncationError(i: Int): Double = abs(method.compute(i) - particularSolution.compute(i * method.step + method.initial.x))
    fun globalTruncationError(n: Int): Double = (0..n).maxOf { localTruncationError(it) }
}