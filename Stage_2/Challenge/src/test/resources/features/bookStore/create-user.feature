Feature: Creación de usuario, generación de token y validación de información

  Scenario: CP-1 — Crear usuario exitosamente
    Given el usuario prepara un request body válido para crear usuario
    When realiza una petición POST a User
    Then la respuesta tiene status 201 y contiene un userId

  Scenario: CP-2 — Generar token válido
    Given el usuario tiene credenciales válidas
    When realiza una petición POST a GenerateToken
    Then la respuesta tiene status 200 y contiene un token

  Scenario: CP-3 — Obtener info del usuario con token válido
    Given el usuario tiene un token válido y un userId existente
    When realiza una petición GET a obtener informacion del usuario
    Then la respuesta tiene status 200 y el userId corresponde al usuario creado