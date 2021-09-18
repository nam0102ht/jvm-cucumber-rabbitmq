Feature: rabbitmq to push to queue
  Scenario Outline: push message to queue
    Given push any message to queue input "<Messages>"
    When receive message from queue output
    Then validate message
    Examples:
    |   Messages    |
    |   message.txt |