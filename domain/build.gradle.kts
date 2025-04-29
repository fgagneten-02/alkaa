import extension.setFrameworkBaseName

plugins {
    id("com.escodro.multiplatform")
}

kotlin {
    setFrameworkBaseName("domain")

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.logging)
            implementation(libs.kotlinx.datetime)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "com.escodro.domain"
}
