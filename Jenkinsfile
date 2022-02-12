@Library('common_shared_blog_lib') _

pipeline {
    agent {
        kubernetes{
            yaml libraryResource('maven-pod.yaml')
        }
    }
    stages {
        stage ("initialization") {
            script {
                props = readYaml file: 'Jenkinsfile.yaml'
                build_stage = props['build_stage']
                build_type = props['build']['type']
            }
        }
        stage ('build') {
            script {
                if(build_type == "maven") {
                    sh """
                        echo "MOCK: mvn clean dependencies:copy-dependencies package"
                    """
                }
                if else(build_type == "gradle") {
                    sh """
                        echo "MOCK: gradle build"
                    """
                }
                else {
                    echo "Unknown build type"
                }
            }
        }
    }
}