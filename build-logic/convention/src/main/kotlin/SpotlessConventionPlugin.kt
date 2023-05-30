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
import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt")
                    ktlint(
                        libs.findVersion("ktlint").get()
                            .toString(),
                    ).userData(mapOf("android" to "true"))
                    licenseHeaderFile(
                        rootProject.file(".spotless/copyright.kt"),
                        "^(package|object|import|interface)",
                    )
                    trimTrailingWhitespace()
                    indentWithSpaces()
                    endWithNewline()
                }
                kotlinGradle {
                    target("**/*.gradle.kts, *.gradle.kts")
                    targetExclude("**/build/**")
                    ktlint(
                        libs.findVersion("ktlint").get()
                            .toString(),
                    ).userData(mapOf("android" to "true"))
                    licenseHeaderFile(
                        rootProject.file(".spotless/copyright.kt"),
                        "package|import|tasks|apply|plugins|include|val|object|interface",
                    )
                    trimTrailingWhitespace()
                    indentWithSpaces()
                    endWithNewline()
                }
                format("kts") {
                    target("**/*.kts")
                    targetExclude("**/build/**/*.kts")
                    // Look for the first line that doesn't have a block comment (assumed to be the license)
                    licenseHeaderFile(
                        rootProject.file(".spotless/copyright.kts"),
                        "(^(?![\\/ ]\\*).*$)",
                    )
                    trimTrailingWhitespace()
                    indentWithSpaces()
                    endWithNewline()
                }
                format("misc") {
                    target("**/*.md", "**/.gitignore")
                    targetExclude(
                        "**/*.yaml",
                        "*.yml",
                        ".gradle/**",
                        ".gradle-cache/**",
                        "**/tools/**",
                        "**/build/**",
                    )
                    trimTrailingWhitespace()
                    indentWithSpaces()
                    endWithNewline()
                }
                format("xml") {
                    target("**/res/**/*.xml")
                    targetExclude(
                        "**/build/**",
                        "**/build-logic/**",
                        ".gradle/**"
                    )
                    // Look for the first XML tag that isn't a comment (<!--) or the xml declaration (<?xml)
                    licenseHeaderFile(rootProject.file(".spotless/copyright.xml"), "(<[^!?])")
                    trimTrailingWhitespace()
                    indentWithSpaces(4)
                    endWithNewline()
                }
            }
        }
    }
}