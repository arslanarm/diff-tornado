package me.plony.implementation
import me.plony.differentialEquations.abstractions.Differentiable
import com.example.differentialEquations.solutions.Derivative
import com.example.differentialEquations.solutions.GeneralSolution
import com.example.differentialEquations.solutions.ParticularSolution
import com.example.differentialEquations.utils.ComputedConstant
import com.example.differentialEquations.utils.Constant
import com.example.differentialEquations.utils.Point
import com.example.differentialEquations.utils.UnknownConstant
import kotlin.math.E
import kotlin.math.pow

class GeneralSolutionImpl : GeneralSolution(), me.plony.differentialEquations.abstractions.Differentiable {
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


