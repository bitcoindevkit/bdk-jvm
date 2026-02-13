import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("ch.qos.logback:logback-classic:1.5.13")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

tasks.register<JavaExec>("WalletSetupBip32") {
    group = "application"
    description = "Runs the main function in the WalletSetupBip32 example"
    mainClass.set("org.bitcoindevkit.WalletSetupBip32Kt")
    classpath = sourceSets["main"].runtimeClasspath
    workingDir = project.rootDir
}

tasks.register<JavaExec>("MultisigTransaction") {
    group = "application"
    description = "Runs the main function in the MultisigTransaction example"
    mainClass.set("org.bitcoindevkit.MultisigTransactionKt")
    classpath = sourceSets["main"].runtimeClasspath
    workingDir = project.rootDir
}

tasks.register<JavaExec>("Kyoto") {
    group = "application"
    description = "Runs the main function in the Kyoto example"
    mainClass.set("org.bitcoindevkit.KyotoKt")
    classpath = sourceSets["main"].runtimeClasspath
    workingDir = project.rootDir
}
