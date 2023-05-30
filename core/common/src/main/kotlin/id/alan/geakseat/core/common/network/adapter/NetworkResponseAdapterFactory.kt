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
package id.alan.geakseat.core.common.network.adapter

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit

/**
 * Factory of Network Response Adapter
 */
class NetworkResponseAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? =
        // suspend functions wrap the response type in `Call`
        if (Call::class.java != getRawType(returnType)) {
            null
        } else {
            // check first that the return type is `ParameterizedType`
            check(returnType is ParameterizedType) {
                "return type must be parameterized as Call<NetworkResponse<<Foo>> or " +
                    "Call<NetworkResponse<out Foo>>"
            }

            // get the response type inside the `Call` type
            val responseType = getParameterUpperBound(0, returnType)
            // if the response type is not ApiResponse then we can't handle this type, so we return null
            if (getRawType(responseType) != NetworkResponse::class.java) {
                null
            } else {
                // the response type is ApiResponse and should be parameterized
                check(responseType is ParameterizedType) {
                    "Response must be parameterized as " +
                        "NetworkResponse<Foo> or NetworkResponse<out Foo>"
                }

                val successBodyType = getParameterUpperBound(0, responseType)
                val errorBodyType = getParameterUpperBound(1, responseType)

                val errorBodyConverter =
                    retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

                NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)
            }
        }
}
