package com.example.differentialEquations.error

import com.example.differentialEquations.methods.ImprovedEulerMethod
import com.example.differentialEquations.solutions.ParticularSolution

class ImprovedEulerError(
    method: ImprovedEulerMethod,
    particularSolution: ParticularSolution
) : Error<ImprovedEulerMethod>(
    method,
    particularSolution
)