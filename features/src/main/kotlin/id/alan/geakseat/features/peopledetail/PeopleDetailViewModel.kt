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
package id.alan.geakseat.features.peopledetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.alan.geakseat.core.model.data.PeopleData
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * View model class that manage [PeopleDetailScreen] data
 */
@HiltViewModel
class PeopleDetailViewModel @Inject constructor() : ViewModel() {

    private val _peopleData = MutableStateFlow(PeopleData())
    val peopleData: StateFlow<PeopleData> = _peopleData.asStateFlow()

    /**
     * Handle People Data from List.
     */
    fun handleSharedPeopleData(sharedPeopleData: PeopleData?) {
        sharedPeopleData?.let {
            _peopleData.value = sharedPeopleData
        }
    }
}
