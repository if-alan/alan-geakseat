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
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            extensions.configure<DetektExtension> {
                source = project.files("src/main/kotlin", "src/main/java")
                config = files("$rootDir/.detekt/config.yml")
                autoCorrect = true
            }

            tasks.withType<Detekt>().configureEach {
                reports {
                    html.required.set(true) // observe findings in your browser with structure and code snippets
                    html.outputLocation.set(project.file("build/reports/detekt/report.xml"))
                    xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
                    xml.outputLocation.set(project.file("build/reports/detekt/report.xml"))
                }
            }
        }
    }
}
