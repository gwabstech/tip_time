package com.gwabs.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gwabs.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // declare the binding object
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize the binding variable i created
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
           calculateTips()
        }


    }

    private fun calculateTips(){
        // here i get the cost of service and save it to a variable
        val costOfService = binding.costOfService.text.toString()
        val cost = costOfService.toDoubleOrNull()
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent-> 0.18
            else->0.15
        }

        if (cost == null){
            binding.costOfService.error = "Please enter cost of service"
            return
        }
        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}