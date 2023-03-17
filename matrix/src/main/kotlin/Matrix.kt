class Matrix(private val matrixAsString: String) {

    private val colSize = matrixAsString.lines().first().split(" ").size;
    private val matrixInARow : List<Int> = matrixAsString.lines().flatMap { it -> it.split(" ") }.map{it.toInt()}

    fun column(colNr: Int): List<Int> {
        return matrixInARow.filterIndexed { index, _ -> (index % colSize) + 1 == colNr }
    }

    fun row(rowNr: Int): List<Int> {
        return matrixInARow.filterIndexed { index, _ -> (index / colSize) + 1 == rowNr }
    }
}
