pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Packaging'){
            steps {
                sh "git archive -v -o artifect.zip --format=zip HEAD"
            }
        }
        stage('Docker Build') {
            steps {
                sh "docker build -t kfengbest/mst-js:latest ."
            }
        }
        stage('Docker Push') {
            agent any
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                sh 'docker push kfengbest/mst-js:latest'
                }
            }
        }  
        stage('Upload to S3') {
            steps {
                withAWS(region:"us-east-1",credentials:"global_usnp_aws_r") {
                    s3Upload(file:"artifect.zip", bucket:"cp-docker2-stg-s3-eb",path:"artifect.zip")
                }
            }
        }
    }
}
