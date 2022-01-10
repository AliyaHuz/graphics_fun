package ru.akhuz.ui.painting.functions

import java.lang.Math.abs


object Func: Functions {
    override fun invoke(x: Double): Double {
            return abs(x)/abs(x+1)
    }
}