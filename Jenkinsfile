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
        stage('Upload to S3') {
            steps {
                withAWS(region:"us-east-1",credentials:"global_usnp_aws_r") {
                    s3Upload(file:"artifect.zip", bucket:"fusionteam-tho-dev",path:"artifect.zip")
                }
            }
        }  
        stage('Docker Build') {
            steps {
                sh "docker build -t ft-tho-dev:latest ."
                sh "docker tag ft-tho-dev:v1 823140877761.dkr.ecr.us-east-1.amazonaws.com/ft-tho-dev:latest"
            }
        }
        
        stage('Docker Push') {
            environment {
                AWS_ECR_LOGIN='true'
                AWS_ECR_LOGIN_REGISTRY_IDS='823140877761'
                AWS_DEFAULT_REGION='us-east-1'
                AWS_REGION='us-east-1'
            }              
            steps {
                echo 'Docker Push..'
                
                withDockerRegistry([credentialsId: 'global_usnp_aws_r', url: 'https://823140877761.dkr.ecr.us-east-1.amazonaws.com']) {
                    sh "docker push 823140877761.dkr.ecr.us-east-1.amazonaws.com/ft-tho-dev:latest"
                }

            }
        }  
                     
    }
}
