package me.plony.implementation

import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.utils.Point
import kotlin.math.pow

class DerivativeImpl : Derivative() {
    override fun compute(input: Point): Double {
        val (x, y) = input
        return 5 - x.pow(2) - y.pow(2) + 2 * x * y
    }
}