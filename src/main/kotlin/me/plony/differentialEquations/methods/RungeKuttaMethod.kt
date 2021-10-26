package me.plony.differentialEquations.methods

import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.solutions.ParticularSolution
import me.plony.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

class RungeKuttaMethod(particularSolution: ParticularSolution, initial: Point, derivative: Derivative, step: Double) : Method(particularSolution, initial, derivative, step) {
    override fun computeWithPrevious(input: Int, previous: Double): Double {
        val x = initial.x + step * (input - 1)
        val k1 = derivative.compute(Point(x, previous))
        val k2 = derivative.compute(Point(x + step / 2, previous + step / 2 * k1))
        val k3 = derivative.compute(Point(x + step / 2, previous + step / 2 * k2))
        val k4 = derivative.compute(Point(x + step, previous + step * k3))
        return previous + (step / 6) * (k1 + 2 * k2 + 2 * k3 + k4)
    }
}