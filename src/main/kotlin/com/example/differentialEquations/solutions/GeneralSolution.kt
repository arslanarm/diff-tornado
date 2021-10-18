package com.example.differentialEquations.solutions

import com.example.differentialEquations.utils.Point

abstract class GeneralSolution : ExplicitSolution {
    abstract fun particular(points: List<Point>): ParticularSolution
}