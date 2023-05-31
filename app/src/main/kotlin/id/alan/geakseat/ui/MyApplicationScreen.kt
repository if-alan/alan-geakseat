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
package id.alan.geakseat.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import id.alan.geakseat.features.MyAppState
import id.alan.geakseat.features.rememberMyAppState
import id.alan.geakseat.navigation.MyNavHost
import id.alan.geakseat.ui.theme.MyApplicationTheme

/**
 * First screen when starting the app / main screen of the app
 */
@Composable
fun MyApplicationScreen(
    appState: MyAppState = rememberMyAppState(),
) {
    MyApplicationTheme {
        Scaffold(
            scaffoldState = appState.scaffoldState,
            snackbarHost = {
                SnackbarHost(hostState = it) { data ->
                    Snackbar(
                        snackbarData = data,
                        actionColor = Color.White,
                        backgroundColor = MaterialTheme.colors.error
                    )
                }
            }
        ) { innerPadding ->
            MyNavHost(
                modifier = Modifier.padding(innerPadding),
                appState = appState
            )
        }
    }
}
