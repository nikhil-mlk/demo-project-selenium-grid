pipeline{
agent any
environment {
        DOCKER_IMAGE = 'nmalik1986/selenium'
            }
    stages{
    stage('Pull Docker Image') {
                    steps {
                        script {
                            withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                                // Login to Docker Hub
                                sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
                                // Pull the Docker image from Docker Hub
                                sh "docker pull ${DOCKER_IMAGE}"
                            }
                        }
                    }
                }

    stage('Run Selenium Grid')
    {
        steps{
        script
        {
        sh "docker-compose -f grid.yml up -d"
        }
      }
    }

    stage('Run Test Cases')
    {
        steps{
        sh "docker-compose -f test-suites.yml up"
        script{
            if(fileExists('results/testng-failed.xml'))
            {
                error('failed tests cases')
            }
                }
    }
    }
}
post{
   always{
   sh "docker-compose -f grid.yml down"
   sh "docker-compose -f test-suites.yml down"
   archiveArtifacts artifacts: 'results/emailable-report.html', fingerprint: true
    }
   }
}