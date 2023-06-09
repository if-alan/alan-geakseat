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
package id.alan.geakseat.features.peopledetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.alan.geakseat.core.model.data.PeopleData
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Composable method to handle [PeopleDetailScreen]
 */
@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun PeopleDetailRoute(
    sharedPeopleData: PeopleData?,
    viewModel: PeopleDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.handleSharedPeopleData(sharedPeopleData)
    }

    val peopleData by viewModel.peopleData.collectAsStateWithLifecycle()

    PeopleDetailScreen(peopleData)
}

@Composable
private fun PeopleDetailScreen(
    peopleData: PeopleData
) {
    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize()
    ) {
        RowField("Name:", peopleData.name)
        Row {
            RowField("Height:", peopleData.height)
            Spacer(modifier = Modifier.size(16.dp))
            RowField("Mass:", peopleData.mass)
        }
        Text(text = "Color")
        Row {
            RowField("Hair:", peopleData.hairColor)
            Spacer(modifier = Modifier.size(16.dp))
            RowField("Skin:", peopleData.skinColor)
            Spacer(modifier = Modifier.size(16.dp))
            RowField("Eye:", peopleData.eyeColor)
        }
        RowField("Gender:", peopleData.gender)
        RowField("Birth Year:", peopleData.birthYear)
        ColumnField("Films:", peopleData.films)
        ColumnField("Species:", peopleData.species)
        ColumnField("Starships:", peopleData.starships)
        ColumnField("Vehicles:", peopleData.vehicles)
    }
}

@Composable
private fun RowField(label: String, value: String) {
    Row(
        modifier = Modifier.padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = value,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun ColumnField(label: String, data: List<String>) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(text = label)
        Text(
            text = data.joinToString(", ").ifBlank { "-" },
            fontWeight = FontWeight.SemiBold,
        )
    }
}
