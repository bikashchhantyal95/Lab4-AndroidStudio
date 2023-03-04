package com.example.bikashchhantyal_score

import android.R
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bikashchhantyal_score.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    // is_scoring_team_A is used to keep track of which team is currently scoring
    private var is_scoring_team_A = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.switchTeams.setOnCheckedChangeListener{ _, isChecked ->
            val message: String = if(isChecked) "You have selected Team B" else "You have selected Team A"
            is_scoring_team_A = !isChecked
            binding.points.clearCheck()
            printToastMessage(message)
            if (is_scoring_team_A) {
                binding.teamATitle.setTextColor(Color.RED)
                binding.teamBTitle.setTextColor(Color.BLACK)
                binding.teamAScore.setTextColor(Color.RED)
                binding.teamBScore.setTextColor(Color.BLACK)
            } else {
                binding.teamBTitle.setTextColor(Color.RED)
                binding.teamATitle.setTextColor(Color.BLACK)
                binding.teamAScore.setTextColor(Color.BLACK)
                binding.teamBScore.setTextColor(Color.RED)
            }
        }
        if(is_scoring_team_A){
            binding.teamATitle.setTextColor(Color.RED)
            binding.teamAScore.setTextColor(Color.RED)
        }else{
            binding.teamBTitle.setTextColor(Color.RED)
            binding.teamBScore.setTextColor(Color.RED)
        }
//       By default team A is selected
//        if switch button is clicked it will switch team from team A to B o
//        if team B is selected it will switch to team B
        binding.increaseButton.setOnClickListener{
            if (is_scoring_team_A){
                binding.teamAScore.text = (Integer.parseInt(binding.teamAScore.text as String) + 1).toString()
            }else{
                binding.teamBScore.text = (Integer.parseInt(binding.teamBScore.text as String) + 1).toString()
            }
        }
        binding.decreaseButton.setOnClickListener{
            if (is_scoring_team_A){
                binding.teamAScore.text = (Integer.parseInt(binding.teamAScore.text as String) - 1).toString()
                if(Integer.parseInt(binding.teamAScore.text as String) < 0){
                    printToastMessage("Score cannot be negative.")
                }
            }else{
                binding.teamBScore.text = (Integer.parseInt(binding.teamBScore.text as String) - 1).toString()
                if(Integer.parseInt(binding.teamBScore.text as String) < 0){
                    printToastMessage("Score cannot be negative.")
                }
            }
        }
        binding.points.setOnCheckedChangeListener{
            _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)


            val score = when(selectedRadioButton?.text.toString()){
                "1" -> 1
                "2" -> 2
                "3" -> 3
                else -> 0
            }
            val teamName: String = if(is_scoring_team_A) "Team A" else "Team B"
            if(is_scoring_team_A){
                binding.teamAScore.text = (Integer.parseInt(binding.teamAScore.text as String) + score).toString()
            }else{
                binding.teamBScore.text = (Integer.parseInt(binding.teamBScore.text as String) + score).toString()
            }
            printToastMessage("$teamName Scored $score points")
        }

    }
    // Function to print Toast Message
    private fun printToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun IncrementByValue(value: Int, ){

    }
}