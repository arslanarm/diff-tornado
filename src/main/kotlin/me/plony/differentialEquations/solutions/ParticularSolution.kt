package me.plony.differentialEquations.solutions

import me.plony.differentialEquations.abstractions.Computable
import com.example.differentialEquations.utils.Constant
import com.example.differentialEquations.utils.Point

abstract class ParticularSolution : ExplicitSolution,
    me.plony.differentialEquations.abstractions.Computable<Double, Double> {
    override val constants: List<Constant> = listOf()
    abstract fun compute(i: Int, step: Double, initial: Point): Double
}