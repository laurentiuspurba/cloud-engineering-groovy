/*** BEGIN META {
  "name" : "A seed job",
  "comment" : "A seed job that will create a job for testing purpose"
  "parameters": []
  "authors": [
     { name: "Laurentius Purba"}
  ]
 */

String basePath = 'hosted-jenkins-two'

folder(basePath) {
   description 'DSL generated folder'
}

pipelineJob("$basePath/pipeline-seed-job") {
  description()
  // daysToKepp, numToKepp, artifactDaysToKeep, artifactNumToKeep
  logRotator(2, -1, 1, -1)

  definition {
    cps {
      sandbox(true)
      script('''
        pipeline {
          agent any
          stages {
            stage('Running simple job') {
              steps {
                echo 'Hello world'
              }
            }
            stage('Showing jenkins uid') {
              steps {
                sh 'id'
              }
            }
            stage('Process statistics') {
              steps {
                sh 'docker ps -a'
              }
            }
          }
        }
      '''.stripIndent().trim())
    }
  }
}
