package com.lanstar.game1a2b

class StageRecord(var ans: String, var guessRecords: ArrayList<Triple<Int, Int, String>>) {
}

class GameRecord(var score: Int, var stageRecords: Array<StageRecord>) {

}

object  GameManager {
    const val SCORE_PER_WIN = 25
    const val LENGTH = 4
    const val ROUNDS = 4
    const val S = "0123456789"

    val histories = arrayListOf<GameRecord>()
    var selectedGame = 0
    var selectedStage = 0
}