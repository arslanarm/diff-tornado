package me.plony.differentialEquations.error

import com.example.differentialEquations.methods.RungeKuttaMethod
import com.example.differentialEquations.solutions.ParticularSolution

class RungeKuttaError(
    method: RungeKuttaMethod,
    particularSolution: ParticularSolution
) : me.plony.differentialEquations.error.Error<RungeKuttaMethod>(
    method,
    particularSolution
)