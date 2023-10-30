fun printBoard(board: Array<Array<Char>>) {
    for (y in board.indices) {
        for (x in board[y].indices) {
            print(" ${board[y][x]} |")
        }
        if (y != 2) println("\n---+---+---+---") else println()
    }
}

fun getPlayerInput() : Map<String, Int> {
    while (true) {
        print(">> ")
        val playerMoveUnformatted = readln()
        try {
            if (playerMoveUnformatted.length != 3 || playerMoveUnformatted[1] != ' ') {
                println("Input should be (x y)")
                continue
            }

            return mapOf("x" to playerMoveUnformatted[0].digitToInt(), "y" to playerMoveUnformatted[2].digitToInt())

        } catch (error: java.lang.IllegalArgumentException) {
            println("Input should be numbers")
        } catch (error: StringIndexOutOfBoundsException) {
            println("Input should be (x y)")
        }
    }

}

fun isSpaceEmpty(coordinates : Map<String, Int>, board: Array<Array<Char>>) : Boolean {
    return board[coordinates["y"]!!][coordinates["x"]!!] == ' '
}

fun main() {
    val board = arrayOf(
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', ' ')
    )

    printBoard(board)
    while (true) {
        val playerMoveCoordinates = getPlayerInput()
        if (!isSpaceEmpty(playerMoveCoordinates, board)) continue
        break
    }
}