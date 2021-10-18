package com.example.differentialEquations.abstractions

import com.example.differentialEquations.solutions.Derivative

interface Differentiable {
    fun derivative(): Derivative
}