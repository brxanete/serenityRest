Feature: Obtencion de usuarios

  Traemos una pagina especifica de usuarios con el fin de poder realizar consultas

  @ApiTest
  @Get
  Scenario: Obtencion exitosa de usuarios

    Given Bryan es un cliente que consulta usuarios
    When Envia la informacion requerida para la consulta
    Then El debe obtener la informacion de la pagina consultada
