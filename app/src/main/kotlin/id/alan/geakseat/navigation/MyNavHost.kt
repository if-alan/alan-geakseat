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
package id.alan.geakseat.navigation

import MyAppState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import id.alan.geakseat.features.peoplelist.navigation.PeopleListDestination
import id.alan.geakseat.features.peoplelist.navigation.peopleListGraph
import id.alan.geakseat.features.peopledetail.navigation.PeopleDetailDestination
import id.alan.geakseat.features.peopledetail.navigation.peopleDetailGraph

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 * @param modifier a [Modifier] for parent the text field
 * @param appState App State of the app, that used to create & config navigation
 * @param startDestination default start destination of the navigation
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyNavHost(
    modifier: Modifier,
    appState: MyAppState,
    startDestination: String = PeopleListDestination.route
) {
    AnimatedNavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        peopleListGraph(
            navigateToPeopleDetail = {
                appState.navigate(PeopleDetailDestination, null)
            },
            showSnackBar = appState::showSnackBar
        )
        peopleDetailGraph(
            navController = appState.navController,
        )
    }
}
