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
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:data"))
                add("implementation", project(":core:navigation"))
                add("implementation", project(":core:ui"))

                add("implementation", libs.findLibrary("hilt.android").get())
                add("kapt", libs.findLibrary("hilt.compiler").get())

                add("implementation",libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation",libs.findLibrary("androidx.lifecycle.viewModelCompose").get())

                add("implementation",libs.findLibrary("androidx.compose.material").get())
                add("implementation",libs.findLibrary("androidx.paging.compose").get())

                add("implementation", libs.findLibrary("accompanist.pager").get())
//                add("implementation", libs.findLibrary("accompanist.pager.indicators").get())

                add("implementation", libs.findLibrary("accompanist.swiperefresh").get())
            }
        }
    }
}
