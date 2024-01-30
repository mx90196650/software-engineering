package ducksim

class MoonBling(aDuck: Duck): Bling(aDuck) {
    override fun display(): String = aDuck.display() + ":)"
}