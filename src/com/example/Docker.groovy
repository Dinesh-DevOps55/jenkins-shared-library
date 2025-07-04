#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script //This script will allow us to execute pipeline commands.
    }

    def buildDockerImage(String imageName) {
        script.echo "Building the Docker image..."
        script.sh "docker build -t $imageName ."
    }

    def dockerLogin() {

        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
        }
    }

    def dockerPush(String imageName) {
        script.sh "docker push $imageName"
    }
}