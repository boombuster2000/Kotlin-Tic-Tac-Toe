fun clearScreen() {
    for (i in 0..80*300) println()
}

fun printBoard(board: Array<Array<Char>>) {
    clearScreen()
    for (y in board.indices) {
        for (x in board[y].indices) {
            print(" ${board[y][x]} |")
        }
        if (y != 2) println("\n---+---+---+---") else println()
    }
}

fun getPlayerInput() : Map<Char, Int> {
    while (true) {
        print(">> ")
        val playerMoveUnformatted = readln()
        try {
            if (playerMoveUnformatted.length != 3 || playerMoveUnformatted[1] != ' ') {
                println("Input should be (x y)")
                continue
            }

            if ( playerMoveUnformatted[0].digitToInt() !in 1..3 || playerMoveUnformatted[2].digitToInt() !in 1..3) {
                println("X and Y is between 1 and 3 inclusive")
                continue
            }
            return mapOf('y' to playerMoveUnformatted[0].digitToInt()-1, 'x' to playerMoveUnformatted[2].digitToInt()-1)

        } catch (error: java.lang.IllegalArgumentException) {
            println("Input should be numbers")
        } catch (error: StringIndexOutOfBoundsException) {
            println("Input should be (x y)")
        }
    }

}

fun isSpaceEmpty(coordinates : Map<Char, Int>, board: Array<Array<Char>>) : Boolean {
    return board[coordinates['y']!!][coordinates['x']!!] == ' '
}

fun updateBoard(player : Int, coordinates : Map<Char, Int>, board: Array<Array<Char>>) : Array<Array<Char>> {
    if (player == 1) board[coordinates['y']!!][coordinates['x']!!] = 'X'
    else if (player == 2) board[coordinates['y']!!][coordinates['x']!!] = 'O'

    return board
}

fun main() {
    var board = arrayOf(
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', ' '),
        arrayOf(' ', ' ', ' ')
    )

    for (round in 0..8) {
        val player = (round % 2) + 1

        printBoard(board)
        val playerMoveCoordinates: Map<Char, Int>

        while (true) {
            val playerMoveCoordinatesUnvalidated = getPlayerInput()
            if (!isSpaceEmpty(playerMoveCoordinatesUnvalidated, board)) continue
            playerMoveCoordinates = playerMoveCoordinatesUnvalidated
            break
        }

        board = updateBoard(player, playerMoveCoordinates, board)
    }
}