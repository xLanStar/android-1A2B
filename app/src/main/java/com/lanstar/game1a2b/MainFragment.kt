package com.lanstar.game1a2b

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

//        按下 開始按鈕 的時候，叫 navController 從 StartFragment 跳轉到 GuessFragment
        view.findViewById<Button>(R.id.btnStart).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_guessFragment)
        }
//        按下 開始按鈕 的時候，叫 navController 從 StartFragment 跳轉到 HistoryFragment
        view.findViewById<Button>(R.id.btnHistory).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_historyFragment)
        }

        return view
    }
}