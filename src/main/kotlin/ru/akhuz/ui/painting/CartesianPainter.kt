package ru.akhuz.ui.painting

import java.awt.*

class CartesianPainter(private val plane: CartesianPlane): Painter
{
    init {

    }
    var maxTickColor: Color = Color.RED
    var avTickColor: Color = Color.BLUE
    var minTickColor: Color = Color.GRAY
    var axesColor: Color = Color.GRAY
    var tSizeMin = 2
    var tSizeAv = 4
    var tSizeMax = 6
    override fun paint(g: Graphics){
        paintAxes(g) //в него переносим старый код пэйнтера
        //paintTickX(g)
        //paintTickY(g)
        //paintLabelsX(g)
        //paintLabelsY(g)
    }

    private fun paintTickX(g: Graphics){
        with (plane){(g as Graphics2D).apply {
            stroke=BasicStroke(2F)
            var i = xMin
            var k = Math.round(width/(60*(xMax-xMin))).toDouble()
            /*color = minTickColor


            while (i<xMax){
                i+=(0.1*k)
                i = (Math.round(i*10)).toDouble()/10
                drawLine(xCtr2Scr(i), yCtr2Scr(0.0) - tSizeMin, xCtr2Scr(i), yCtr2Scr(0.0) + tSizeMin)
            }
            color = avTickColor
            i = Math.round(xMin).toDouble()
            if (i-0.5*k>=xMin){
                drawLine(xCtr2Scr(i-0.5*k), yCtr2Scr(0.0) - tSizeAv, xCtr2Scr(i-0.5*k), yCtr2Scr(0.0) + tSizeAv)
            }
            while (i<xMax){
                i+=(0.5*k)
                i = (Math.round(i*10)).toDouble()/10
                drawLine(xCtr2Scr(i), yCtr2Scr(0.0) - tSizeAv, xCtr2Scr(i), yCtr2Scr(0.0) + tSizeAv)
            }*/
            color = maxTickColor
            i = Math.round(xMin).toDouble()
            drawLine(xCtr2Scr(i), yCtr2Scr(0.0) - tSizeAv, xCtr2Scr(i), yCtr2Scr(0.0) + tSizeAv)
            if (i-1.0*k>=xMin){
                drawLine(xCtr2Scr(i-1.0*k), yCtr2Scr(0.0) - tSizeAv, xCtr2Scr(i-1.0*k), yCtr2Scr(0.0) + tSizeAv)
            }
            while (i<xMax){
                i+=(1.0*k)
                i = (Math.round(i*10)).toDouble()/10
                drawLine(xCtr2Scr(i), yCtr2Scr(0.0) - tSizeMax, xCtr2Scr(i), yCtr2Scr(0.0) + tSizeMax)
            }
        }}
    }

    private fun paintTickY(g: Graphics){
        with (plane){(g as Graphics2D).apply {
            stroke=BasicStroke(2F)
            //color = minTickColor
            var i = yMin
            /*while (i<yMax){
                i+=0.1
                i = (Math.round(i*10)).toDouble()/10
                drawLine(xCtr2Scr(0.0)- tSizeMin, yCtr2Scr(i), xCtr2Scr(0.0)+ tSizeMin, yCtr2Scr(i))
            }
            color = avTickColor
            i = Math.round(yMin).toDouble()
            if (i-0.5>=yMin){
                drawLine(xCtr2Scr(0.0)-tSizeAv, yCtr2Scr(i-0.5), xCtr2Scr(i) + tSizeAv, yCtr2Scr(i-0.5))
            }
            while (i<yMax){
                i+=0.5
                i = (Math.round(i*10)).toDouble()/10
                drawLine(xCtr2Scr(0.0)- tSizeAv, yCtr2Scr(i), xCtr2Scr(0.0)+ tSizeAv, yCtr2Scr(i))
            }*/
            color = maxTickColor
            i = Math.round(yMin).toDouble()
            drawLine(xCtr2Scr(0.0) - tSizeAv, yCtr2Scr(i), xCtr2Scr(0.0) + tSizeAv, yCtr2Scr(i))
            if (i-1.0>=yMin){
                drawLine(xCtr2Scr(0.0)-tSizeAv, yCtr2Scr(i-0.5), xCtr2Scr(i) + tSizeAv, yCtr2Scr(i-0.5))
            }
            while (i<yMax){
                i+=1.0
                i = (Math.round(i*10)).toDouble()/10
                drawLine(xCtr2Scr(0.0)- tSizeMax, yCtr2Scr(i), xCtr2Scr(0.0)+ tSizeMax, yCtr2Scr(i))
            }
        }}
    }

