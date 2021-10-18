package me.plony.differentialEquations.methods

import com.example.differentialEquations.solutions.Derivative
import com.example.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

class RungeKuttaMethod(initial: Point, derivative: Derivative, step: Double) : me.plony.differentialEquations.methods.Method(initial, derivative, step) {
    override fun computeWithPrevious(input: Int, previous: Double): Double {
        val x = initial.x + step * (input - 1)
        val k1 = derivative.compute(Point(x, previous))
        val k2 = derivative.compute(Point(x + step / 2, previous + step / 2 * k1))
        val k3 = derivative.compute(Point(x + step / 2, previous + step / 2 * k2))
        val k4 = derivative.compute(Point(x + step, previous + step * k3))
        val answer = previous + (step / 6) * (k1 + 2 * k2 + 2 * k3 + k4)
        answers[input] = answer
        return answer
    }
}