#!/user/bin/env groovy

def call() {
    echo "building the application for branch $BRANCH NAME"
    sh 'mvn package'
}
