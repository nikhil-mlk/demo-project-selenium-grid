pipeline{
agent
{
label 'local-agent'
}
	environment {
		dockerImage = ''
		registry = 'nikhildocker1986/demo-repository'
		registryCredential = 'DockerHub'
		IMAGE_TAG = "demo-project-${env.BUILD_NUMBER}"
	}
	stages{

    stage('Build Jar')
    {
        steps{
        script
        {
        bat 'mvn clean package -DskipTests'
        }
      }
    }
    stage('Build Docker Image')
    {
        steps{
         script
                {
                dockerImage = docker.build("${IMAGE_TAG}")
                }
             }
    }
    stage('Push Image to Docker Hub')
    {
        steps
        {
            script
            {
                withCredentials([usernamePassword(credentialsId: "${registryCredential}", passwordVariable: 'pass', usernameVariable: 'user')]) {
                bat "docker login --username=${user} --password=${pass}"
                bat "docker tag ${IMAGE_TAG} ${registry}:${env.BUILD_NUMBER}"
                bat "docker push ${registry}:${env.BUILD_NUMBER}"
            }
        }
    }
   }

}
}