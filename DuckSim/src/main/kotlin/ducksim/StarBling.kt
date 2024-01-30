package ducksim

class StarBling(aDuck: Duck): Bling(aDuck) {
    override fun display(): String = aDuck.display() + ":*"
}