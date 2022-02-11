package com.egoriku.blurhash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import cafe.adriel.voyager.navigator.Navigator
import com.egoriku.blurhash.screen.Main
import com.egoriku.blurhash.theme.AppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Scaffold {
                    Navigator(screen = Main())
                }
            }
        }
    }
}