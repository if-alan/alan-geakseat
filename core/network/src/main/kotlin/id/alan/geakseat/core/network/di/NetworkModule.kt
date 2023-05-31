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

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.alan.geakseat.core.common.network.adapter.NetworkResponseAdapterFactory
import id.alan.geakseat.core.network.BuildConfig
import id.alan.geakseat.core.network.adapter.NullAdapter
import id.alan.geakseat.core.network.authorization.UnauthorizedInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Class that contributes to the object graph [SingletonComponent].
 *
 * @see Module
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val TIMEOUT: Long = 100L
    }

    /**
     * Create a provider method binding for [Moshi].
     *
     * @return Instance of moshi.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Create a provider method binding for [Retrofit.Builder].
     *
     * @return Instance of retrofit builder.
     * @see Provides
     */
    @Provides
    fun provideRetrofitBuilder(moshi: Moshi): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(NullAdapter())
            .addConverterFactory(MoshiConverterFactory.create(moshi))

    /**
     * Create a base provider method binding for [OkHttpClient.Builder].
     *
     * @return Instance of OkHttpClient Builder.
     * @see Provides
     */
    @Provides
    fun provideBasicOkhttpClient(): OkHttpClient.Builder = OkHttpClient.Builder()
        .callTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addNetworkInterceptor(StethoInterceptor())

    /**
     * Create a provider method binding for [OkHttpClient]
     * without any authenticator
     *
     * @return Instance of http client.
     * @see Provides
     */
    @Provides
    fun provideUnauthorizedInterceptor() = UnauthorizedInterceptor()

    /**
     * Create a provider method binding for [OkHttpClient]
     * without any authenticator
     *
     * @return Instance of http client.
     * @see Provides
     */
    @Provides
    fun provideUnAuthHttpClient(
        clientBuilder: OkHttpClient.Builder,
        unauthorizedInterceptor: UnauthorizedInterceptor
    ): OkHttpClient = clientBuilder
        .addInterceptor(unauthorizedInterceptor)
        .build()
}
