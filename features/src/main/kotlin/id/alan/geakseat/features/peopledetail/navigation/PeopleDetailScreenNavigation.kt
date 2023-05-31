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
package id.alan.geakseat.features.peopledetail.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import id.alan.geakseat.core.navigation.MyNavigationDestination
import id.alan.geakseat.features.SharedPeopleDataViewModel
import id.alan.geakseat.features.peopledetail.PeopleDetailRoute

/**
 * object that define people detail route & destination
 */
object PeopleDetailDestination : MyNavigationDestination {
    override val route = "people_detail_route"
    override val destination = "people_detail_destination"
}

/**
 * People Drtail graph builder config
 *
 * @param navController: [NavHostController] for Send Data
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.peopleDetailGraph(
    navController: NavHostController
) {
    composable(route = PeopleDetailDestination.route) {
        val sharedPeopleDetailViewModel: SharedPeopleDataViewModel? =
            navController.previousBackStackEntry?.let { hiltViewModel(it) }

        PeopleDetailRoute(
            sharedPeopleData = sharedPeopleDetailViewModel?.sharedPeopleData
        )
    }
}
