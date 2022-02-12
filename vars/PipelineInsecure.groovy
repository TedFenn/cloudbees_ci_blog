def call(Map pipelineParams) {
    pipeline {
        agent {
            kubernetes{
                yaml libraryResource('maven-pod.yaml')
            }
        }
        stages {
            stage ("initialization") {
                script {
                    build_stage = pipelineParams.build_stage
                    build_type = pipelineParams.build_type
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
}