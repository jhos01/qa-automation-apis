Feature: Obtener el primer libro disponible y asignarlo a un usuario
  Como tester de API de BookStore
  Quiero obtener el ISBN del primer libro disponible
  Para asignar 1 libro a un usuario

  @Assign_book
  Scenario: Obtener primer libro y asignarlo al usuario
    Given que tengo 1 userID valido "df19805f-01a3-41c2-830a-e1c0cddfcf03"
    And que tengo un token valido "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6Im5pbmphRm9yVGVzdGluZzE1MGU1IiwicGFzc3dvcmQiOiJUZXN0QEAxMjM1IiwiaWF0IjoxNzYzMzUzNzAwfQ.swU-gnK3YpWjoVx17hhpmZ72t6Qa70N4DC0K0cFowyw"
    When Obtengo la lista de libros disponibles
    And Obtengo el ISBN del primer libro
    And asigno ese libro al usuario
    Then la respuesta debe tener status 201