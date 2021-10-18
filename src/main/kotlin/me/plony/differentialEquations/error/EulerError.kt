package me.plony.differentialEquations.error

import me.plony.differentialEquations.methods.EulerMethod
import com.example.differentialEquations.solutions.ParticularSolution
import kotlin.math.abs

class EulerError(
    method: me.plony.differentialEquations.methods.EulerMethod,
    particularSolution: ParticularSolution
) : me.plony.differentialEquations.error.Error<me.plony.differentialEquations.methods.EulerMethod>(
    method,
    particularSolution
)