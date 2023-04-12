package ru.mys_ya.ssdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.mys_ya.ssdiary.ui.theme.SSDiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SSDiaryTheme {
                SSDiaryApp()
            }
        }
    }
}