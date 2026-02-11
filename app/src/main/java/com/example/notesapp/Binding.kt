package com.example.notesapp

import com.example.notesapp.ui.theme.CardColors


object Binding {

    fun bindingColors():CardColors  {
        return when (getCardColor()) {
            1 -> CardColors.BLUE
            2 -> CardColors.PINK
            3 -> CardColors.GREEN
            4 -> CardColors.YELLOW
            5 -> CardColors.PURPLE
            else -> CardColors.BLUE // default value
        }
    }

    fun getCardColor(): Int {
        val startValue = 1
        val lastValue = 5
        return (startValue .. lastValue).random()


    }



}