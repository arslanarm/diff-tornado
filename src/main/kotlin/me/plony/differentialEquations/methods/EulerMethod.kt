package me.plony.differentialEquations.methods

import com.example.differentialEquations.solutions.Derivative
import com.example.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

class EulerMethod(initial: Point, derivative: Derivative, step: Double) : me.plony.differentialEquations.methods.Method(initial, derivative, step) {
    override fun computeWithPrevious(input: Int, previous: Double): Double {
        val answer = previous + step * derivative.compute(Point(initial.x + (input - 1) * step, previous))
        answers[input] = answer
        return answer
    }
}