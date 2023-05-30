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

import androidx.annotation.Keep

/**
 * Generic network response for any type responses [T].
 *
 * @param status A string description of the call status.
 * @param message A more descriptive message of the failure call status.
 * @param data The results returned by the call.
 */
@Keep
data class BaseSuccessResponse<T>(
    val status: String?,
    val message: String?,
    val data: T?
)
