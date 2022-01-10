package ru.akhuz.ui.painting

import ru.akhuz.ui.painting.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel(private val painters: List<Painter>) : JPanel() {//массив элементов Painter vararg

    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.let { painters.forEach { p->
            p.paint(it)}}
    }
}
