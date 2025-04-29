package com.escodro.shared

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.escodro.appstate.AppState
import com.escodro.appstate.rememberAlkaaAppState
import com.escodro.designsystem.AlkaaTheme
import com.escodro.shared.model.AppThemeOptions
import org.koin.compose.koinInject
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars

expect fun provideResult() : Double

@Composable
fun AlkaaMultiplatformApp(
    modifier: Modifier = Modifier,
    appState: AppState = rememberAlkaaAppState(),
) {
    AlkaaTheme(isDarkTheme = rememberIsDarkTheme()) {
        Home(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    WindowInsets.systemBars
                        .asPaddingValues()
                )
        )
    }
}

@Composable
fun Home(
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(text = provideResult().toString())
    }
}

@Composable
private fun rememberIsDarkTheme(viewModel: AppViewModel = koinInject()): Boolean {
    val isSystemDarkTheme = isSystemInDarkTheme()

    val theme by remember(viewModel) {
        viewModel.loadCurrentTheme()
    }.collectAsState(initial = AppThemeOptions.SYSTEM)

    val isDarkTheme = when (theme) {
        AppThemeOptions.LIGHT -> false
        AppThemeOptions.DARK -> true
        AppThemeOptions.SYSTEM -> isSystemDarkTheme
    }
    return isDarkTheme
}
