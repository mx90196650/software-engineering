package ducksim

class Flock(val ducks: List<Duck>): Duck(defaultQuackBehavior = QuackNoise()) {
    override fun display(): String {
        return "Flock" + ducks.map { it.display().first() }.joinToString(":", ":")
    }

    override val capture = object : DuckMenuItem {
        override fun invoke() {
            ducks.forEach { it.doCapture() }
            doCapture()
        }
    }

    override val release
        get() = object : DuckMenuItem {
            override fun invoke() {
                ducks.forEach { it.doRelease() }
                doRelease()
            }
        }

    override val joinDSWC
        get() = object : DuckMenuItem {
            override fun invoke() {
                ducks.forEach { it.doJoinDSWC() }
                doJoinDSWC()
            }
        }

    override val quitDSWC
        get() = object : DuckMenuItem {
            override fun invoke() {
                ducks.forEach { it.doQuitDSWC() }
                doQuitDSWC()
            }
        }

}