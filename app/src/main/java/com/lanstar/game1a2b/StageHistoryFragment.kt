package com.lanstar.game1a2b

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StageHistoryFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_stage_history, container, false) as LinearLayout

        val gameRecord = GameManager.histories[GameManager.selectedGame]
        for (i in 0 until GameManager.ROUNDS) {
            val stageRecord = gameRecord.stageRecords[i]
            val stageResultView = inflater.inflate(R.layout.view_stage_result, container, false)
            stageResultView.findViewById<TextView>(R.id.textStageAt).text = "Stage: ${i + 1}"
            stageResultView.findViewById<TextView>(R.id.textAns).text = "Ans: ${stageRecord.ans}"
            stageResultView.findViewById<TextView>(R.id.textTries).text = "Tries: ${stageRecord.guessRecords.size}"
            stageResultView.setOnClickListener {
                GameManager.selectedStage = i
                findNavController().navigate(R.id.action_stageHistoryFragment_to_recordHistoryFragment)
            }
            view.addView(stageResultView)
        }
        return view
    }
}