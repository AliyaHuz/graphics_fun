package ru.akhuz.ui.painting

import ru.akhuz.ui.painting.functions.Func
import ru.akhuz.ui.painting.functions.ParamFunX
import ru.akhuz.ui.painting.functions.ParamFunY
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.*

class MainFrame: JFrame() {
    val mpan: GraphicsPanel
    val ppan1: GraphicsPanel
    val ppan2: GraphicsPanel
    val lbl_xmin: JLabel
    val lbl_xmax: JLabel
    val lbl_ymin: JLabel
    val lbl_ymax: JLabel
    val lbl_tmin: JLabel
    val lbl_tmax: JLabel
    val spn_xmin: JSpinner
    val spn_xmax: JSpinner
    val spn_ymin: JSpinner
    val spn_ymax: JSpinner
    val spn_tmin: JSpinner
    val spn_tmax: JSpinner
    val xminM: SpinnerNumberModel
    val xmaxM: SpinnerNumberModel
    val yminM: SpinnerNumberModel
    val ymaxM: SpinnerNumberModel
    val tminM: SpinnerNumberModel
    val tmaxM: SpinnerNumberModel

    init{
        minimumSize = Dimension(1200,700)
        defaultCloseOperation = EXIT_ON_CLOSE
        lbl_xmin = JLabel().apply{ text = "Xmin"}
        lbl_xmax = JLabel().apply { text = "Xmax" }
        lbl_ymin = JLabel().apply { text = "Ymin" }
        lbl_ymax = JLabel().apply { text = "Ymax" }
        lbl_tmin = JLabel().apply { text = "Tmin" }
        lbl_tmax = JLabel().apply { text = "Tmax" }
        xminM = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        spn_xmin = JSpinner(xminM)
        xmaxM = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        spn_xmax = JSpinner(xmaxM)
        yminM = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        spn_ymin = JSpinner(yminM)
        ymaxM = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        spn_ymax = JSpinner(ymaxM)
        tminM = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        spn_tmin = JSpinner(tminM)
        tmaxM = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        spn_tmax = JSpinner(tmaxM)

        val plane1 = CartesianPlane(
            spn_xmin.value as Double,
            spn_xmax.value as Double,
            spn_ymin.value as Double,
            spn_ymax.value as Double
        )
        val plane2 = CartesianPlane(
            spn_tmin.value as Double,
            spn_tmax.value as Double,
            spn_xmin.value as Double,
            spn_xmax.value as Double
        )
        val plane3 = CartesianPlane(
            spn_tmin.value as Double,
            spn_tmax.value as Double,
            spn_ymin.value as Double,
            spn_ymax.value as Double
        )

        val cartesianPainter1 = CartesianPainter(plane1)
        val funPainter1 = FunctionPainter(plane1, Func, Color.BLUE)
        val painters1 = mutableListOf(cartesianPainter1, funPainter1)
        val cartesianPainter2 = CartesianPainter(plane2)
        val funPainter2 = FunctionPainter(plane2, ParamFunX, Color.RED)
        val painters2 = mutableListOf(cartesianPainter2, funPainter2)
        val cartesianPainter3 = CartesianPainter(plane3)
        val funPainter3 = FunctionPainter(plane3, ParamFunY, Color.GREEN)
        val painters3 = mutableListOf(cartesianPainter3, funPainter3)


        mpan = GraphicsPanel(painters1).apply{ background = Color.WHITE}
        ppan1 = GraphicsPanel(painters2).apply{ background = Color.WHITE}
        ppan2 = GraphicsPanel(painters3).apply{ background = Color.WHITE}

        mpan.addComponentListener(object: ComponentAdapter(){
            override fun componentResized(e: ComponentEvent?) {
                plane1.width = mpan.width
                plane1.height = mpan.height
                mpan.repaint()
            }
        })
        ppan1.addComponentListener(object: ComponentAdapter(){
            override fun componentResized(e: ComponentEvent?) {
                plane2.width = ppan1.width
                plane2.height = ppan1.height
                ppan1.repaint()
            }
        })
        ppan2.addComponentListener(object: ComponentAdapter(){
            override fun componentResized(e: ComponentEvent?) {
                plane3.width = ppan2.width
                plane3.height = ppan2.height
                ppan2.repaint()
            }
        })
        spn_xmin.addChangeListener{
            xmaxM.minimum = spn_xmin.value as Double + 0.1
            plane1.xSegment = Pair(spn_xmin.value as Double, spn_xmax.value as Double)
            mpan.repaint()
        }
        spn_xmax.addChangeListener{
            xminM.maximum = spn_xmax.value as Double - 0.1
            plane1.xSegment = Pair(spn_xmin.value as Double, spn_xmax.value as Double)
            mpan.repaint()
        }
        spn_ymin.addChangeListener{
            ymaxM.minimum = spn_ymin.value as Double + 0.1
            plane1.ySegment = Pair(spn_ymin.value as Double, spn_ymax.value as Double)
            mpan.repaint()
        }
        spn_ymax.addChangeListener{
            yminM.maximum = spn_ymax.value as Double - 0.1
            plane1.ySegment = Pair(spn_ymin.value as Double, spn_ymax.value as Double)
            mpan.repaint()
        }
        spn_tmin.addChangeListener{
            tmaxM.minimum = spn_tmin.value as Double + 0.1
            plane2.xSegment = Pair(spn_tmin.value as Double, spn_tmax.value as Double)
            plane3.xSegment = Pair(spn_tmin.value as Double, spn_tmax.value as Double)
            ppan1.repaint()
            ppan2.repaint()
        }
        spn_tmax.addChangeListener{
            tminM.maximum = spn_tmax.value as Double - 0.1
            plane2.xSegment = Pair(spn_tmin.value as Double, spn_tmax.value as Double)
            plane3.xSegment = Pair(spn_tmin.value as Double, spn_tmax.value as Double)
            ppan1.repaint()
            ppan2.repaint()
        }
        layout = GroupLayout(contentPane).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(8)
                    .addComponent(mpan,600,600,600)
                    .addGap(8)
                    .addGroup(createParallelGroup()
                        .addComponent(ppan1, 300,300,300)
                        .addComponent(ppan2, 300,300,300)
                    )
                    .addGap(8)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(lbl_xmin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_xmax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ymin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ymax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_tmin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_tmax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                    )
                    .addGroup(
                        createParallelGroup()
                            .addComponent(spn_xmin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(spn_xmax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(spn_ymin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(spn_ymax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(spn_tmin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(spn_tmax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                    )
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(8)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(mpan, 600,600,600)
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(ppan1, 300,300,300)
                                    .addGap(4)
                                    .addComponent(ppan2, 300,300,300)
                            )
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(lbl_xmin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(4)
                                    .addComponent(lbl_xmax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(4)
                                    .addComponent(lbl_ymin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(7)
                                    .addComponent(lbl_ymax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(4)
                                    .addComponent(lbl_tmin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(4)
                                    .addComponent(lbl_tmax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(spn_xmin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(1)
                                    .addComponent(spn_xmax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(1)
                                    .addComponent(spn_ymin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(1)
                                    .addComponent(spn_ymax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(1)
                                    .addComponent(spn_tmin, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                    .addGap(1)
                                    .addComponent(spn_tmax, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                            )
                    )
                    .addGap(8)
            )
        }
        pack()
        plane1.width = mpan.width
        plane1.height = mpan.height
        plane2.width = ppan1.width
        plane2.height = ppan1.height
        plane3.width = ppan2.width
        plane3.height = ppan2.height
    }
}