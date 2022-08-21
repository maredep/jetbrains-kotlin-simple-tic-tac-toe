package tictactoe

fun main() {
    var user = 0
    // Prints an empty grid at the beginning of the game.
    val n = "         "
    val charArray = n.toCharArray()
    var resultList = createMutableList(charArray)
    showGrid(resultList)
    while(true){
        val input = readLine()!!.split(" ")
        var winner = " "
        var playerCurrent = user % 2
        when {
            !isNumeric(input) -> println("You should enter numbers!")
            input[0].toInt() !in 1..3 || input[1].toInt() !in 1..3 -> println("Coordinates should be from 1 to 3!")
            resultList[input[0].toInt() - 1][input[1].toInt()] == " " -> {
                when (playerCurrent) {
                    0 -> {
                        resultList[input[0].toInt() - 1][input[1].toInt()] = "X"
                        showGrid(resultList)
                        user++
                    }
                    1 -> {
                        resultList[input[0].toInt() - 1][input[1].toInt()] = "O"
                        showGrid(resultList)
                        user++
                    }
                }
            }
        resultList[input[0].toInt() - 1][input[1].toInt()] == "X" -> println("This cell is occupied! Choose another one!")
        resultList[input[0].toInt() - 1][input[1].toInt()] == "O" -> println("This cell is occupied! Choose another one!") 
        }
        winner = gameStatus(resultList)
        if (winner == "X" || winner == "O") {
            println("$winner wins")
            break
        } 
        if (winner == " " && user == 9) {
            println("Draw")
                break
        }
    }
}

fun createMutableList(charArray: CharArray): MutableList<MutableList<String>> {
    val resultList = mutableListOf(
        mutableListOf("|", charArray[0].toString(), charArray[1].toString(), charArray[2].toString(), "|"),
        mutableListOf("|", charArray[3].toString(), charArray[4].toString(), charArray[5].toString(), "|"),
        mutableListOf("|", charArray[6].toString(), charArray[7].toString(), charArray[8].toString(), "|"),
    )
    return resultList
}

fun showGrid(resultList: MutableList<MutableList<String>>) {
    // Show grid result
    println("---------")
    for (i in 0 until resultList.size) {
        println(resultList[i].joinToString(" "))
    }
    println("---------")
}

fun isNumeric(inputCheck: List<String>): Boolean {
    var i = 0
    var check = true
    while (i < inputCheck.size) {
        if (inputCheck[i].toInt() == null) {
            check = false
            break
        }

        i++
        if(i == inputCheck.size - 1) {
            check = true
        }
    }

    return check
}

fun gameStatus(resultList: MutableList<MutableList<String>>): String {
    var winner = " " 
    for(i in 0..2) {
        if(resultList[i][1] == resultList[i][2] && resultList[i][1] == resultList[i][3] && resultList[i][2] == resultList[i][3]) {
            winner = resultList[i][1]
        }
        if(resultList[0][i + 1] == resultList[1][i + 1] && resultList[0][i + 1] == resultList[2][i + 1] && resultList[1][i + 1] == resultList[2][i + 1]) {
            winner = resultList[0][i + 1]
        }
    }
    if(resultList[0][1] == resultList[1][2] && resultList[1][2] == resultList[2][3] && resultList[0][1] == resultList[2][3]){
        winner = resultList[0][1]
    }
    if(resultList[0][3] == resultList[1][2] && resultList[1][2] == resultList[2][1] && resultList[0][3] == resultList[2][1]){
        winner = resultList[0][3]
    }
    return winner
}  
