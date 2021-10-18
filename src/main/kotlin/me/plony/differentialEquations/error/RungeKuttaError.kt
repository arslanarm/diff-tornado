package me.plony.differentialEquations.error

import me.plony.differentialEquations.methods.RungeKuttaMethod
import me.plony.differentialEquations.solutions.ParticularSolution

class RungeKuttaError(
    method: RungeKuttaMethod,
    particularSolution: ParticularSolution
) : me.plony.differentialEquations.error.Error<RungeKuttaMethod>(
    method,
    particularSolution
)