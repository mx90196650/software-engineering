package ducksim

import java.awt.Color

abstract class Bling(val aDuck: Duck) : Duck(aDuck.defaultFlyBehavior,
                                            aDuck.defaultQuackBehavior) {
    override val color: Color
            get() = aDuck.color
}