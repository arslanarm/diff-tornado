package me.plony.differentialEquations.error

import me.plony.differentialEquations.methods.ImprovedEulerMethod
import com.example.differentialEquations.solutions.ParticularSolution

class ImprovedEulerError(
    method: me.plony.differentialEquations.methods.ImprovedEulerMethod,
    particularSolution: ParticularSolution
) : me.plony.differentialEquations.error.Error<me.plony.differentialEquations.methods.ImprovedEulerMethod>(
    method,
    particularSolution
)