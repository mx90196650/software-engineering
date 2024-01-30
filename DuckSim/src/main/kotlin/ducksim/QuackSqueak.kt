package ducksim

class QuackSqueak : QuackBehavior {
    override val state = State.QUACKING
    override val quackText = "Squeak!"
}