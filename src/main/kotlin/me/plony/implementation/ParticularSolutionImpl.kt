package me.plony.implementation

import com.example.differentialEquations.solutions.ParticularSolution
import com.example.differentialEquations.utils.ComputedConstant
import com.example.differentialEquations.utils.Point
import kotlin.math.E
import kotlin.math.pow

class ParticularSolutionImpl(val c1: ComputedConstant) : ParticularSolution() {
    override fun compute(i: Int, step: Double, initial: Point) = compute(i * step + initial.x)
    override fun compute(input: Double): Double = input + 2 + 1 / (E.pow(4 * input) * c1.value - .25)
}