package com.lanstar.game1a2b

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText


class GuessFragment : Fragment() {

    private lateinit var textScore: TextView
    private lateinit var textStage: TextView
    private lateinit var textAttempt: TextView
    private lateinit var inputGuess: TextInputEditText
    private lateinit var btnGuess: Button
    private lateinit var guessResult: LinearLayout

    private var stage = 0
    private var attempts = 10
    private var score = 0
    private var ans = ""
    private var records = arrayListOf<Triple<Int, Int, String>>()
    private var stageRecords = Array(4) { StageRecord("", arrayListOf()) }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_guess, container, false)

        textScore = view.findViewById(R.id.textScore)
        textStage = view.findViewById(R.id.textStage)
        textAttempt = view.findViewById(R.id.textAttempt)
        inputGuess = view.findViewById(R.id.inputGuess)
        btnGuess = view.findViewById(R.id.btnGuess)
        guessResult = view.findViewById(R.id.guessResult)

        btnGuess.setOnClickListener {
//            Check input
            if (inputGuess.text == null || inputGuess.text.toString().length != GameManager.LENGTH) {
                Toast.makeText(activity, "請輸入四位數字", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            Get Guess Text
            val guess = inputGuess.text.toString()
            inputGuess.text!!.clear()
//            Calculate A
            var a = 0
            var b = 0
            val f1 = Array(GameManager.LENGTH) { true }
            val f2 = Array(GameManager.LENGTH) { true }
            for (i in 0 until GameManager.LENGTH) {
                if (ans[i] == guess[i]) {
                    f1[i] = false
                    f2[i] = false
                    ++a
                }
            }
//            Calculate B
            if (a != GameManager.LENGTH) {
                for (i in 0 until GameManager.LENGTH) {
                    if (!f1[i]) continue
                    for (j in 0 until GameManager.LENGTH) {
                        if (ans[i] == guess[j] && f2[j]) {
                            f1[i] = false
                            f2[j] = false
                            ++b
                            break
                        }
                    }
                }
            }
//            Record
            records.add(Triple(a, b, guess))

//            Check if the stage is over
            if (a == GameManager.LENGTH || attempts == 1) {
//            if (true) {
                if (a == GameManager.LENGTH) {
//                    Stage Win
                    score += GameManager.SCORE_PER_WIN
                    Toast.makeText(activity, "correct", Toast.LENGTH_SHORT).show()
                }
                stageRecords[stage - 1].guessRecords = records
                records = arrayListOf()
                if (stage == GameManager.ROUNDS) {
//                    Game Over
                    GameManager.histories.add(GameRecord(score, stageRecords))
//                    reset
                    stage = 0
                    score = 0
                    records = arrayListOf<Triple<Int, Int, String>>()
                    stageRecords = Array(GameManager.ROUNDS) { StageRecord("", arrayListOf()) }
//                    result fragment
                    findNavController().navigate(R.id.action_guessFragment_to_resultFragment)
                    return@setOnClickListener
                }
                nextStage()
                return@setOnClickListener
            }

//            Show Record
            val recordView: View = layoutInflater.inflate(R.layout.view_guess_result, null)
            recordView.findViewById<TextView>(R.id.textRecord).text = "Record: ${(11 - attempts)}"
            recordView.findViewById<TextView>(R.id.textInput).text = "Input: $guess"
            recordView.findViewById<TextView>(R.id.textResult).text = "Result: ${a}A${b}B"
            guessResult.addView(recordView)
            attempts--
            textAttempt.text = "Attempt: $attempts"
        }

        nextStage()

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun nextStage() {
//        Reset
        ++stage
        attempts = 10
//        Update UI
        textScore.text = "Score: $score"
        textStage.text = "Stage: $stage/${GameManager.ROUNDS}"
        textAttempt.text = "Attempt: $attempts"
        guessResult.removeAllViews()
//        Regenerate Answer
        val sb = StringBuilder()
        val used = Array(GameManager.S.length) { false }
        for (i in 0 until GameManager.LENGTH) {
            var num = (0 until GameManager.S.length).random()
            while (used[num]) {
                num = (0 until GameManager.S.length).random()
            }
            used[num] = true
            sb.append(GameManager.S[num])
        }
        ans = sb.toString()
        stageRecords[stage - 1].ans = ans
        println("ans: $ans")
    }
}