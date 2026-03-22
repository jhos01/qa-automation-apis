# Challenge 1 & 2 - Automatizacion de pruebas API con serializacion y desearializacion de los requests

El Challenge 1 & 2 consisten en 1 proyecto de automatizacion de pruebas API usando Rest Assured, Java, Cucumber y Maven. 
## Seccion 1 
Objetivo/ Historia de Usuario

Gestion de Publicaciones y Comentarios: En este proyecto quiero poder listar usuarios para validar que la API [JSON Placeholder](https://jsonplaceholder.typicode.com) funcione correctamente y que los datos sean consistentes.

📁 Carpeta: Stage_2/Challenge

## Criterios de aceptación

BaseUrl: https://jsonplaceholder.typicode.com"

Listar usuarios:

- GET /users debe devolver HTTP 200.
- La lista no debe estar vacía y cada usuario debe tener id, name, username y email.

## 🧪 Casos de Prueba 
Archivo .feature
```
Feature: Gestión de comentarios en la aplicación

Scenario: CP01 El usuario crea un comentario exitosamente
  Given que el usuario quiere agregar un comentario a un post existente
  And el usuario proporciona la siguiente información:
    | postId | nombre | email             | comentario                        |
    | 1      | Juan   | juan@email.com    | Este es un comentario de prueba   |
  When el usuario envía la solicitud para crear el comentario
  Then el sistema debe registrar el comentario correctamente
  And debe responder con un código 201
  And el comentario registrado debe incluir un ID generado por el sistema
  And los datos enviados deben coincidir con la información registrada

Scenario: CP02 El usuario consulta los comentarios de un post
  Given que el usuario quiere ver los comentarios asociados a un post específico
  And el post tiene comentarios registrados
  When el usuario solicita ver los comentarios del post con ID 1
  Then el sistema debe devolver la lista de comentarios existentes
  And debe responder con un código 200
  And cada comentario debe incluir un ID, nombre, email válido y contenido
  And el email de cada comentario debe contener "@"

```


## Ejecución 
1. Clona o descarga el repositorio https://github.com/jhos01/qax-automation-apis.git
2. Abre una nueva terminal en la carpeta raíz qax-automation-apis.
3. Cambia al directorio del proyecto con el comando: cd Stage_2/Challenge
4. Ejecuta el siguiente comando: "mvn test"

## Seccion 2: Automatizacion de la API Book Store

Objetivo/ Historia de Usuario

Registro y acceso seguro a la Book Store API
En este proyecto como usuario/automatizador de pruebas, quiero poder registrar un usuario, obtener un token de autenticación y consumir endpoints protegidos con ese token, para verificar que la API BookStore [BookStore](https://demoqa.com/swagger/) maneja correctamente el alta de usuarios, la emisión de tokens y el acceso autorizado a información sensible.

BaseUrl: https://demoqa.com

### Flujo del usuario para agregar un libro
- POST /Account/v1/User → crear usuario con userName y password.
- POST /Account/v1/GenerateToken → obtener token JWT con userName y password.
- GET /Account/v1/User/{userId} → obtener información del usuario, usando Authorization: Bearer .
- GET BookStore/v1/Books Obtener la informacion de los libros
- POST BookStore/v1/Books → Agregar un libro al usuario, usando Authorization: Bearer .

📁 Carpeta: Stage_2/Challenge

## Criterios de aceptación
Automatizar un flujo completo de interacción con la API de libros: desde buscar un libro, extraer el ISBN del primer libro de la lista, hasta agregarlo a un usuario existente

## 🧪 Casos de Prueba 
1. Crear usuario con datos inválidos
```
    Dado: Enviar POST /Account/v1/User con:
    userName vacío o
    password que no cumple reglas.
    Entonces: Verificar que la API responda HTTP 400.
    Resultado esperado: status_code == 400 y el cuerpo contiene información de error.
```

2. Generar token con credenciales inválidas
```
  Dado: Usar credenciales incorrectas.
  Entonces: Verificar que la API responda HTTP 200.
  Resultado esperado: status_code en {200} y mensaje de autenticación fallida.`User authorization failed.`
```

3. Acceso protegido sin token o con token inválido
```
  Dado: Realizar petición sin header Authorization o con Bearer <invalid>.
  Entonces: Verificar que la API responda HTTP 401
  Resultado esperado: status_code en {401} y el cuerpo contiene mensaje de acceso denegado. `User not authorized!` y code `1200`
```


4. Buscar libro y agregarlo a un usuario
```
  Dado: Realizar GET /Books o endpoint equivalente para obtener la lista de libros, Extraer el primer ISBN del primer libro del array de resultados
  Cuando: Realiza la peticion para agregar el libro a un usuario
  Entonces: Usar ese ISBN para enviar POST /BookStore/v1/Book/ y agregar un nuevo libro al usuario.
  Resultado esperado:  Status code 201.   Verificar que la respuesta confirme que el libro fue agregado es correctamente.
```


## Ejecución 
1. Clona o descarga el repositorio https://github.com/jhos01/qax-automation-apis.git
2. Abre una nueva terminal en la carpeta raíz qax-automation-apis.
3. Cambia al directorio del proyecto con el comando: cd Stage_2/Challenge
4. Ejecuta el siguiente comando: "mvn test"
 
