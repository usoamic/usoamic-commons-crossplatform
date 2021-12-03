package io.usoamic.dependencies

object Versions {
    private const val isAndroid = false

    object Usoamic {
        const val validateUtilKt = "1.0.1-3"
        val kt get() = when(isAndroid) {
             true -> "1.2.2-android"
             false -> "1.3.0-beta.9"
        }
    }

    object Java {
        const val javaxAnnotationApi = "1.3.2"
    }

    object DI {
        const val dagger = "2.37"
        const val daggerCompiler = "2.37"
    }

    object Rx {
        const val java = "2.2.19"
        const val kotlin = "2.4.0"
    }

    object Cryptocurrency {
        val web3j get() = if (isAndroid) "4.6.0-android" else "4.6.0"
    }

    object Test {
        const val kotlinTestJunit = "1.5.21"
        const val junitJupiter = "5.5.0"
    }

    object Other {
        const val gson = "2.8.5"
        const val zxing = "3.4.0"
    }
}