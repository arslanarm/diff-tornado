package me.plony.differentialEquations.solutions

import me.plony.differentialEquations.utils.Constant

interface ExplicitSolution {
    val constants: List<Constant>
}