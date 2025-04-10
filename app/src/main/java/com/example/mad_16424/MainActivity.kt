package com.example.mad_16424

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mad_16424.ui.theme.Mad_16424Theme
import com.example.mad_16424.ui.theme.WineAppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mad_16424Theme {
                WineAppNavigation()
            }
        }
    }
}



