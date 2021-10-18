package me.plony.differentialEquations.abstractions

import me.plony.differentialEquations.solutions.Derivative

interface Differentiable {
    fun derivative(): Derivative
}