package me.plony.differentialEquations.methods

import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

class EulerMethod(initial: Point, derivative: Derivative, step: Double) : Method(initial, derivative, step) {
    override fun computeWithPrevious(input: Int, previous: Double): Double {
        val answer = previous + step * derivative.compute(Point(initial.x + (input - 1) * step, previous))
        answers[input] = answer
        return answer
    }
}