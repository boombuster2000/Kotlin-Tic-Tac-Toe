fun clearScreen() {
    for (i in 0..80*300) println()
}

fun printBoard(board: Array<Array<Char>>) {
    clearScreen()
    for (y in board.indices) {
        for (x in board[y].indices) {
            print(" ${board[y][x]}")
            if (x != 2) print(" |")
        }
        if (y != 2) println("\n---+---+---") else println()
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

fun isSpaceEmpty(coordinates : Map<Char, Int>, board : Array<Array<Char>>) : Boolean {
    return board[coordinates['y']!!][coordinates['x']!!] == ' '
}

fun updateBoard(player : Int, coordinates : Map<Char, Int>, board : Array<Array<Char>>) : Array<Array<Char>> {
    if (player == 1) board[coordinates['y']!!][coordinates['x']!!] = 'X'
    else if (player == 2) board[coordinates['y']!!][coordinates['x']!!] = 'O'

    return board
}

fun getWinner(board : Array<Array<Char>>) : Int {
    val symbols = arrayOf('X', 'O')
    for (symbol in symbols) {
        for (y in board.indices) {
            if (board[y][0] == symbol && board[y][0] == board[y][1] && board[y][1] == board[y][2]) return symbols.indexOf(symbol) + 1
        }
        for (y in board.indices) {
            if (board[0][y] == symbol && board[0][y] == board[1][y] && board[1][y] == board[2][y]) return symbols.indexOf(symbol) + 1
        }

        if (board[1][1] == ' ') return 0
        else if (board[1][1] == symbol && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return symbols.indexOf(symbol) + 1
        else if (board[1][1] == symbol && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return symbols.indexOf(symbol) + 1
    }

    return 0
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
        val winner = getWinner(board)

        if (round == 8 && winner == 0) {
            printBoard(board)
            print("Draw!")
            break
        }

        if (winner == 0) continue

        printBoard(board)
        println("Player $winner won!")
        break
    }

}