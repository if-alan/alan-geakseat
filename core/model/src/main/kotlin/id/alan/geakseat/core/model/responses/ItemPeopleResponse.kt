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
package id.alan.geakseat.core.model.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response of peoples from API
 */
@Keep
@JsonClass(generateAdapter = true)
data class ItemPeopleResponse(
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "height")
    val height: String? = "",
    @Json(name = "mass")
    val mass: String? = "",
    @Json(name = "hair_color")
    val hairColor: String? = "",
    @Json(name = "skin_color")
    val skinColor: String? = "",
    @Json(name = "eye_color")
    val eyeColor: String? = "",
    @Json(name = "birth_year")
    val birthYear: String? = "",
    @Json(name = "gender")
    val gender: String? = "",
    @Json(name = "homeworld")
    val homeworld: String? = "",
    @Json(name = "films")
    val films: List<ItemFilmResponse>? = listOf(),
    @Json(name = "vehicles")
    val vehicles: List<ItemVehicleResponse>? = listOf(),
    @Json(name = "starships")
    val starships: List<ItemVehicleResponse>? = listOf(),
    @Json(name = "created")
    val created: String? = "",
    @Json(name = "edited")
    val edited: String? = "",
    @Json(name = "url")
    val url: String? = "",
)
