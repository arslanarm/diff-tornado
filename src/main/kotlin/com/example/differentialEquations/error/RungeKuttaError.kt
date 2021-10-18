package com.example.differentialEquations.error

import com.example.differentialEquations.methods.RungeKuttaMethod
import com.example.differentialEquations.solutions.ParticularSolution

class RungeKuttaError(
    method: RungeKuttaMethod,
    particularSolution: ParticularSolution
) : Error<RungeKuttaMethod>(
    method,
    particularSolution
)