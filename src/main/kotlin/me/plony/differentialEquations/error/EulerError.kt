package me.plony.differentialEquations.error

import me.plony.differentialEquations.methods.EulerMethod
import me.plony.differentialEquations.solutions.ParticularSolution
import kotlin.math.abs

class EulerError(
    method: EulerMethod,
    particularSolution: ParticularSolution
) : me.plony.differentialEquations.error.Error<EulerMethod>(
    method,
    particularSolution
)