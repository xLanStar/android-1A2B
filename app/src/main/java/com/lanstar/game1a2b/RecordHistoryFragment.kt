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

class RecordHistoryFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_record_history, container, false) as LinearLayout

        val stageRecord = GameManager.histories[GameManager.selectedGame].stageRecords[GameManager.selectedStage]
        for (i in 0 until stageRecord.guessRecords.size) {
            val guessRecord = stageRecord.guessRecords[i]
            val guessResultView = inflater.inflate(R.layout.view_guess_result, container, false)
            guessResultView.findViewById<TextView>(R.id.textRecord).text = "Record: ${i+1}"
            guessResultView.findViewById<TextView>(R.id.textInput).text = "Input: ${guessRecord.third}"
            guessResultView.findViewById<TextView>(R.id.textResult).text = "Result: ${guessRecord.first}A${guessRecord.second}B"

            view.addView(guessResultView)
        }
        return view
    }
}