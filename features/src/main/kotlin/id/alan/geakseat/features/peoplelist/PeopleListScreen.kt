/*
 * Copyright 2020 Nur If Alan Farizi | Indonesia
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
package id.alan.geakseat.features.peoplelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import id.alan.geakseat.core.ui.state.ErrorUiState
import id.alan.geakseat.core.ui.utils.StringUtils.NETWORK_ERROR
import id.alan.geakseat.core.ui.utils.StringUtils.SERVER_ERROR
import id.alan.geakseat.core.ui.utils.StringUtils.X_ICON
import id.alan.geakseat.features.SharedPeopleDataViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Composable method to handle [PeopleListScreen]
 */
@Composable
fun PeopleListRoute(
    navigateToPeopleDetail: () -> Unit,
    sharedPeopleDataViewModel: SharedPeopleDataViewModel,
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    viewModel: PeopleListViewModel = hiltViewModel()
) {
    PeopleListScreen(
        navigateToPeopleDetail = navigateToPeopleDetail,
        sharedPeopleDataViewModel = sharedPeopleDataViewModel,
        viewModel = viewModel
    )

    HandleErrorState(
        showSnackBar = showSnackBar,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalCoroutinesApi::class)
@Composable
private fun PeopleListScreen(
    navigateToPeopleDetail: () -> Unit,
    sharedPeopleDataViewModel: SharedPeopleDataViewModel,
    viewModel: PeopleListViewModel
) {
    val peopleListData = viewModel.peopleListData
        .collectAsLazyPagingItems()

    SwipeRefresh(
        modifier = Modifier
            .padding(all = 16.dp),
        state = rememberSwipeRefreshState(false),
        onRefresh = { peopleListData.refresh() }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (peopleListData.loadState.refresh) {
                is LoadState.Loading -> {}
                is LoadState.NotLoading ->
                    items(peopleListData.itemCount) { index ->
                        peopleListData[index]?.let {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        sharedPeopleDataViewModel.sharePeopleData(it)
                                        navigateToPeopleDetail()
                                    }
                            ) {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = it.name ?: ""
                                )
                            }
                        }
                    }

                is LoadState.Error -> {}
            }

            when (peopleListData.loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> item { CircularProgressIndicator() }
                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalCoroutinesApi::class)
@Composable
private fun HandleErrorState(
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    viewModel: PeopleListViewModel
) {
    val errorUiState by viewModel.errorUiState.collectAsStateWithLifecycle()

    LaunchedEffect(errorUiState) {
        when (errorUiState) {
            is ErrorUiState.NetworkError -> {
                showSnackBar(NETWORK_ERROR, X_ICON, SnackbarDuration.Short, null)
                viewModel.resetErrorMessage()
            }

            is ErrorUiState.ApiError -> {
                showSnackBar(
                    (errorUiState as ErrorUiState.ApiError).errorName,
                    X_ICON,
                    SnackbarDuration.Short,
                    null
                )
                viewModel.resetErrorMessage()
            }

            is ErrorUiState.UnknownError -> {
                showSnackBar(SERVER_ERROR, X_ICON, SnackbarDuration.Short, null)
                viewModel.resetErrorMessage()
            }

            else -> {}
        }
    }
}
