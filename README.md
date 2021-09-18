# Java 11, SpringBoot, RabbitMQ, Cucumber Testing

## Start Test Environment
Before running service, please run test environment to initialize RabbitMQ environment
### In window:
`gradlew startTestEnvironment`
### In unix:
`./gradlew startTestEnvironment`

## Start Application within background terminal
Your service must be turned before running the cucumber test
### In window:
`gradlew runApp`
### In unix
`./gradlew runApp`
And stopping app, please run 
### In window:
`gradlew stopApp`
### In unix
`./gradlew stopApp`

## Start Cucumber test with command
### In window:
`graldew cucumberCli`
### In Unix
`./gradlew cucumberCli`
