package ducksim

class NewDuckController(val duckPond: DuckPond, val view: DuckSimView) {
    fun createNewDuck() {

        if (duckPond.noSelectedDucks()) {
            val makeDuckDialog = MakeDuckDialog(duckPond, view)
            makeDuckDialog.setSize(300, 200)
            makeDuckDialog.isVisible = true
        } else {
            // get the selected ducks from the model
            val selectedDucks = duckPond.selectedDucks.filterNot { it is Flock }
            // filter the selected ducks by removing any flocks

            if (selectedDucks.isNotEmpty()) {
                val flock = Flock(selectedDucks)
                duckPond.addNewDuck(flock)
                view.repaint()
            }
            // if there is at least one duck after removing flocks,
            // create a new flock with the selected ducks

        }
    }
}