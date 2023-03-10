data class MinesweeperBoard(val InputBoard:List<String>) {

    var matrix: Array<IntArray>
    var r = InputBoard.size
    var c = 0;
    init {
        if(r>0)
            c = InputBoard[0].length
        matrix = Array(r+2) { IntArray(c+2) {0}}
        println("Size of gameboard $r x $c")
    }

    fun withNumbers(): List<String> {
        //Fill the matrix
        for (indexI in 0 until r) {
            for ((indexJ,valueJ) in InputBoard[indexI].withIndex()) {
                if (valueJ.equals('*'))
                    matrix[indexI+1][indexJ+1] = -9
            }
        }
        checkAndUpdate(matrix)


        val output: MutableList<String> = mutableListOf()
        var tmp:String
        for (i in 1 .. r) {
            tmp=""
            for (j in 1..c) {
                if(matrix[i][j]<0)
                    tmp += "*"
                else if(matrix[i][j]==0)
                    tmp += " "
                else
                    tmp += matrix[i][j]
            }
            output.add(tmp)
        }

        return output

    }

    fun checkAndUpdate(matrix : Array<IntArray>){

        for (i in 1 .. r) {
            for (j in 1 .. c) {
                if(matrix[i][j]<0) {
                    matrix[i - 1][j]++
                    matrix[i - 1][j - 1]++
                    matrix[i][j - 1]++
                    matrix[i + 1][j + 1]++
                    matrix[i + 1][j]++
                    matrix[i][j + 1]++
                    matrix[i - 1][j + 1]++
                    matrix[i + 1][j - 1]++
                }
            }
        }
    }
}
