package me.plony.differentialEquations.methods

import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.solutions.ParticularSolution
import me.plony.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

class ImprovedEulerMethod(
    particularSolution: ParticularSolution,
    initial: Point,
    derivative: Derivative,
    step: Double,
    val eulerMethod: EulerMethod = EulerMethod(
        particularSolution,
        initial,
        derivative,
        step
    )
) : Method(particularSolution, initial, derivative, step) {

    override fun computeWithPrevious(input: Int, previous: Double): Double {
        val yApproximate = eulerMethod.compute(input)
        return previous + (step / 2) * (
                derivative.compute(Point(initial.x + (input - 1) * step, previous)) +
                derivative.compute(Point(initial.x + input * step, yApproximate))
                )

    }
}