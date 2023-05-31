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

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.alan.geakseat.core.data.repository.CommonDispatcherRepository
import id.alan.geakseat.core.data.repository.impl.CommonDispatcherRepositoryImpl
import javax.inject.Singleton

/**
 * Class that provide dispatcher
 */
@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    /**
     * function that provide coroutine common dispatcher (ex: io, main)
     */
    @Singleton
    @Provides
    fun provideDispatcher(): CommonDispatcherRepository = CommonDispatcherRepositoryImpl()
}
