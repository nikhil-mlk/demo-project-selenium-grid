pipeline{
agent
{
label 'local-agent'
}
	environment {
		dockerImage = ''
		registry = 'pgts2023/mtoautomation'
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
   }

}