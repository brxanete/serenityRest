Feature: Obtencion del plan del usuario

Es necesario obtener los planes del usuario, en el momento de la consulta

  @ApiTest
  @GetPlans
  Scenario: Obtencion exitosa de los planes del usuario

    Given Bryan es un cliente que quiere validar su plan
    When Envia la informacion requerida para la consulta de planes
    Then El debe validar la obtencion correcta de la informacion