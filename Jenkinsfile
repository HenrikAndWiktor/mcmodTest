// This Jenkinsfile runs on our Windows 10 Jenkins server
node {

    // Clean workspace before doing anything
    deleteDir()

    try {
        stage ('Clone') {
            checkout scm
        }
        stage ('preparations') {
            bat "D:\\Programs\\gradle\\gradle-3.0\\bin\\gradle.bat setupCiWorkspace --stacktrace"
        }
        stage('Build') {
            bat "D:\\Programs\\gradle\\gradle-3.0\\bin\\gradle.bat build --stacktrace"
        }
        stage ('Tests') {
        }
        stage ('Post') {
            archiveArtifacts allowEmptyArchive: true, artifacts: 'build/libs/*.jar', excludes: '*-sources.jar', fingerprint: true, onlyIfSuccessful: true
        }
    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}