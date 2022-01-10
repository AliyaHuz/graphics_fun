package ru.akhuz

import ru.akhuz.ui.painting.MainFrame

fun main() {
    val wnd = MainFrame()
    wnd.isVisible = true
    MainFrame().apply { isVisible = true }
}

