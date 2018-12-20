node {

    // Clean workspace before doing anything
    deleteDir()

    try {
        stage ('Clone') {
            checkout scm
        }
        stage ('preparations') {
            bat "gradle setupCiWorkspace"
        }
        stage('Build') {
            bat "gradle build"
        }
        stage ('Tests') {
        }
    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}