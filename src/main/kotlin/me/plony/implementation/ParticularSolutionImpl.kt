package me.plony.implementation

import me.plony.differentialEquations.solutions.ParticularSolution
import me.plony.differentialEquations.utils.ComputedConstant
import me.plony.differentialEquations.utils.Point
import kotlin.math.E
import kotlin.math.ln
import kotlin.math.pow

class ParticularSolutionImpl(val c1: ComputedConstant) : ParticularSolution() {
    override val pointsOfDiscontinuity: List<Double> = listOf(
        ln(1/(4 * c1.value)) / 4
    )

    override fun compute(i: Int, step: Double, initial: Point) = compute(i * step + initial.x)
    override fun compute(input: Double): Double = input + 2 + 1 / (E.pow(4 * input) * c1.value - .25)
}