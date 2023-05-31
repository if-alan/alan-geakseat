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
package id.alan.geakseat.core.data.repository.impl

import id.alan.geakseat.core.common.network.responses.GenericListResponse
import id.alan.geakseat.core.data.repository.PeopleRepository
import id.alan.geakseat.core.model.responses.ItemPeopleResponse
import id.alan.geakseat.core.network.api.PeopleApi
import javax.inject.Inject

/**
 * Repository module for handling People network operations
 */
class PeopleRepositoryImpl @Inject constructor(
    private val api: PeopleApi
) : PeopleRepository {

    /**
     * Get list of people
     *
     * @return Response success or failed.
     */
    override suspend fun getListPeople(
        page: Int
    ): GenericListResponse<ItemPeopleResponse, Any> =
        api.getPeoples(page)
}
