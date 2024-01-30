package ducksim

import java.awt.Color

class GooseDock(val goose: Goose) : Duck(defaultQuackBehavior = QuackHonk()) {
    override val color: Color
        get() = Color.BLUE

    override fun display(): String {
        return goose.name
    }
}