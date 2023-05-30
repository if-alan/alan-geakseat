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
plugins {
    id("alan.android.library")
    kotlin("kapt")
    id("alan.spotless")
    id("alan.detekt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.retrofit.core)
    implementation(libs.stetho.okhttp3)

    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.converter)
    kapt(libs.moshi.codegen)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
