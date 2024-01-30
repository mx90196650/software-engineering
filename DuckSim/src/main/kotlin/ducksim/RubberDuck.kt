package ducksim

import java.awt.Color

class RubberDuck : Duck(defaultFlyBehavior =  FlyNoWay(), defaultQuackBehavior = QuackSqueak()) {

    override val color: Color = Color.YELLOW
    override val quackText: String = "Squeak!"

    override fun display() = "Rubber"
}