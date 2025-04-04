fun check(name: String, result: Boolean, correct: Boolean) {
    if (result == correct) {
        println("success - $name")
    } else {
        println("failed - $name")
    }
}

fun isValidSudoku(board: List<List<Any>>): Boolean {

    val size = board.size
    val maxNumberOFColumns = board.maxOf { it.size }
    val minNumberOFColumns = board.minOf { it.size }
    if (size == 0 || maxNumberOFColumns != minNumberOFColumns || (size != maxNumberOFColumns && size != minNumberOFColumns)) {
        return false
    }

    val boardSize = (size + '0'.code).toChar()
    for (row in 0 until size) {
        for (col in 0 until size) {
            val cell = board[row][col]
            if (cell != '-' && cell !in '1'..boardSize) return false
        }
    }

    val seenRows = List(size) { mutableSetOf<Any>() }
    val seenColumns = List(size) { mutableSetOf<Any>() }

    for (row in 0 until size) {
        for (col in 0 until size) {
            val cell = board[row][col]
            if (cell != '-') {
                if (seenRows[row].contains(cell)) return false
                else seenRows[row].add(cell)
                if (seenColumns[col].contains(cell)) return false
                else seenColumns[col].add(cell)
            }
        }
    }

    val subgridSize = Math.sqrt(size.toDouble()).toInt()
    for (row in 0 until size step subgridSize) {
        for (col in 0 until size step subgridSize) {
            val seenCells = mutableSetOf<Any>()
            for (i in 0 until subgridSize) {
                for (j in 0 until subgridSize) {
                    val cell = board[row + i][col + j]
                    if (cell != '-' && seenCells.contains(cell)) return false
                    else seenCells.add(cell)
                }
            }
        }
    }
    return true
}

fun main() {

    check(
        name = "should return true when valid board",
        result = isValidSudoku(
            listOf(
                listOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
                listOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
                listOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
                listOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
                listOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
                listOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
                listOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
                listOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
                listOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
            )
        ),
        correct = true
    )

    check(
        name = "should return false when invalid board size",
        result = isValidSudoku(
            listOf(
                listOf('5', '3', '-'),
                listOf('6', '-', '-'),
                listOf('-', '9', '8'),
                listOf('8', '-', '-')
            )
        ),
        correct = false
    )

    check(
        name = "should return false invalid range",
        result = isValidSudoku(
            listOf(
                listOf('0', '3', '-', '-', '7', '-', '-', '-', '-'),//0
                listOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
                listOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
                listOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
                listOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
                listOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
                listOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
                listOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
                listOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
            )
        ),
        correct = false
    )
    check(
        name = "should return false when invalid negative numbers",
        result = isValidSudoku(
            listOf(
                listOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
                listOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
                listOf('-', "-9", '8', '-', '-', '-', '-', '6', '-'),//-9
                listOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
                listOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
                listOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
                listOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
                listOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
                listOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
            )
        ),
        correct = false
    )
    check(
        name = "should return false when non-numeric value",
        result = isValidSudoku(
            listOf(
                listOf('@', '3', '-', '-', '7', '-', '-', '-', '-'),//@
                listOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
                listOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
                listOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
                listOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
                listOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
                listOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
                listOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
                listOf('-', '-', '-', '-', '8', '-', '-', '7', '9')

            )
        ),
        correct = false
    )

    check(
        name = "should return false when board with duplicate row entries",
        result = isValidSudoku(
            listOf(
                listOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
                listOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
                listOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
                listOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
                listOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
                listOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
                listOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
                listOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
                listOf('-', '-', '-', '-', '8', '-', '-', '9', '9')

            )
        ),
        correct = false
    )

    check(
        name = "should return false when board with duplicate in column",
        result = isValidSudoku(
            listOf(
                listOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
                listOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
                listOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
                listOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
                listOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
                listOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
                listOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
                listOf('-', '-', '-', '4', '1', '9', '-', '-', '9'),
                listOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
            )
        ),
        correct = false
    )

    check(
        name = "should return false when invalid subgrid",
        result = isValidSudoku(
            listOf(
                listOf('5', '3', '_', '-', '7', '-', '-', '-', '-'),
                listOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
                listOf('-', '9', '5', '-', '-', '-', '-', '6', '-'),
                listOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
                listOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
                listOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
                listOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
                listOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
                listOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
            )
        ),
        correct = false
    )

}
