package ducksim

class CrossBling(aDuck: Duck): Bling(aDuck) {
    override fun display(): String = aDuck.display() + ":+"
}