pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "News Client"
include(":app")
include(":core")
include(":features")
include(":features:home")
include(":core:ui")
include(":core:data")
include(":core:repository-api")
include(":core:repository-impl")
include(":core:database")
include(":core:model")
include(":core:mapper")
include(":core:remote-data-source-api")
include(":core:remote-data-source-impl")
include(":core:utils")
