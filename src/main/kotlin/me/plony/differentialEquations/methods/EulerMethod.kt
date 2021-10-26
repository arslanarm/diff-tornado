package me.plony.differentialEquations.methods

import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.solutions.ParticularSolution
import me.plony.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

class EulerMethod(particularSolution: ParticularSolution, initial: Point, derivative: Derivative, step: Double) : Method(particularSolution, initial, derivative, step) {
    override fun computeWithPrevious(
        input: Int,
        previous: Double
    ): Double =
        previous + step * derivative.compute(Point(initial.x + (input - 1) * step, previous))
}