package G_ProjectionMapping

import lib.BrushMapper
import org.openrndr.application
import org.openrndr.draw.loadImage
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.imageFit.imageFit
import org.openrndr.extra.noise.linear
import kotlin.math.cos

fun main() {
    application {

        configure {
            width = 1280
            height = 720
        }

        program {
            val images = listOf(
                loadImage("data/images/For coding-02.png"),
                loadImage("data/images/For coding-04.png"),
                loadImage("data/images/For coding-06.png"),
                loadImage("data/images/For coding-08.png"),
                loadImage("data/images/For coding-10.png"),
            )

            val backgrounds = listOf(
                loadImage("data/images/For coding-01.png"),
                loadImage("data/images/For coding-03.png"),
                loadImage("data/images/For coding-05.png"),
                loadImage("data/images/For coding-07.png"),
                loadImage("data/images/For coding-09.png"),
            )

            extend(Screenshots())
            val bm = extend(BrushMapper())

            var lastSwitched = 0.0

            var cycleIndex = 0
            bm.background = backgrounds[cycleIndex.mod(backgrounds.size)]

            extend {

                //    bm.targetRadius = 20.0 + linear(seconds)

                if (seconds - lastSwitched > 20.0) {
                    bm.reset(drawer)

                    lastSwitched = seconds
                    cycleIndex++
                    bm.background = backgrounds[cycleIndex.mod(backgrounds.size)]

                }

                drawer.imageFit(images[cycleIndex.mod(images.size)] , drawer.bounds)

            }
        }
    }
}