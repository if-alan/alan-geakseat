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
package id.alan.geakseat.features.peoplelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import id.alan.geakseat.core.data.repository.CommonDispatcherRepository
import id.alan.geakseat.core.data.repository.PeopleRepository
import id.alan.geakseat.core.model.responses.ItemPeopleResponse
import id.alan.geakseat.core.model.responses.asExternalModel
import id.alan.geakseat.core.ui.state.ErrorUiState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * View model class that manage [PeopleListScreen] data
 */
@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val dispatcher: CommonDispatcherRepository
) : ViewModel() {

    private val _errorUiState = MutableStateFlow<ErrorUiState?>(null)
    val errorUiState: StateFlow<ErrorUiState?> = _errorUiState.asStateFlow()

    private val pagingConfig = PagingConfig(1, initialLoadSize = 1)

    val peopleListData = Pager(pagingConfig) {
        PeoplePagingSource(peopleRepository)
    }.flow
        .map { it.map(ItemPeopleResponse::asExternalModel) }
        .flowOn(dispatcher.io)
        .cachedIn(viewModelScope)

    /**
     * Handle reset error message, so snackbar will not showed everytime
     */
    fun resetErrorMessage() {
        _errorUiState.value = null
    }
}
