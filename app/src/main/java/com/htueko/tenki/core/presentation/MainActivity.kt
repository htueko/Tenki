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

/**
 * The primary entry point and host [ComponentActivity] for the Tenki application.
 *
 * This activity handles the high-level UI orchestration using Jetpack Compose and is
 * annotated with [AndroidEntryPoint] to enable Hilt dependency injection.
 *
 * ### Key Responsibilities:
 * - **Edge-to-Edge**: Configures the window to draw behind system bars using [enableEdgeToEdge].
 * - **Back Navigation**: Intercepts hardware back presses to provide custom logging and
 * controlled activity finishing via [OnBackPressedCallback].
 * - **Theme Provisioning**: Wraps the content in [TenkiTheme] to provide consistent
 * colors, typography, and shapes.
 * - **Root Navigation**: Sets [HomeScreen] as the root composable within a [Scaffold].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Cache for the class name used in logging.
     * Derived via the [getClassName] utility.
     */
    private val tag = getClassName<MainActivity>()

    /**
     * Initializes the activity and sets the Compose content.
     * * Inside [setContent], the following local states are initialized:
     * - **OnBackPressedCallback**: Custom handler for the hardware back button.
     * - **SnackbarHostState**: State for managing [BasicSnackBar] visibility across the app.
     * - **CoroutineScope**: A [rememberCoroutineScope] for launching side-effects
     * within the Compose hierarchy.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in [onSaveInstanceState].
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            // to handle the hardware onBackPressed
            val callBack =
                object : OnBackPressedCallback(enabled = true) {
                    /**
                     * Custom handler for hardware back press.
                     * Logs the event via [logInfo] and invokes [finish].
                     */
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

            /**
             * Host state for managing Snackbars.
             * This state is hoisted here and passed down to [HomeScreen].
             */
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
