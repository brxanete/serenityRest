Feature: Eliminacion de usuario

  Un usuario admin va a realizar la eliminacion de un usuario

  @ApiTest
  @Delete
  Scenario: Eliminacion exitosa de usuario

    Given Bryan es un admin que debe realizar la eliminacion de un usuario
    When Envia la informacion requerida para la eliminacion
    Then El debe validar la eliminacion correcta de datos