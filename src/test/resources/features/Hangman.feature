@tag
Feature: Validacao das regras do jogo da forca
  Como usuario eu quero que o jogo funcione corretamente.

  @tag1
  Scenario Outline: Validar cenarios de derrota
    Given O jogo iniciou
    And Eu digitei a palavra "<palavra>"
    When Eu digitei a letra 1 "<letra1>"
    And Eu digitei a letra 2 "<letra2>"
    And Eu digitei a letra 3 "<letra3>"
    And Eu digitei a letra 4 "<letra4>"
    And Eu digitei a letra 5 "<letra5>"
    And Eu digitei a letra 6 "<letra6>"
    Then Eu perdi o jogo

    Examples:
      | palavra | letra1 | letra2 | letra3 | letra4 | letra5 | letra6 |
      | banana  | e      | c      | d      | j      | t      | f      |
      | alface  | t      | d      | g      | i      | j      | b      |
      | repolho | a      | f      | c      | d      | u      | b      |
      | laranja | e      | b      | c      | d      | i      | o      |
      | mamao   | u      | b      | i      | d      | e      | j      |
      | pera    | q      | c      | i      | b      | w      | o      |


  @tag2
  Scenario Outline: Validar cenarios de vitoria
    Given O jogo iniciou
    And A palavra para adivinhar e "<palavra>"
    When Eu digitei a letra correta 1 "<letra1>"
    And Eu digitei a letra correta 2 "<letra2>"
    And Eu digitei a letra correta 3 "<letra3>"
    And Eu digitei a letra correta 4 "<letra4>"
    And Eu digitei a letra correta 5 "<letra5>"
    Then Eu ganhei o jogo

    Examples:
      | palavra | letra1 | letra2 | letra3 | letra4 | letra5 |
      | banana  | b      | n      | a      | w      | u      |
      | alface  | a      | l      | f      | c      | e      |
      | laranja | l      | r      | a      | n      | j      |