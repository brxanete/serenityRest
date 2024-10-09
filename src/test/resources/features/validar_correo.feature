Feature: Validacion correo usuario

  Es necesario realizar la validacion del correo registrado

  @ApiTest
  @MailValidate
  Scenario: Validacion exitosa del correo del usuario

    Given Bryan es un cliente que quiere validar su correo
    When Obtiene la informacion del usuario
    Then El debe validar que el correo sea el correcto