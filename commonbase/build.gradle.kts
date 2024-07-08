plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}
group = "com.lazyliuzy.lzybase"
version = "1.0"

android {
    namespace = "com.lazyliuzy.commonbase"
    compileSdk = 34
    //1
    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    tasks.register<Jar>("generateSourcesJar") {
        from(project.android.sourceSets.getByName("main").java.srcDirs)
        archiveClassifier.set("sources")
    }
    afterEvaluate {
        publishing {
            publications {
                // 创建一个名为 "release" 的 Maven 发布
                register("release", MavenPublication::class) {
                    // 应用 release 构建变体的组件
                    from(components["release"])
                    // 可以根据需要自定义发布的属性
                    groupId = "com.lazyliuzy.lzybase"
                    artifactId = "commonbase"
                    version = "1.0"
                }

                // 创建一个名为 "debug" 的 Maven 发布
//                register("debug", MavenPublication::class) {
//                    // 应用 debug 构建变体的组件
//                    from(components["debug"])
//                    groupId = "com.lazyliuzy.lzybase"
//                    artifactId = "commonbase"
//                    version = "1.0"
//                }
            }
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}