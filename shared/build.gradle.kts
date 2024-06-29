import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.kotlinxSerialization)
	alias(libs.plugins.sqldelight)
}

kotlin {
	androidTarget {
		compilations.all {
			compileTaskProvider.configure {
				compilerOptions {
					jvmTarget.set(JvmTarget.JVM_11)
					freeCompilerArgs.add("-Xexpect-actual-classes")
				}
			}
		}
	}
	
	listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64()
	).forEach {
		it.binaries.framework {
			baseName = "shared"
			isStatic = true
			binaryOption("bundleId", "com.antonkuznetsov.kotlinmultiplatform.shared")
		}
	}
	
	sourceSets {
		commonMain.dependencies {
			implementation(libs.kotlinx.coroutines.core)
			implementation(libs.ktor.client.core)
			implementation(libs.ktor.client.content.negotiation)
			implementation(libs.ktor.serialization.kotlinx.json)
			implementation(libs.runtime)
			implementation(libs.kotlinx.datetime)
			implementation(libs.koin.core)
		}
		commonTest.dependencies {
			implementation(libs.kotlin.test)
		}
		androidMain.dependencies {
			implementation(libs.ktor.client.android)
			implementation(libs.android.driver)
		}
		iosMain.dependencies {
			implementation(libs.ktor.client.darwin)
			implementation(libs.native.driver)
		}
	}
	task("testClasses")
}

android {
	namespace = "com.antonkuznetsov.kotlinmultiplatform.shared"
	compileSdk = 34
	defaultConfig {
		minSdk = 28
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}

sqldelight {
	databases {
		create("AppDatabase") {
			packageName.set("com.antonkuznetsov.kotlinmultiplatform.cache")
		}
	}
}
