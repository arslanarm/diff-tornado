package me.plony.differentialEquations.utils
sealed class Constant

object UnknownConstant : Constant()
class ComputedConstant(val value: Double) : Constant()
