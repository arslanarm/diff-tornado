package me.plony.differentialEquations.methods

import com.example.differentialEquations.solutions.Derivative
import com.example.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

class ImprovedEulerMethod(initial: Point, derivative: Derivative, step: Double, val eulerMethod: me.plony.differentialEquations.methods.EulerMethod = me.plony.differentialEquations.methods.EulerMethod(
    initial,
    derivative,
    step
)
) : me.plony.differentialEquations.methods.Method(initial, derivative, step) {

    override fun computeWithPrevious(input: Int, previous: Double): Double {
        val yApproximate = eulerMethod.compute(input)
        val answer = previous + (step / 2) * (derivative.compute(Point(initial.x + (input - 1) * step, previous)) + derivative.compute(Point(initial.x + input * step, yApproximate)))
        answers[input] = answer
        return answer
    }
}