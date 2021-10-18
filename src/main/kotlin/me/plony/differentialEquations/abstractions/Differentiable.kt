package me.plony.differentialEquations.abstractions

import com.example.differentialEquations.solutions.Derivative

interface Differentiable {
    fun derivative(): Derivative
}