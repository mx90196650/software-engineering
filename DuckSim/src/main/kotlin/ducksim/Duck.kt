package ducksim

import java.awt.Color

abstract class Duck(val defaultFlyBehavior: FlyBehavior = FlyWithWings(),
                    val defaultQuackBehavior: QuackBehavior = QuackNormal()) : Observer {

    var flyBehavior: FlyBehavior = defaultFlyBehavior
    var quackBehavior: QuackBehavior = defaultQuackBehavior

    // values that can be overridden

    open val color: Color = Color.BLACK
    open val quackText: String = "Quack!"

    // variables that can be changed only by functions in this class

    var state = State.SWIMMING
        private set
    var isFree = true
        private set
    var isOnDSWC = false
        private set

    // function for setting the state back to its default (swimming)

    fun swim() {
        state = State.SWIMMING
    }

    // functions from the context menu

    open fun fly() {
        state = flyBehavior.state
    }

    open fun quack() {
        state = quackBehavior.state
    }

    open val joinDSWC = object: DuckMenuItem {
        override fun invoke() {
            doJoinDSWC()
        }
    }

    fun doJoinDSWC() {
        DuckFactory.registerObserver(this)
        isOnDSWC = true
    }

    open val quitDSWC = object: DuckMenuItem {
        override fun invoke() {
            doQuitDSWC()
        }
    }

    fun doQuitDSWC() {
        DuckFactory.removeObserver(this)
        isOnDSWC = false
    }

    open val capture = object: DuckMenuItem {
        override fun invoke() {
            doCapture()
        }
    }

    fun doCapture() {
        isFree = false
        flyBehavior = FlyNoWay()
        quackBehavior = QuackNoWay()
    }

    open val release = object: DuckMenuItem {
        override fun invoke() {
            doRelease()
        }
    }

    fun doRelease() {
        isFree = true
        flyBehavior = defaultFlyBehavior
        quackBehavior = defaultQuackBehavior
    }


    // abstract display function that must be implemented by concrete classes

    abstract fun display(): String

    override fun welcome() {
        state = State.WELCOMING
    }
}
