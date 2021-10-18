package me.plony.differentialEquations.abstractions
interface Computable<T, R> {
    fun compute(input: T): R
}