package ru.akhuz.ui.painting.functions

import kotlin.math.sin


object ParamFunY: Functions {
    override fun invoke(t: Double): Double {
        return sin(4*t)
    }
}