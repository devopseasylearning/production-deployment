pipeline {
  agent any
  environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
	}
options {
    buildDiscarder(logRotator(numToKeepStr: '20'))
    disableConcurrentBuilds()
    timeout (time: 60, unit: 'MINUTES')
    timestamps()
  }
    stages {
        stage('Setup parameters') {
            steps {
                script {
                    properties([
                        parameters([
                        
                        choice(
                            choices: ['KFC', 'Bomber', 'Weather', 'Titan'], 
                            name: 'Application'
                                 
                                ),


                          string(
                            defaultValue: '001',
                            name: 'KFC_ui',
			    description: 'Enter the image Tag to deploy KFC ui',
                            trim: true
                            ),

                          string(
                            defaultValue: '001',
                            name: 'KFC_consumer',
			    description: 'Enter the image Tag to deploy KFC consumer',
                            trim: true
                            ),

                          string(
                            defaultValue: '001',
                            name: 'KFC_api',
			    description: 'Enter the image Tag to deploy KFC api',
                            trim: true
                            ),

                          string(
                            defaultValue: 'v1.0.1',
                            name: 'titan_ui',
			    description: 'Enter the image Tag to deploy titan ui',
                            trim: true
                            ),

                          string(
                            defaultValue: 'v1.0.1',
                            name: 'titan_db',
			    description: 'Enter the image Tag to deploy titan db',
                            trim: true
                            ),

                          string(
                            defaultValue: 'v1.0.1',
                            name: 'titan_api',
			    description: 'Enter the image Tag to deploy titan api',
                            trim: true
                            ),


                          string(name: 'WARNTIME',
                             defaultValue: '0',
                            description: '''Warning time (in minutes) before starting upgrade'''),

                          string(
                                defaultValue: 'production',
                                name: 'Please_leave_this_section_as_it_is',
                                trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'bomber_assets',
			    description: 'Enter the image Tag to deploy Bomber assets',
                            trim: true
                            ),


                          string(
                            defaultValue: '0.2.0',
                            name: 'bomber_cart',
			    description: 'Enter the image Tag to deploy Bomber cart',
                            trim: true
                            ),

                          string(
                            defaultValue: '1.13.1',
                            name: 'bomber_dynamodb',
			    description: 'Enter the image Tag to deploy Bomber dynamodb',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'bomber_catalog',
			    description: 'Enter the image Tag to deploy Bomber catalog',
                            trim: true
                            ),

                          string(
                            defaultValue: '5.7.6',
                            name: 'bomber_mysql',
			    description: 'Enter the image Tag to deploy Bomber mysql',
                            trim: true
                            ),


                          string(
                            defaultValue: '0.2.0',
                            name: 'bomber_checkout',
			    description: 'Enter the image Tag to deploy Bomber checkout',
                            trim: true
                            ),


                          string(
                            defaultValue: '6.0-alpine',
                            name: 'bomber_redis',
			    description: 'Enter the image Tag to deploy Bomber redis',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.0.2',
                            name: 'bomber_rabbitmq',
			    description: 'Enter the image Tag to deploy Bomber rabbitmq',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'bomber_orders',
			    description: 'Enter the image Tag to deploy Bomber orders',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.2.0',
                            name: 'bomber_ui',
			    description: 'Enter the image Tag to deploy Bomber ui',
                            trim: true
                            ),                         



                          string(
                            defaultValue: '0.0.1',
                            name: 'weather_auth',
			    description: 'Enter the image Tag to deploy weather auth',
                            trim: true
                            ),

                          string(
                            defaultValue: '8.0.32',
                            name: 'weather_mysql',
			    description: 'Enter the image Tag to deploy weather mysql',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.0.1',
                            name: 'weather_ui',
			    description: 'Enter the image Tag to deploy weather ui',
                            trim: true
                            ),

                          string(
                            defaultValue: '0.0.1',
                            name: 'weather_weather',
			    description: 'Enter the image Tag to deploy weather weather',
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
                      
                      docker tag  devopseasylearning2021/assets:0.2.0           devopseasylearning2021/assets:$bomber_assets
                      docker tag  devopseasylearning2021/cart:0.2.0             devopseasylearning2021/cart:$bomber_cart
                      docker tag  devopseasylearning2021/dynamodb:1.13.1          devopseasylearning2021/dynamodb:$bomber_dynamodb
                      docker tag  devopseasylearning2021/catalog:0.2.0           devopseasylearning2021/catalog:$bomber_catalog 
                      docker tag   devopseasylearning2021/mysql:5.7.6            devopseasylearning2021/mysql:$bomber_mysql
                      docker tag   devopseasylearning2021/checkout:0.2.0          devopseasylearning2021/checkout:$bomber_checkout
                      docker tag   devopseasylearning2021/redis:6.0-alpine           devopseasylearning2021/redis:$bomber_redis 
                      docker tag   devopseasylearning2021/orders:0.2.0           devopseasylearning2021/orders:$bomber_orders
                      docker tag   devopseasylearning2021/rabbitmq:0.0.2          devopseasylearning2021/rabbitmq:$bomber_rabbitmq
                      docker tag    devopseasylearning2021/ui:0.2.0              devopseasylearning2021/ui:$bomber_ui
                      
                      
                      docker push  devopseasylearning2021/assets:$bomber_assets
                      docker push  devopseasylearning2021/cart:$bomber_cart
                      docker push          devopseasylearning2021/dynamodb:$bomber_dynamodb 
                      docker push   devopseasylearning2021/catalog:$bomber_catalog 
                      docker push   devopseasylearning2021/mysql:$bomber_mysql
                      docker push    devopseasylearning2021/checkout:$bomber_checkout
                      docker push       devopseasylearning2021/redis:$bomber_redis 
                      docker push   devopseasylearning2021/orders:$bomber_orders
                      docker push            devopseasylearning2021/rabbitmq:$bomber_rabbitmq
                      docker push   devopseasylearning2021/ui:$bomber_ui
                '''
            }
        }


stage('Update BOMBER values file') {
  when{   
      expression {
      env.Application == 'Bomber' }
            }

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
  registry: devopseasylearning2021
  pullPolicy: IfNotPresent
  repository:
    assets:
     name: assets
     tag: $bomber_assets        
    cart:
     name: cart
     tag: $bomber_cart      
    dynamodb:
     name: dynamodb
     tag: $bomber_dynamodb
    catalog:
     name: catalog
     tag: $bomber_catalog 
    mysql:
     name: mysql
     tag: $bomber_mysql 
    checkout:
     name: checkout
     tag: $bomber_checkout
    redis:
     name: redis
     tag: $bomber_redis
    orders:
     name: orders
     tag: $bomber_orders
    rabbitmq:
     name: rabbitmq
     tag: $bomber_rabbitmq
    ui:
     name: ui
     tag: $bomber_ui   

EOF


git config --global user.name "devopseasylearning"
git config --global user.email info@devopseasylearning.com
git add -A 
git commit -m "commit from Jenkins" || true
git push https://devopseasylearning:$TOKEN@github.com/devopseasylearning/production-deployment.git  || true
	            '''
	          }

	        }

	      }


	    }





stage('Update weather values file') {
  when{   
      expression {
      env.Application == 'Weather' }
            }

	      steps {
	        script {
	          withCredentials([
	            string(credentialsId: 'github-token2', variable: 'TOKEN'),
	          ]) {

	            sh '''
                 rm -rf production-deployment || true
                 git clone https://devopseasylearning:$TOKEN@github.com/devopseasylearning/production-deployment.git 
                 cd production-deployment/weather
    
cat <<EOF > values.yaml
    replicaCount: 1
    image:
      registry: devopseasylearning2021
      pullPolicy: IfNotPresent
      repository:
        auth:
         name: weatherapp-auth
         tag: $weather_auth        
        mysql:
         name: weatherapp-mysql
         tag: $weather_mysql      
        ui:
         name: weatherapp-ui
         tag: $weather_ui
        weather:
         name: weatherapp-weather
         tag: $weather_weather 
EOF
                 git config --global user.name "devopseasylearning"
                 git config --global user.email info@devopseasylearning.com
                 git add -A 
                 git commit -m "commit from Jenkins" || true
                 git push https://devopseasylearning:$TOKEN@github.com/devopseasylearning/production-deployment.git  || true
	            '''
	          }

	        }

	      }

	    }





stage('Update titan values file') {
  when{   
      expression {
      env.Application == 'Titan' }
            }

	      steps {
	        script {
	          withCredentials([
	            string(credentialsId: 'github-token2', variable: 'TOKEN'),
	          ]) {

	            sh '''
                 rm -rf production-deployment || true
                 git clone https://devopseasylearning:$TOKEN@github.com/devopseasylearning/production-deployment.git 
                 cd production-deployment/titan
cat <<EOF > prod-values.yaml
    replicaCount: 3
    image:
      repository: devopseasylearning2021/canary-prod 
      pullPolicy: IfNotPresent
      tag: "$titan_ui"
EOF
                 
                 git config --global user.name "devopseasylearning"
                 git config --global user.email info@devopseasylearning.com
                 git add -A 
                 git commit -m "commit from Jenkins" || true
                 git push https://devopseasylearning:$TOKEN@github.com/devopseasylearning/production-deployment.git  || true
	            '''
	          }

	        }

	      }

	    }




stage('Update KFC values file') {
  when{   
      expression {
      env.Application == 'KFC' }
            }

	      steps {
	        script {
	          withCredentials([
	            string(credentialsId: 'github-token2', variable: 'TOKEN'),
	          ]) {

	            sh '''
                 rm -rf production-deployment || true
                 git clone https://devopseasylearning:$TOKEN@github.com/devopseasylearning/production-deployment.git 
                 cd production-deployment/kfc
cat <<EOF > prod-values.yaml
    replicaCount: 3
    image:
      repository: devopseasylearning2021/kfc
      pullPolicy: IfNotPresent
      tag: "$KFC_ui"
EOF
                 
                 git config --global user.name "devopseasylearning"
                 git config --global user.email info@devopseasylearning.com
                 git add -A 
                 git commit -m "commit from Jenkins" || true
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
  if (Please_leave_this_section_as_it_is == 'origin/production') {
    channel = 'go-no-go-production'
  } else {
    channel = 'go-no-go-production'
  }
  if (buildResult == "SUCCESS") {
    switch(whereAt) {
      case 'WARNING':
        slackSend(channel: channel,
                color: "#439FE0",
                message: "Production: Upgrade starting in ${env.WARNTIME} minutes @ ${env.BUILD_URL}  Application $Application")
        break
    case 'STARTING':
      slackSend(channel: channel,
                color: "good",
                message: "Production: Starting upgrade @ ${env.BUILD_URL} Application $Application")
      break
    default:
        slackSend(channel: channel,
                color: "good",
                message: "Production: Upgrade completed successfully @ ${env.BUILD_URL}  Application $Application")
        break
    }
  } else {
    slackSend(channel: channel,
              color: "danger",
              message: "Production: Upgrade was not successful. Please investigate it immediately.  @ ${env.BUILD_URL}  Application $Application")
  }
}
