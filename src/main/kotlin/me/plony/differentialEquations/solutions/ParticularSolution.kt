package me.plony.differentialEquations.solutions

import me.plony.differentialEquations.abstractions.Computable
import me.plony.differentialEquations.utils.Constant
import me.plony.differentialEquations.utils.Point

abstract class ParticularSolution : ExplicitSolution,
    Computable<Double, Double> {
    override val constants: List<Constant> = listOf()
    abstract fun compute(i: Int, step: Double, initial: Point): Double
}