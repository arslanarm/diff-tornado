package me.plony.implementation

import me.plony.differentialEquations.solutions.ParticularSolution
import me.plony.differentialEquations.utils.ComputedConstant
import me.plony.differentialEquations.utils.Point
import kotlin.math.E
import kotlin.math.pow

class ParticularSolutionImpl(val c1: ComputedConstant) : ParticularSolution() {
    override fun compute(i: Int, step: Double, initial: Point) = compute(i * step + initial.x)
    override fun compute(input: Double): Double = input + 2 + 1 / (E.pow(4 * input) * c1.value - .25)
}