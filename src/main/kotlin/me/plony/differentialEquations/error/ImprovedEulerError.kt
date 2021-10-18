package me.plony.differentialEquations.error

import me.plony.differentialEquations.methods.ImprovedEulerMethod
import me.plony.differentialEquations.solutions.ParticularSolution

class ImprovedEulerError(
    method: ImprovedEulerMethod,
    particularSolution: ParticularSolution
) : me.plony.differentialEquations.error.Error<ImprovedEulerMethod>(
    method,
    particularSolution
)