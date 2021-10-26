package me.plony.differentialEquations.methods

import me.plony.differentialEquations.abstractions.Computable
import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.solutions.ParticularSolution
import me.plony.differentialEquations.utils.Point
import java.util.concurrent.ConcurrentHashMap

abstract class Method(
    val particularSolution: ParticularSolution,
    val initial: Point,
    val derivative: Derivative,
    val step: Double
):
    Computable<Int, Double> {
    val answers = ConcurrentHashMap<Int, Double>().apply {
        put(0, initial.y)
    } // because compute is recursive, to cache already known answers
    override fun compute(input: Int): Double {
        require(input >= 0)
        if (answers[input] != null) return answers[input]!!
        if (initial.x + input * step in particularSolution.pointsOfDiscontinuity) return particularSolution.compute(input, step, initial)
        val answer = computeWithPrevious(input, answers[input - 1] ?: compute(input - 1).also { answers[input - 1] = it })
        answers[input] = answer
        return answer
    }
    abstract fun computeWithPrevious(input: Int, previous: Double): Double
}