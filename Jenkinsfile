pipeline {
  agent any
  stages {
    stage('TestRun On Dev') {
      steps {
        sh 'mvn clean install -Denv="dev"'
      }
    }

    stage('Test Run on QA') {
      steps {
        sh 'mvn clean install -Denv="qa"'
      }
    }

  }
}