node {

    // Clean workspace before doing anything
    deleteDir()

    try {
        stage ('Clone') {
            checkout scm
        }
        stage ('preparations') {
            bat "gradlew.bat setupCiWorkspace"
        }
        stage('Build') {
            bat "gradlew.bat build"
        }
        stage ('Tests') {
        }
    } catch (err) {
        currentBuild.result = 'FAILED'
        notifyFailed()
        throw err
    }
}