def call(Map pipelineParams) {
    def build_stage
    def build_type

    pipeline {
        agent {
            kubernetes{
                yaml libraryResource('maven-pod.yaml')
            }
        }
        stages {
            stage ("initialization") {
                steps {
                    script {
                        build_stage = pipelineParams.build_stage
                        build_type = pipelineParams.build_type
                    }
                }
            }
            stage ('build') {
                steps {
                    script {
                        if(build_type == "maven") {
                            sh """
                                echo 'MOCK: mvn clean dependencies:copy-dependencies package'
                            """
                        } else if(build_type == "gradle") {
                            sh """
                                echo 'MOCK: gradle build'
                            """
                        } else {
                            echo "Unknown build type"
                        }
                    }
                }
            }
        }
    }
}