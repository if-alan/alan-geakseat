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

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.alan.geakseat.core.common.network.adapter.NetworkResponse
import id.alan.geakseat.core.data.repository.PeopleRepository
import id.alan.geakseat.core.model.responses.ItemPeopleResponse
import id.alan.geakseat.core.ui.utils.StringUtils.UNKNOWN_ERROR

/**
 * It is used to load pages of People List data.
 */
@Suppress("ComplexMethod")
class PeoplePagingSource(
    private val peopleRepository: PeopleRepository
) : PagingSource<Int, ItemPeopleResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemPeopleResponse> {

        val pageNumber = params.key ?: 1

        // Since 1 is the lowest page number, return null to signify no more pages should
        // be loaded before it.
        val prevKey = if (pageNumber == 1) null else pageNumber - 1
        val loadSize = params.loadSize

        return when (
            val response =
                peopleRepository.getListPeople(
                    page = pageNumber
                )
        ) {
            is NetworkResponse.Success -> {
                val data = response.body.results ?: mutableListOf()
                val nextKey =
                    if (data.isNotEmpty() && data.size >= loadSize) pageNumber + 1 else null

                LoadResult.Page(data, prevKey, nextKey)
            }

            is NetworkResponse.ApiError -> LoadResult.Error(Throwable(UNKNOWN_ERROR))
            is NetworkResponse.NetworkError -> LoadResult.Error(Throwable(UNKNOWN_ERROR))
            is NetworkResponse.UnknownError -> LoadResult.Error(
                response.error ?: Throwable(UNKNOWN_ERROR)
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemPeopleResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
