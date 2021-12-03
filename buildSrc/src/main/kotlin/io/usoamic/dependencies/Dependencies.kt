package io.usoamic.dependencies

object Dependencies {
    object Usoamic {
        const val validateUtilKt = "com.github.usoamic:validateutilkt:v${Versions.Usoamic.validateUtilKt}"
        val kt get() = "com.github.usoamic:usoamickt:v${Versions.Usoamic.kt}"
    }

    object Java {
        const val javaxAnnotationApi = "javax.annotation:javax.annotation-api:${Versions.Java.javaxAnnotationApi}"
    }

    object Rx {
        const val java = "io.reactivex.rxjava2:rxjava:${Versions.Rx.java}"
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.Rx.kotlin}"
    }

    object Kotlin {
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
        const val stdLibJDK = "org.jetbrains.kotlin:kotlin-stdlib"
        const val stdLibJDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    }

    object DI {
        const val dagger = "com.google.dagger:dagger:${Versions.DI.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.DI.dagger}"
    }

    object Cryptocurrency {
        val web3j get() = "org.web3j:core:${Versions.Cryptocurrency.web3j}"
    }

    object Other {
        const val gson = "com.google.code.gson:gson:${Versions.Other.gson}"
        const val zxing = "com.google.zxing:core:${Versions.Other.zxing}"
    }
}