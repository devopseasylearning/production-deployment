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
       registry: deveopseasylearning2021
       pullPolicy: IfNotPresent
       repository:
         auth:
          name: weatherapp-auth
          tag: $weather_weatherapp-auth        
         mysql:
          name: mysql
          tag: $weather_mysql      
         ui:
          name: ui
          tag: $weather_ui
         weather:
          name: weather
          tag: $weather_weather  
                      
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





