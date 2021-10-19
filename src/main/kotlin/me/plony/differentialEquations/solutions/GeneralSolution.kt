package me.plony.differentialEquations.solutions

import me.plony.differentialEquations.utils.Point

abstract class GeneralSolution : ExplicitSolution {
    abstract fun particular(points: List<Point>): ParticularSolution
}