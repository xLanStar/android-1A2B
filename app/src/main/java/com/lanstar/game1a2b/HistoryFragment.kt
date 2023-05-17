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

class HistoryFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_history, container, false) as LinearLayout

        for (i in 0 until GameManager.histories.size) {
            val gameResultView = inflater.inflate(R.layout.view_game_result, container, false)
            gameResultView.findViewById<TextView>(R.id.textGame).text = "Game: ${i+1}"
            gameResultView.findViewById<TextView>(R.id.textScore).text = "Score: ${GameManager.histories[i].score}"
            gameResultView.setOnClickListener {
                GameManager.selectedGame = i
                findNavController().navigate(R.id.action_historyFragment_to_stageHistoryFragment)
            }
            view.addView(gameResultView)
        }
        return view
    }
}