    private fun paintLabelsX(g: Graphics){
        with (plane){
            with (g as Graphics2D){
                stroke=BasicStroke(1F)
                font = Font("Cambria", Font.PLAIN, 12)
                var oneTick = 1.0
                while (oneTick<xMax){
                    val (tW, tH) =fontMetrics.getStringBounds(oneTick.toString(), g).run{
                        Pair(width.toFloat(), height.toFloat())
                    }
                    drawString(oneTick.toString(), xCtr2Scr(oneTick)-tW/2F, yCtr2Scr(0.0)+tH+tSizeMax)//левый нижний угол вместо верхнего
                    oneTick+=1.0
                }
                oneTick=-1.0
                while (oneTick>xMin){
                    val (tW, tH) =fontMetrics.getStringBounds(oneTick.toString(), g).run{
                        Pair(width.toFloat(), height.toFloat())
                    }
                    drawString(oneTick.toString(), xCtr2Scr(oneTick)-tW/2F, yCtr2Scr(0.0)+tH+tSizeMax)//левый нижний угол вместо верхнего
                    oneTick-=1.0
                }
                oneTick=0.0
                val (tW, tH) =fontMetrics.getStringBounds(oneTick.toString(), g).run{
                    Pair(width.toFloat(), height.toFloat())
                }
                drawString(oneTick.toString(), xCtr2Scr(oneTick).toFloat(), yCtr2Scr(0.0)+tH+tSizeMax)
            }
        }}


    private fun paintLabelsY(g: Graphics){
        var oneTick = 1.0
        with (plane){
            with (g as Graphics2D){
                stroke=BasicStroke(1F)
                font = Font("Cambria", Font.PLAIN, 12)
                while (oneTick<yMax){
                    val (tW, tH) =fontMetrics.getStringBounds(oneTick.toString(), g).run{
                        Pair(width.toFloat(), height.toFloat())
                    }
                    drawString(oneTick.toString(), xCtr2Scr(0.0)+tW/2F,yCtr2Scr(oneTick)+tH/4F )//левый нижний угол вместо верхнего
                    oneTick+=1.0
                }
                oneTick=-1.0
                while (oneTick>yMin){
                    val (tW, tH) =fontMetrics.getStringBounds(oneTick.toString(), g).run{
                        Pair(width.toFloat(), height.toFloat())
                    }
                    drawString(oneTick.toString(), xCtr2Scr(0.0)+tW/2F,yCtr2Scr(oneTick)+tH/4F)//левый нижний угол вместо верхнего
                    oneTick-=1.0
                }
            }
        }
    }

    private fun paintAxes(g: Graphics) {
        with(plane) { (g as Graphics2D).apply {
            stroke = BasicStroke(3F)
            color = axesColor
            //if (ymin <= 0 && ymax >= 0) ??
            if (xMin > 0 || xMax <0){
                drawLine(width, 0,width,height)
                drawLine(0,0,0,height)
            }else {
                drawLine(xCtr2Scr(0.0), 0, xCtr2Scr(0.0), height)//верт ось
            }
            if (yMin > 0 || yMax <0){
                drawLine(0, height,width,height)
                drawLine(0,0,width,0)
            }else{
                drawLine(0, yCtr2Scr(0.0), width, yCtr2Scr(0.0))//коорд начала отрезка и конца отрезка, горизонт ось
            }
        }}
    }

}