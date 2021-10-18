package com.example.differentialEquations.solutions

import com.example.differentialEquations.abstractions.Computable
import com.example.differentialEquations.utils.Constant
import com.example.differentialEquations.utils.Point

abstract class ParticularSolution : ExplicitSolution, Computable<Double, Double> {
    override val constants: List<Constant> = listOf()
    abstract fun compute(i: Int, step: Double, initial: Point): Double
}