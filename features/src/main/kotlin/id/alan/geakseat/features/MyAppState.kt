/*
 * Copyright 2023 Nur If Alan Farizi | Indonesia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.alan.geakseat.features

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import id.alan.geakseat.core.navigation.MyNavigationDestination
import id.alan.geakseat.core.ui.utils.StringUtils.X_ICON
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Composable method to create navigation controller and pass it to [MyAppState]
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberMyAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = remember {
            SnackbarHostState()
        }
    ),
    navController: NavHostController = rememberAnimatedNavController(),
    snackBarScope: CoroutineScope = rememberCoroutineScope(),
): MyAppState =
    remember(scaffoldState, navController, snackBarScope) {
        MyAppState(
            scaffoldState = scaffoldState,
            navController = navController,
            snackbarScope = snackBarScope
        )
    }

/**
 * Class that used as app state to config navigation controller methods
 *
 * @see Stable
 * @param navController: navigation controller used in the App
 */
@Stable
class MyAppState(
    val scaffoldState: ScaffoldState,
    val snackbarScope: CoroutineScope,
    val navController: NavHostController
) {
    private var snackBarJob: Job? = null

    /**
     * UI logic for navigating to a particular destination in the app. The NavigationOptions to
     * navigate with are based on the type of destination, which could be a top level destination or
     * just a regular destination.
     *
     * @param destination: The [MyNavigationDestination] the app needs to navigate to.
     * @param route: Optional route to navigate to in case the destination contains arguments.
     */
    fun navigate(destination: MyNavigationDestination, route: String? = null) {
        navController.navigate(route ?: destination.route) {
            launchSingleTop = true
        }
    }

    /**
     * Handle show snackBar
     */
    fun showSnackBar(
        message: String,
        actionLabel: String? = X_ICON,
        duration: SnackbarDuration = SnackbarDuration.Short,
        snackBarAction: (() -> Unit)? = null
    ) {
        snackbarScope.launch {
            snackBarJob?.cancel()
            snackBarJob = launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = duration
                )
                when (snackBarResult) {
                    SnackbarResult.Dismissed -> {}
                    SnackbarResult.ActionPerformed ->
                        snackBarAction
                            ?: scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                }
            }
        }
    }
}
