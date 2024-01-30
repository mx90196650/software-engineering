package ducksim

import java.awt.Color

class DecoyDuck : Duck(FlyNoWay(), QuackNoWay()) {

    override val color: Color = Color.ORANGE
    override val quackText: String = ""

    override fun display() = "Decoy"
}
