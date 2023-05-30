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
package id.alan.geakseat.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.alan.geakseat.core.network.api.SwApi
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Class that contributes services API.
 *
 * @see Module
 */
@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ApiModule {

    /**
     * Create a provider method binding for [SwApi].
     *
     * @return Instance of Star Wars service.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideTaskService(
        client: OkHttpClient,
        retrofitBuilder: Retrofit.Builder
    ): SwApi = retrofitBuilder
        .client(client)
        .build()
        .create(SwApi::class.java)
}
