// This Jenkinsfile runs on our Windows 10 Jenkins server
node {

    // Clean workspace before doing anything
    deleteDir()

    try {
        stage ('Clone') {
            checkout scm
        }
        stage ('preparations') {
            bat "D:\\Programs\\gradle\\gradle-5.0\\bin\\gradle.exe setupCiWorkspace"
        }
        stage('Build') {
            bat "D:\\Programs\\gradle\\gradle-5.0\\bin\\gradle.exe build"
        }
        stage ('Tests') {
        }
    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}