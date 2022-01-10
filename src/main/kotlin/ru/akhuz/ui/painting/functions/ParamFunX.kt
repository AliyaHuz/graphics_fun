package ru.akhuz.ui.painting.functions

import kotlin.math.cos

object ParamFunX: Functions {
    override fun invoke(t: Double): Double {
        return cos(4*t)
    }
}