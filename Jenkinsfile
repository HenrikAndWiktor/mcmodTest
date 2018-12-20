/*
MIT License

Copyright (c) 2018 Yellow Duck / Wiktor Eriksson

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
 */
node {
    def gradlehome // NOT BIN DIR
    gradlehome="D:\\Programs\\gradle\\gradle-3.0\\" //Add \ or / (depends on OS) to gradlehome
    // Clean workspace before doing anything
    deleteDir()
    checkout scm
    result = sh (script: "git log -1 | grep '[jenkins]'", returnStatus: true)
    if (result == 0) {
        echo "performing build..."
        try {
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
    } else {
        echo "not running..."
    }
}