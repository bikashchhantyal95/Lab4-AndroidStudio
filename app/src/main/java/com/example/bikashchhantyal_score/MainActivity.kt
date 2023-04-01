package com.example.bikashchhantyal_score

import android.R
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.bikashchhantyal_score.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    // is_scoring_team_A is used to keep track of which team is currently scoring
    private var is_scoring_team_A = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggleMode.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        // Add a listener to the switch button for team selection
        binding.switchTeams.setOnCheckedChangeListener{ _, isChecked ->
            val message: String = if(isChecked) "You have selected Team B" else "You have selected Team A"

            // Update is_scoring_team_A based on the current state of the switch button
            is_scoring_team_A = !isChecked

            // Clear the selected radio button
            binding.points.clearCheck()

            // display message to show the selected team
            displayMessage(message)

            // Update UI based on the currently scoring team
            if (is_scoring_team_A) {
                binding.teamATitle.setTextColor(Color.GREEN)
                binding.teamBTitle.setTextColor(Color.WHITE)
                binding.teamAScore.setTextColor(Color.GREEN)
                binding.teamBScore.setTextColor(Color.WHITE)
            } else {
                binding.teamBTitle.setTextColor(Color.GREEN)
                binding.teamATitle.setTextColor(Color.WHITE)
                binding.teamAScore.setTextColor(Color.WHITE)
                binding.teamBScore.setTextColor(Color.GREEN)
            }
        }

        // Update UI based on the initially selected team
        if(is_scoring_team_A){
            binding.teamATitle.setTextColor(Color.GREEN)
            binding.teamAScore.setTextColor(Color.GREEN)
        }else{
            binding.teamBTitle.setTextColor(Color.GREEN)
            binding.teamBScore.setTextColor(Color.GREEN)
        }

        // Add a listener to the increase button for score increment
//        Increase score by one for the selected team
        binding.increaseButton.setOnClickListener{
            if (is_scoring_team_A){
                binding.teamAScore.text = (Integer.parseInt(binding.teamAScore.text as String) + 1).toString()
            }else{
                binding.teamBScore.text = (Integer.parseInt(binding.teamBScore.text as String) + 1).toString()
            }
        }

        // Add a listener to the decrease button for score decrement
        binding.decreaseButton.setOnClickListener{
            if (is_scoring_team_A){
                binding.teamAScore.text = (Integer.parseInt(binding.teamAScore.text as String) - 1).toString()
                if(Integer.parseInt(binding.teamAScore.text as String) < 0){
                    binding.teamAScore.text = "0"
                    displayMessage("Score cannot be negative.")
                }
            }else{
                binding.teamBScore.text = (Integer.parseInt(binding.teamBScore.text as String) - 1).toString()
                if(Integer.parseInt(binding.teamBScore.text as String) < 0){
//                    set score to zero if score of team B is less than 0
                    binding.teamBScore.text = "0"
                    displayMessage("Score cannot be negative.")
                }
            }
        }

        // Add a listener to the radio group button for score selection
        binding.points.setOnCheckedChangeListener{
            _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

//            value is stored on [score] variable based on selected radio button
            val score = when(selectedRadioButton?.text.toString()){
                "1" -> 1
                "2" -> 2
                "3" -> 3
                else -> 0
            }

//            value is stored on [teamName] variable based on selected team
            val teamName: String = if(is_scoring_team_A) "Team A" else "Team B"

//            increase score base on selected value from radio btn for selected team
            if(is_scoring_team_A){
                binding.teamAScore.text = (Integer.parseInt(binding.teamAScore.text as String) + score).toString()
            }else{
                binding.teamBScore.text = (Integer.parseInt(binding.teamBScore.text as String) + score).toString()
            }
            displayMessage("$teamName Scored $score points")
        }

    }


    // Function to print display Message
    private fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}