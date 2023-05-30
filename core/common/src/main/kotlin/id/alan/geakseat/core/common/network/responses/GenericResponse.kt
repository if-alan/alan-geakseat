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
package id.alan.geakseat.core.common.network.responses

import id.alan.geakseat.core.common.network.adapter.NetworkResponse

/**
 * Generic response for API response
 * Ignore the error response body
 * @param S Success responses response
 */
typealias GenericResponseSuccess<S> =
    NetworkResponse<BaseSuccessResponse<S>, BaseFailResponse<Any>>

/**
 * Generic response for API response
 * @param S Success responses response
 * @param F Failed responses response
 */
typealias GenericResponse<S, F> =
    NetworkResponse<BaseSuccessResponse<S>, BaseFailResponse<F>>

/**
 * Generic response for API response with List type and meta body responses
 * @param S Success responses response
 * @param F Failed responses response
 */
typealias GenericListResponse<S, F> =
    NetworkResponse<BaseSuccessListResponse<S>, BaseFailResponse<F>>
