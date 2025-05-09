pipeline{
agent
{
label 'local-agent'
}
	environment {
		dockerImage = ''
		registry = 'nikhildocker1986/demoproject'
		registryCredential = 'DockerHub'
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
                def imageTag = "demo-project-${env.BUILD_NUMBER}"
                dockerImage = docker.build(imageTag)
                }
             }
    }
    stage('Push Image to Docker Hub')
    {
        steps
        {
            script
            {
                def imageTag = "demo-project-${env.BUILD_NUMBER}"
                withCredentials([usernamePassword(credentialsId: "${registryCredential}", passwordVariable: 'pass', usernameVariable: 'user')]) {
                bat "docker login --username=${user} --password=${pass}"
                bat "docker tag ${imageTag} ${registry}:${env.BUILD_NUMBER}"
                bat "docker push ${registry}:${env.BUILD_NUMBER}"
            }
        }
    }
   }

}
}