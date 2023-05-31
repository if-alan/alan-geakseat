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
package id.alan.geakseat.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.alan.geakseat.core.data.repository.PeopleRepository
import id.alan.geakseat.core.data.repository.impl.PeopleRepositoryImpl

/**
 * Class that contributes to the object graph [SingletonComponent].
 *
 * @see Module
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    /**
     * Create a method binding for [PeopleRepository].
     *
     * @return Instance of Label Repository
     */
    @Binds
    fun bindsPeopleRepository(
        labelRepository: PeopleRepositoryImpl
    ): PeopleRepository
}
