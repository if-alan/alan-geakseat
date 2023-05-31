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
package id.alan.geakseat.core.data.repository

import id.alan.geakseat.core.common.network.responses.GenericListResponse
import id.alan.geakseat.core.model.responses.ItemPeopleResponse

/**
 * Repository module for handling People network operations.
 */
interface PeopleRepository {

    /**
     * Get list of People
     *
     * @param page page for Pagination
     */
    suspend fun getListPeople(page: Int): GenericListResponse<ItemPeopleResponse, Any>
}
