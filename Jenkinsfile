pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'echo "Buld env"'
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