package com.lanstar.game1a2b

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ResultFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val stageResultContainer = view.findViewById<LinearLayout>(R.id.stageResult)

        val gameRecord = GameManager.histories.last()
        for (i in 0 until GameManager.ROUNDS) {
            val stageRecord = gameRecord.stageRecords[i]
            val stageResultView = inflater.inflate(R.layout.view_stage_result, container, false)
            stageResultView.findViewById<TextView>(R.id.textStageAt).text = "Stage: ${i + 1}"
            stageResultView.findViewById<TextView>(R.id.textAns).text = "Ans: ${stageRecord.ans}"
            stageResultView.findViewById<TextView>(R.id.textTries).text = "Tries: ${stageRecord.guessRecords.size}"
            stageResultContainer.addView(stageResultView)
        }

        view.findViewById<TextView>(R.id.textTotalScore).text = "Score: ${gameRecord.score}"

        view.findViewById<Button>(R.id.btnNext).setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_guessFragment)
        }
        view.findViewById<Button>(R.id.btnHistory2).setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_historyFragment)
        }
        view.findViewById<Button>(R.id.btnHome).setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
        }

        return view
    }
}