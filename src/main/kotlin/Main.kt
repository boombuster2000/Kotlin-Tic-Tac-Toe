fun printBoard(board: Array<Array<String>>) {
    for (y in board.indices) {
        for (x in board[y].indices) {
            print(" ${board[y][x]} |")
        }
        if (y != 2) println("\n---+---+---+---")
    }
}

fun main() {
    val board = arrayOf(
        arrayOf(" ", " ", " "),
        arrayOf(" ", " ", " "),
        arrayOf(" ", " ", " ")
    )

    printBoard(board)

}