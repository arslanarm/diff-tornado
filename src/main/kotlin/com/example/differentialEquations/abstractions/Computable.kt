package com.example.differentialEquations.abstractions
interface Computable<T, R> {
    fun compute(input: T): R
}