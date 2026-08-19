plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.compose)

	//Core External Libraries
	alias(libs.plugins.jetbrains.kotlin.serialization)
	alias(libs.plugins.devtools.ksp)
	alias(libs.plugins.hilt.android)
}

android {
	namespace = "com.filippochinni.inventoryapp"
	compileSdk {
		version = release(37)
	}

	defaultConfig {
		applicationId = "com.filippochinni.inventoryapp"
		minSdk = 29
		targetSdk = 37
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			optimization {
				enable = false
			}
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	buildFeatures {
		compose = true
	}
}

dependencies {
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	testImplementation(libs.junit)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.compose.ui.test.junit4)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.junit)
	debugImplementation(libs.androidx.compose.ui.test.manifest)
	debugImplementation(libs.androidx.compose.ui.tooling)

	//Core External Libraries
	implementation(libs.androidx.navigation3.ui)
	implementation(libs.androidx.navigation3.runtime)
	implementation(libs.androidx.material3.adaptive.navigation3)
	implementation(libs.androidx.lifecycle.viewmodel.navigation3)
	implementation(libs.kotlinx.serialization.core)
	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)
	implementation(libs.androidx.room3.runtime)
	ksp(libs.androidx.room3.compiler)
	implementation(libs.androidx.datastore)
	implementation(libs.androidx.datastore.preferences)
	implementation(libs.androidx.lifecycle.viewmodel.compose)
	implementation(libs.androidx.compose.material3.window.size.class1)
	implementation(libs.coil.compose)
	implementation(libs.retrofit)
	implementation(libs.kotlinx.serialization.json)
	implementation(libs.retrofit2.kotlinx.serialization.converter)
	testRuntimeOnly(libs.junit.platform.launcher)
	testImplementation(libs.junit.jupiter)
	testImplementation(libs.kotlinx.coroutines.test)
	androidTestImplementation(libs.androidx.navigation.testing)
	androidTestImplementation(libs.androidx.espresso.intents)
	androidTestImplementation(libs.androidx.work.testing)
}

//Core External Libraries
tasks .withType<Test> {
	useJUnitPlatform()
}
