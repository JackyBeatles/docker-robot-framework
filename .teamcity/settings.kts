import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.buildSteps.script
version = "2021.2"

project {

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }
    steps {
        script {
            name = "Robot Test"
            scriptContent = "docker run --rm --shm-size=1g -e BROWSER=firefox -v %system.teamcity.build.checkoutDir%/test:/opt/robotframework/tests -v %system.teamcity.build.checkoutDir%:/opt/robotframework/reports ppodgorsek/robot-framework:latest"
        }
    }
    triggers {
        vcs {
        }
    }
})