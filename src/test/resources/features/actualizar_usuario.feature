Feature: Actualizacion de usuarios

  Con el fin de poder administrar mis productos bancarios yo como usuario quiero poder actualizar mi informacion

  @ApiTest
  @Update
  Scenario: Actualizacion exitoso de usuario

    Given Bryan es un cliente que quiere actualizar su usuario
    When Envia la informacion requerida para la actualizacion
    Then El debe validar la actualizacion correcta de datos