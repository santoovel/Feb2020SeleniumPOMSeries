pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'echo "Buld env"'
      }
    }
    
    stage('deploy on QA Env') {
      steps {
        sh 'echo "deploy on QA Env"'
      }
    }

    stage('QA Testing') {
      parallel {
        stage('QA Testing') {
          steps {
            sh 'echo "QA testing"'
          }
        }

        stage('API Test on QA') {
          steps {
            sh 'echo "api test on qa"'
          }
        }

      }
    }

    stage('Deploy on Stage') {
      steps {
        sh 'echo "Deploy on stage"'
      }
    }

    stage('Stage Testing') {
      steps {
        sh 'echo "Stage Testing"'
      }
    }

    stage('Deploy Prod') {
      steps {
        sh 'echo "Deploy prod"'
      }
    }

  }
}
