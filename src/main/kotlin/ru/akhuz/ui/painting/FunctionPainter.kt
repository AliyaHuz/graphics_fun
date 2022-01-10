package ru.akhuz.ui.painting
import ru.akhuz.ui.painting.functions.Functions
import java.awt.*

class FunctionPainter(val plane: CartesianPlane, val func: Functions, fcolor:Color) : Painter {

    var funColor: Color= fcolor
    override fun paint(g :Graphics){
        with(g as Graphics2D){
            color = funColor
            stroke = BasicStroke(4F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
            val rh = mapOf(
                RenderingHints.KEY_ANTIALIASING to RenderingHints.VALUE_ANTIALIAS_ON,//сгдаживание включено(на границах линии присутствуют промежуточные цвета)
                RenderingHints.KEY_INTERPOLATION to RenderingHints.VALUE_INTERPOLATION_BICUBIC,
                RenderingHints.KEY_RENDERING to RenderingHints.VALUE_RENDER_QUALITY,
                RenderingHints.KEY_DITHERING to RenderingHints.VALUE_DITHER_ENABLE
            )
            setRenderingHints(rh)
            with(plane) {
                for (i in 0 until width){
                    drawLine(i, yCtr2Scr(func.invoke(xScr2Crt(i))),i+1,yCtr2Scr(func.invoke(xScr2Crt(i+1))))
                }
            }}
    }
}