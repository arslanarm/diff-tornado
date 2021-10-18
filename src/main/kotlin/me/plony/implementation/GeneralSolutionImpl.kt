package me.plony.implementation
import me.plony.differentialEquations.abstractions.Differentiable
import me.plony.differentialEquations.solutions.Derivative
import me.plony.differentialEquations.solutions.GeneralSolution
import me.plony.differentialEquations.solutions.ParticularSolution
import me.plony.differentialEquations.utils.ComputedConstant
import me.plony.differentialEquations.utils.Constant
import me.plony.differentialEquations.utils.Point
import me.plony.differentialEquations.utils.UnknownConstant
import kotlin.math.E
import kotlin.math.pow

class GeneralSolutionImpl : GeneralSolution(), Differentiable {
    var c1: Constant = UnknownConstant
    override val constants: List<Constant> get() = listOf(c1)

    override fun particular(points: List<Point>): ParticularSolution {
        if (c1 is ComputedConstant) return ParticularSolutionImpl(c1 as ComputedConstant)
        require(points.size == 1)
        val (x, y) = points.first()
        val c1 = ComputedConstant((1/(y - x - 2) + .25)/E.pow(4 * x)).also { c1 = it }
        return ParticularSolutionImpl(c1)
    }
    override fun derivative(): Derivative = DerivativeImpl()
}


