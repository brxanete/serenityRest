Feature: Registro de usuarios

  Con el fin de poder administrar mis productos bancarios yo como usuario quiero poder registrarme
  Para poder realizar pagos y ejecutar operaciones sobre mis productos

  @ApiTest
  @Register
  Scenario: Registro exitoso de usuario

    Given Bryan es un cliente que quiere administrar sus productos
    When Envia la informacion requerida para el registro
    Then El debe obtener una cuenta virtual para poder ingresar cuando lo requiera



