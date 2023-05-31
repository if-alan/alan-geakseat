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
package id.alan.geakseat.features.peoplelist.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.SnackbarDuration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import id.alan.geakseat.core.navigation.MyNavigationDestination
import id.alan.geakseat.features.SharedPeopleDataViewModel
import id.alan.geakseat.features.peoplelist.PeopleListRoute

/**
 * object that define people list route & destination
 */
object PeopleListDestination : MyNavigationDestination {
    override val route = "people_list_route"
    override val destination = "people_list_destination"
}

/**
 * People List graph builder config
 *
 * @param navigateToPeopleDetail: Method used to navigate to PeopleDetailScreen
 * @param showSnackBar: Show alert, info, etc with SnackBar
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.peopleListGraph(
    navigateToPeopleDetail: () -> Unit,
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit
) {
    composable(route = PeopleListDestination.route) { backStackEntry ->
        val sharedPeopleDataViewModel: SharedPeopleDataViewModel =
            hiltViewModel(backStackEntry)

        PeopleListRoute(
            navigateToPeopleDetail = navigateToPeopleDetail,
            sharedPeopleDataViewModel = sharedPeopleDataViewModel,
            showSnackBar = showSnackBar
        )
    }
}
