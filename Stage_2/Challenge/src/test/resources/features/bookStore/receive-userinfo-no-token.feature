Feature: Obtener informacion de un user con un token invalido
  Como tester del API Bookstore
  Quiero solicitar la informacion de un usuario
  Para validar que el endpoint falle por enviar un token de autorizacion invalido

  @User_information_with_no_token
  Scenario: Obtener informacion del usuario usando un token invalido
    Given Dado un UserID valido "5c42384c-7d50-4d0f-9fd9-0aa6dcb604f1"
    And que preparo un token invalido "AB"
    When El usuario envia la peticion con token invalido
    Then La respuesta debe devolver status 401
    And el mensaje de error debe ser "User not authorized!"



