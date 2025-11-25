package com.neonscan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.neonscan.ui.navigation.NeonNavHost
import com.neonscan.ui.theme.NeonTheme
import com.neonscan.di.AppModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = AppModule.provideRepo(this)
        val settings = AppModule.provideSettings(this)
        setContent {
            NeonTheme {
                NeonNavHost(repo = repo, settingsRepository = settings)
            }
        }
    }
}
