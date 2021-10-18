package me.plony.differentialEquations.methods

import me.plony.differentialEquations.abstractions.Computable
import com.example.differentialEquations.solutions.Derivative
import com.example.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

abstract class Method(val initial: Point, val derivative: Derivative, val step: Double):
    me.plony.differentialEquations.abstractions.Computable<Int, Double> {
    val answers = ConcurrentHashMap<Int, Double>().apply {
        put(0, initial.y)
    }
    override fun compute(input: Int): Double {
        require(input >= 0)
        if (answers[input] != null) return answers[input]!!
        return computeWithPrevious(input, answers[input - 1] ?: compute(input - 1).also { answers[input - 1] = it })
    }
    abstract fun computeWithPrevious(input: Int, previous: Double): Double
}