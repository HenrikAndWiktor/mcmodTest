node {
    def gradlehome // NOT BIN DIR
    gradlehome="D:\\Programs\\gradle\\gradle-3.0\\" //Add \ or / (depends on OS) to gradlehome
    // Clean workspace before doing anything
    deleteDir()

    try {
        stage ('Clone') {
            checkout scm
        }
        stage ('preparations') {
            if (System.properties['os.name'].toLowerCase().contains('windows')) {
                println "it's Windows"
                bat gradlehome+"bin\\gradle.bat setupCiWorkspace --stacktrace"
            } else {
                println "it's not Windows"
                sh gradlehome+"bin/gradle setupCiWorkspace --stacktrace"
            }
        }
        stage('Build') {
            if (System.properties['os.name'].toLowerCase().contains('windows')) {
                println "it's Windows"
                bat gradlehome+"bin\\gradle.bat build --stacktrace"
            } else {
                println "it's not Windows"
                sh gradlehome+"bin/gradle build --stacktrace"
            }
        }
        stage ('Tests') {
        }
        stage ('Post') {
            archiveArtifacts allowEmptyArchive: true, artifacts: 'build/libs/*.jar', excludes: 'build/libs/*-sources.jar', fingerprint: true, onlyIfSuccessful: true
        }
    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}