pipeline {
  agent any
  stages {
    stage('Checkout Sources') {
      steps {
        git(url: 'https://github.com/bepoland-academy/projects-service.git', branch: 'development')
      }
    }
    stage('Compile') {
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Build Docker Image') {
      steps {
        sh 'docker build -t pl.betse.beontime/projects-service:latest .'
      }
    }
    stage('Tag Docker Image') {
      steps {
        sh '''docker tag pl.betse.beontime/projects-service:latest build-server:5000/pl.betse.beontime/projects-service
'''
      }
    }
    stage('Push Docker Image') {
      steps {
        sh 'docker push build-server:5000/pl.betse.beontime/projects-service'
      }
    }
    stage('Checkout Deployment Config') {
      steps {
        git(url: 'https://github.com/bepoland-academy/deployment-configuration.git', branch: 'development')
      }
    }
    stage('Docker Compose Pull') {
      steps {
        sh 'docker-compose -H main-server:2376 -f production/docker-compose.yml pull'
      }
    }
    stage('Deploy') {
      steps {
        sh 'docker-compose -H main-server:2376 -f production/docker-compose.yml up -d projects-service registry-service'
      }
    }
  }
}