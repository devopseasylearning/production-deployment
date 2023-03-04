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
                            choices: ['Bomber', 'Food', 'Kanibal', 'Tamos', 'Thunder', 'Titan', 'Yelb'], 
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
                            name: 'BOMBERassets',
			    description: 'Enter the image Tag to deploy assets',
                            trim: true
                            ),


                          string(
                            defaultValue: '0.2.0',
                            name: 'BOMBERcart',
			    description: 'Enter the image Tag to deploy cart',
                            trim: true
                            ),

                          string(
                            defaultValue: '1.13.1',
                            name: 'BOMBERdynamodb',
			    description: 'Enter the image Tag to deploy dynamodb',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'BOMBERcatalog',
			    description: 'Enter the image Tag to deploy catalog',
                            trim: true
                            ),

                          string(
                            defaultValue: '5.7.6',
                            name: 'BOMBERmysql',
			    description: 'Enter the image Tag to deploy mysql',
                            trim: true
                            ),


                          string(
                            defaultValue: '0.2.0',
                            name: 'BOMBERcheckout',
			    description: 'Enter the image Tag to deploy checkout',
                            trim: true
                            ),


                          string(
                            defaultValue: '6.0-alpine',
                            name: 'BOMBERredis',
			    description: 'Enter the image Tag to deploy redis',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.0.2',
                            name: 'BOMBERrabbitmq',
			    description: 'Enter the image Tag to deploy rabbitmq',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'BOMBERorders',
			    description: 'Enter the image Tag to deploy orders',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'BOMBERui',
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

    stage('getting started') {
      when{   
          expression {
            env.Application == 'Bomber' }
            }
            steps {
                sh '''
                docker pull  devopseasylearning2021/assets:0.2.0          
                docker pull  devopseasylearning2021/cart:0.2.0            
                docker pull  devopseasylearning2021/dynamodb:1.13.1 
                docker pull  devopseasylearning2021/catalog:0.2.0         
                docker pull   devopseasylearning2021/mysql:5.7.6          
                docker pull   devopseasylearning2021/checkout:0.2.0       
                docker pull   devopseasylearning2021/redis:6.0-alpine     
                docker pull   devopseasylearning2021/orders:0.2.0         
                docker pull   devopseasylearning2021/rabbitmq:0.0.2
                docker pull    devopseasylearning2021/ui:0.2.0            
                      
                      docker tag  devopseasylearning2021/assets:0.2.0           devopseasylearning2021/assets:$BOMBERassets
                      docker tag  devopseasylearning2021/cart:0.2.0             devopseasylearning2021/cart:$BOMBERcart
                      docker tag  devopseasylearning2021/dynamodb:1.13.1          devopseasylearning2021/dynamodb:$BOMBERdynamodb
                      docker tag  devopseasylearning2021/catalog:0.2.0           devopseasylearning2021/catalog:$BOMBERcatalog 
                      docker tag   devopseasylearning2021/mysql:5.7.6            devopseasylearning2021/mysql:$BOMBERmysql
                      docker tag   devopseasylearning2021/checkout:0.2.0          devopseasylearning2021/checkout:$BOMBERcheckout
                      docker tag   devopseasylearning2021/redis:6.0-alpine           devopseasylearning2021/redis:$BOMBERredis 
                      docker tag   devopseasylearning2021/orders:0.2.0           devopseasylearning2021/orders:$BOMBERorders
                      docker tag   devopseasylearning2021/rabbitmq:0.0.2          devopseasylearning2021/rabbitmq:$BOMBERrabbitmq
                      docker tag    devopseasylearning2021/ui:0.2.0              devopseasylearning2021/ui:$BOMBERui
                      
                      
                      docker push  devopseasylearning2021/assets:$BOMBERassets
                      docker push  devopseasylearning2021/cart:$BOMBERcart
                      docker push          devopseasylearning2021/dynamodb:$BOMBERdynamodb 
                      docker push   devopseasylearning2021/catalog:$BOMBERcatalog 
                      docker push   devopseasylearning2021/mysql:$BOMBERmysql
                      docker push    devopseasylearning2021/checkout:$BOMBERcheckout
                      docker push       devopseasylearning2021/redis:$BOMBERredis 
                      docker push   devopseasylearning2021/orders:$BOMBERorders
                      docker push            devopseasylearning2021/rabbitmq:$BOMBERrabbitmq
                      docker push   devopseasylearning2021/ui:$BOMBERui
                '''
            }
        }


stage('Update values file') {

	      steps {
	        script {
	          withCredentials([
	            string(credentialsId: 'github-token2', variable: 'TOKEN'),
	          ]) {

	            sh '''
rm -rf production-deployment || true
git clone https://devopseasylearning:$TOKEN@github.com/devopseasylearning/production-deployment.git 
cd production-deployment/bomber
cat <<EOF > values.yaml
replicaCount: 1
image:
  registry: deveopseasylearning2021
  pullPolicy: IfNotPresent
  repository:
    assets:
     name: assets
     tag: $assets        
    cart:
     name: cart
     tag: $cart      
    dynamodb:
     name: dynamodb
     tag: $dynamodb
    catalog:
     name: catalog
     tag: $catalog 
    mysql:
     name: mysql
     tag: $mysql 
    checkout:
     name: checkout
     tag: $checkout
    redis:
     name: redis
     tag: $redis
    orders:
     name: orders
     tag: $orders
    rabbitmq:
     name: rabbitmq
     tag: $rabbitmq
    ui:
     name: ui
     tag: $ui   

EOF


git config --global user.name "devopseasylearning"
git config --global user.email info@devopseasylearning.com
git add -A 
git commit -m "commit from Jenkins"
git push https://devopseasylearning:$TOKEN@github.com/devopseasylearning/production-deployment.git  || true
	            '''
	          }

	        }

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
