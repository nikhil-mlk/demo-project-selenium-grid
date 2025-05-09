pipeline{
agent any
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
                dockerImage = docker.build("demo-project-1")
                }
             }
    }
   }

}