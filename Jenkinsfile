pipeline {
  agent any
  environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
	}
    stages {

        stage('Setup parameters') {
            steps {
                script {
                    properties([
                        parameters([
                        
                        choice(
                            choices: ['Bomber', 'Food', 'Kanibal', 'Tamos', 'Thunder', 'Tttan', 'Yelb'], 
                            name: 'Application'
                                 
                                ),

                          string(name: 'WARNTIME',
                             defaultValue: '0',
                            description: '''Warning time (in minutes) before starting upgrade'''),

                          string(
                                defaultValue: 'develop',
                                name: 'Please_leave_this_section_as_it_is',
                                trim: true
                            ),
                            
                          string(
                            defaultValue: '0.2.0',
                            name: 'assets',
			    description: 'Enter the image Tag to deploy assets',
                            trim: true
                            ),


                          string(
                            defaultValue: '0.2.0',
                            name: 'cart',
			    description: 'Enter the image Tag to deploy cart',
                            trim: true
                            ),

                          string(
                            defaultValue: '1.13.1',
                            name: 'dynamodb-local',
			    description: 'Enter the image Tag to deploy dynamodb-local',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'catalog',
			    description: 'Enter the image Tag to deploy catalog',
                            trim: true
                            ),

                          string(
                            defaultValue: '5.7.6',
                            name: 'mysql',
			    description: 'Enter the image Tag to deploy mysql',
                            trim: true
                            ),


                          string(
                            defaultValue: '0.2.0',
                            name: 'checkout',
			    description: 'Enter the image Tag to deploy checkout',
                            trim: true
                            ),


                          string(
                            defaultValue: '6.0-alpine',
                            name: 'redis',
			    description: 'Enter the image Tag to deploy redis',
                            trim: true
                            ),

                          string(
                            defaultValue: '3-management',
                            name: 'rabbitmq',
			    description: 'Enter the image Tag to deploy rabbitmq',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'orders',
			    description: 'Enter the image Tag to deploy orders',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'ui',
			    description: 'Enter the image Tag to deploy ui',
                            trim: true
                            ),                         

                        ])
                    ])
                }
            }
        }
 

    stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

    stage('Hello') {
      when{   
          expression {
            env.Application == 'Bomber' }
            }
            steps {
                sh '''
                      docker tag  devopseasylearning2021/assets:0.2.0           devopseasylearning2021/assets:$assets
                      docker tag  devopseasylearning2021/cart:0.2.0             devopseasylearning2021/cart:$cart
                      docker tag  devopseasylearning2021/dynamodb-local:1.13.1          devopseasylearning2021/dynamodb-local:$dynamodb-local 
                      docker tag  devopseasylearning2021/catalog:0.2.0           devopseasylearning2021/catalog:$catalog 
                      docker tag   devopseasylearning2021/mysql:5.7.6            devopseasylearning2021/mysql:$mysql
                      docker tag   devopseasylearning2021/checkout:0.2.0          devopseasylearning2021/checkout:$checkout
                      docker tag   devopseasylearning2021/redis:6.0-alpine           devopseasylearning2021/redis:$redis 
                      docker tag   devopseasylearning2021/orders:0.2.0           devopseasylearning2021/orders:$orders
                      docker tag   devopseasylearning2021/rabbitmq:3-management           devopseasylearning2021/rabbitmq:$rabbitmq
                      docker tag    devopseasylearning2021/ui:0.2.0              devopseasylearning2021/ui:$ui
                      
                      
                      docker push  devopseasylearning2021/assets:$assets
                      docker push  devopseasylearning2021/cart:$cart
                      docker push          devopseasylearning2021/dynamodb-local:$dynamodb-local 
                      docker push   devopseasylearning2021/catalog:$catalog 
                      docker push   devopseasylearning2021/mysql:$mysql
                      docker push    devopseasylearning2021/checkout:$checkout
                      docker push       devopseasylearning2021/redis:$redis 
                      docker push   devopseasylearning2021/orders:$orders
                      docker push            devopseasylearning2021/rabbitmq:$rabbitmq
                      docker push   devopseasylearning2021/ui:$ui
                '''
            }
        }
    }


post {
    always {
      script {
        notifyUpgrade(currentBuild.currentResult, "POST")
      }
    }
    
  }

}





def notifyUpgrade(String buildResult, String whereAt) {
  if (Please_leave_this_section_as_it_is == 'origin/develop') {
    channel = 'development-alerts'
  } else {
    channel = 'development-alerts'
  }
  if (buildResult == "SUCCESS") {
    switch(whereAt) {
      case 'WARNING':
        slackSend(channel: channel,
                color: "#439FE0",
                message: "Challenger: Upgrade starting in ${env.WARNTIME} minutes @ ${env.BUILD_URL}  Application CHALLENGER")
        break
    case 'STARTING':
      slackSend(channel: channel,
                color: "good",
                message: "Challenger: Starting upgrade @ ${env.BUILD_URL} Application CHALLENGER")
      break
    default:
        slackSend(channel: channel,
                color: "good",
                message: "Challenger: Upgrade completed successfully @ ${env.BUILD_URL}  Application CHALLENGER")
        break
    }
  } else {
    slackSend(channel: channel,
              color: "danger",
              message: "Challenger: Upgrade was not successful. Please investigate it immediately.  @ ${env.BUILD_URL}  Application CHALLENGER")
  }
}
