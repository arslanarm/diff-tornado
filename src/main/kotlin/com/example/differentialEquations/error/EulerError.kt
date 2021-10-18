package com.example.differentialEquations.error

import com.example.differentialEquations.methods.EulerMethod
import com.example.differentialEquations.solutions.ParticularSolution
import kotlin.math.abs

class EulerError(
    method: EulerMethod,
    particularSolution: ParticularSolution
) : Error<EulerMethod>(
    method,
    particularSolution
)