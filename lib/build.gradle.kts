import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.SourcesJar
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
}

group = "org.bitcoindevkit"
version = "2.3.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED, TestLogEvent.STANDARD_OUT)
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showStackTraces = true
        showCauses = true
    }
}

dependencies {
    // JNA
    implementation("net.java.dev.jna:jna:5.14.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    // Tests
    testImplementation(kotlin("test"))
    testImplementation("org.kotlinbitcointools:regtest-toolbox:0.1.0")
}

mavenPublishing {
    coordinates(
        groupId = group.toString(),
        artifactId = "bdk-jvm",
        version = version.toString()
    )

    pom {
        name.set("bdk-jvm")
        description.set("Bitcoin Dev Kit Kotlin language bindings.")
        url.set("https://bitcoindevkit.org")
        inceptionYear.set("2021")
        licenses {
            license {
                name.set("APACHE 2.0")
                url.set("https://github.com/bitcoindevkit/bdk/blob/master/LICENSE-APACHE")
            }
            license {
                name.set("MIT")
                url.set("https://github.com/bitcoindevkit/bdk/blob/master/LICENSE-MIT")
            }
        }
        developers {
            developer {
                id.set("bdkdevelopers")
                name.set("Bitcoin Dev Kit Developers")
                email.set("dev@bitcoindevkit.org")
            }
        }
        scm {
            url.set("https://github.com/bitcoindevkit/bdk-ffi/")
            connection.set("scm:git:github.com/bitcoindevkit/bdk-ffi.git")
            developerConnection.set("scm:git:ssh://github.com/bitcoindevkit/bdk-ffi.git")
        }
    }

    configure(
        KotlinJvm(
            javadocJar = JavadocJar.Dokka("dokkaGeneratePublicationHtml"),
            sourcesJar = SourcesJar.Sources(),
        )
    )

    publishToMavenCentral()
    signAllPublications()
}

dokka {
    moduleName.set("bdk-jvm")
    moduleVersion.set(version.toString())
    dokkaSourceSets.main {
        includes.from("../docs/DOKKA_LANDING.md")
        sourceLink {
            localDirectory.set(file("src/main/kotlin"))
            remoteUrl("https://bitcoindevkit.org/")
            remoteLineSuffix.set("#L")
        }
    }
    pluginsConfiguration.html {
        // customStyleSheets.from("styles.css")
        // customAssets.from("logo.svg")
        footerMessage.set("(c) Bitcoin Dev Kit Developers")
    }
}
