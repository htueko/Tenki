package com.htueko.tenki.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.htueko.tenki.core.presentation.composable.BasicSnackBar
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.core.util.getClassName
import com.htueko.tenki.core.util.logInfo
import com.htueko.tenki.feature.home.view.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tag = getClassName<MainActivity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            // to handle the hardware onBackPressed
            val callBack =
                object : OnBackPressedCallback(enabled = true) {
                    override fun handleOnBackPressed() {
                        logInfo(tag = tag, "$tag handleOnBackPressed: called")
                        finish()
                    }
                }
            onBackPressedDispatcher.addCallback(callBack)

            // to launch the coroutine
            val scope = rememberCoroutineScope()

            // to handle the task related to context
            val context = LocalContext.current

            val snackBarHostState = remember {
                SnackbarHostState()
            }

            TenkiTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        BasicSnackBar(snackbarHostState = snackBarHostState)
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        HomeScreen(
                            snackbarHostState = snackBarHostState,
                        )
                    }
                }
            }
        }
    }
}
