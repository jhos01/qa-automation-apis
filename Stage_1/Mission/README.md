# Mission

- 📁 Carpeta: `Stage_1/Mission`

## Implementacion de pruebas automatizadas para consultar la API "Los Simpson" 
 - **Objetivo / Historia de usuario**

Historia de usuario: Exponer listado y detalle de personajes de The Simpsons con paginación

- Como consumidor externo de la API (aplicaciones cliente y servicios internos)
- Quiero obtener un listado paginado de personajes y consultar el detalle por id
- Para mostrar información consistente y navegable en mis aplicaciones y reutilizarla en otros servicios.


## ✅ Casos de Prueba

   CP01 - El usuario puede ver personajes listados por pagina de manera satisfactoria
   
   - Given la API esta disponible en https://thesimpsonsapi.com/api con 
   - When realice la peticion GET a /characters
   - Then el codigo de respuesta debe ser 200 
   
   CP02 - El usuario pueder ver los personajes 1-20 si la pagina es 1 
   
   - Given la API esta disponible en https://thesimpsonsapi.com/api con
   - When realice la peticion GET a characters
   - Then el tamaho del array results es 20
   - And el codigo de respuesta debe ser 200
   
   CP03 - El usuario puede ver 20 personajes por cualquier pagina 
   
   - Given la API esta disponible en https://thesimpsonsapi.com/api con 
   - When realice la peticion GET a /characters
   - Then el tamaho del array results es 20
   - And el codigo de respuesta debe ser 200
   
   CP04 - El usuario puede ver count,next,prev,pages, y results para personajes listados por paginacion
   
   - Given la API esta disponible en https://thesimpsonsapi.com/api con
   - When realice la peticion GET a characters
   - Then el codigo de respuesta debe ser 200
   - And la respuesta debe tener los campos: count,next,prev,pages, y results
     
   CP05 - El usuario recibe 1 error si parametro page = abc
   
   - Given la API esta disponible en https://thesimpsonsapi.com/api
   - When realice la peticion GET a /characters
   - Then el codigo de respuesta debe ser 400
   
   
   
   
   
   CP06 - El usuario recibe 1 error si parametro page = 0
   
   Given la API esta disponible en https://thesimpsonsapi.com/api 
   
    	Query Parameters:
   
    		Page:0
   When realice la peticion GET a /characters
   Then el codigo de respuesta debe ser 400
   
   
   
   
   
   CP07 - El usuario recibe la pagina 1 si no envia el parametro "page"
   
   Given la API esta disponible en https://thesimpsonsapi.com/api
   When realice la peticion GET a /characters
   Then el codigo de respuesta debe ser 200
   And la respuesta debe contener los Ids = {1,2..20}
   
   
   
   
   
   CP08 - El usuario recibe el total de personajes = 1182
   
   Given la API esta disponible en https://thesimpsonsapi.com/api
   When realice la peticion GET a /characters
   Then el codigo de respuesta debe ser 200
   And la respuesta debe contener el campo count = 1182
   
   
   
   
   
   CP09 - El usuario recibe url con page = 7 en el campo next si la pagina de personajes es 6
   
   Given la API esta disponible en https://thesimpsonsapi.com/api con
   
    	Query Parameters:
   
    		Page:6
   
   When realice la peticion GET a /characters
   Then la respuesta debe contener el campo next = "https://thesimpsonsapi.com/api/characters?page=7" 
   and el codigo de respuesta debe ser 200
   
   
   
   
   
   CP10 - El usuario recibe informacion de 1 personaje por ID de manera satisfactoria
   
   Given la API esta disponible en https://thesimpsonsapi.com/api 
   &nbsp;	Path parameters:
   
   &nbsp;		id: {n}
   
   When realice la peticion GET a /characters/:id
   Then el codigo de respuesta debe ser 200
   
   
   
   
   
   CP11 - El usuario puede ver el id,age,birthdate,gender,name,occupation,portrait\_path,phrases,y status de 1 personaje
   
   Given la API esta disponible en https://thesimpsonsapi.com/api 
   
    	Path parameters:
   
    		id:1
   
   When realice la peticion GET a /characters/:id
   Then el codigo de respuesta debe ser 200
   And la respuesta debe contener los campos: id,age,birthdate,gender,name,occupation,portrait\_path,phrases
   
   
   
   
   
   CP12 - El usuario recibe el birthdate de 1 personaje con el formato YYYY-MM-DD
   
   Given la API esta disponible en https://thesimpsonsapi.com/api con
   
    	Path parameters:
   
    		id:2
   
   When realice la peticion GET a /characters/:id
   
   Then el Código de respuesta debe ser 200
   And la respuesta tiene el campo birthdate
   And la respuesta contiene el campo birthdate con formato ^\\d{4}-\\d{2}-\\d{2}$.
   
   
   
   
   
   CP13 - El usuario recibe la direccion de la imagen de 1 personaje elegido
   
   Given la API esta disponible en https://thesimpsonsapi.com/api 
   
    	Path parameters:
   
    		id:3
   
   When realice la peticion GET a /characters/:id
   Then el Código de respuesta debe ser 200
   And la respuesta contiene el campo portrait\_path
   
   
   
   CP14 - El usuario recibe al menos 1 phrase por personaje (phrases is not null)
   
   Given la API esta disponible en https://thesimpsonsapi.com/api 
   
    	Path parameters:
   
    		id:4
   
   When realice la peticion GET a /characters/:id
   Then el Código de respuesta debe ser 200
   And la respuesta contiene el objeto phrases
   
   
   
   
   
   CP15 - El usuario recibe informacion de Homero para el id = 1
   
   Given la API esta disponible en https://thesimpsonsapi.com/api con
   
    	Path parameters:
   
    		id:1
   
   When realice la peticion GET a /characters/:id
   Then el Código de respuesta debe ser 200
   And la respuesta contiene el campo name =  "Homer Simpson"
   
   
   
   CP16 - El usuario recibe 1 error cuando envia 1 ID inexistente (404 Not found)
   
   Given la API esta disponible en https://thesimpsonsapi.com/api con
   
    	Path parameters:
   
    		id:0
   
   When realice la peticion GET a /characters:id
   Then el Código de respuesta debe ser 404
   
   
   
   
   
   CP17 - El usuario recibe 1 error cuando envia 1 ID invalido como Id = abc
   
   Given la API esta disponible en https://thesimpsonsapi.com/api con
    	Path parameters:
   
    		id:abc
   
   When realice la peticion GET a /characters/:id
   Then el Código de respuesta debe ser 400
   
   
   
   CP18 - El usuario recibe la lista de personajes ordenados por id de forma ascendente
   
   Given la API esta disponible en https://thesimpsonsapi.com/api con
   
    	Query Parameters:
   
    		Page:6
   
   When realice la peticion GET a /api/characters
   Then el Código de respuesta debe ser 200
   And los Ids deben estar ordenados de forma ascendente de 1 a 20
   
   
   
   CP19 - El usuario recibe informacion de Gary Chalmers para el id = 19
   
   Given la API esta disponible en https://thesimpsonsapi.com/api con
   
    	Path parameters:
   
    		id:19
   
   When realice la peticion GET a /characters/19
   Then el Código de respuesta debe ser 200
   And la respuesta contiene el campo name =  "Gary Chalmers"
   
   
   
   CP20 - El usuario recibe informacion de Smithers para el id = 14
   
   Given la API esta disponible en https://thesimpsonsapi.com/api con
   
    	Path parameters:
   
    		id:14
   
   When realice la peticion GET a /characters/19
   Then el Código de respuesta debe ser 200
   And la respuesta contiene el campo name =  "Waylon Smithers, Jr."
   
   
   




  
  - **Ejecución** (comandos o pasos)

   Challenge 1:
   
   1. Descarga la coleccion de Postman Api Testing - jhos01 QAXPERT.postman_collection
   2. Importar la coleccion en Postman
   3. Ejecutar los requests

   Challenge 2:

   1. Descargar el repositorio
   2. Abre 1 nueva terminal en la carpeta raiz
   3. Cambia al directorio del proyecto con el comando: cd Stage_1\Challenge\Challenge2
   4. Tener 1 terminal abierta en el directorio anterior 
   5. Ejecuta el comando "mvn test" para inicializar las pruebas
   

