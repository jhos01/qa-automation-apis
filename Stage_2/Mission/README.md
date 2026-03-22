
# Mission #2 - Automatización de APIs con GitHub REST API (GET & POST)

Proyecto de automatización de pruebas API enfocado en la **GitHub REST API**, utilizando **Java**, **Rest Assured**, **Cucumber** y **Maven**.

## Objetivo

El objetivo de esta misión es practicar automatización de APIs enfocada en operaciones de **lectura** y **creación** mediante los métodos **GET** y **POST**, aplicando buenas prácticas de automatización, validación de respuestas y modelado de datos.

Al finalizar este proyecto se cubren los siguientes puntos:

- Estructura de un proyecto de automatización de APIs con Rest Assured y Cucumber.
- Creación de modelos **POJOs** para mapear datos de repositorios e issues.
- Definición de escenarios de prueba en archivos `.feature` usando **Gherkin**.
- Implementación de **Step Definitions** en Java.
- Preparación y envío de requests **GET** y **POST** con headers, payloads y autenticación con token.
- Validación de respuestas de la API:
  - Status code
  - Body
  - Headers
- Diseño de flujos de prueba entre **repositorios** e **issues**.
- Ejecución de pruebas y generación de reportes.

---

## Tecnologías utilizadas

- **Java**
- **Maven**
- **Rest Assured**
- **Cucumber**
- **JUnit**
- **Jackson / Gson** para serialización y deserialización
- **Postman** para pruebas manuales y apoyo en validación inicial

---

## API utilizada

Se utiliza la **GitHub REST API**, una API pública que permite gestionar recursos como repositorios e issues.

### Endpoints cubiertos

#### Repositorios
- `POST /user/repos`
- `GET /users/{username}/repos`
- `GET /repos/{owner}/{repo}`

#### Issues
- `POST /repos/{owner}/{repo}/issues`
- `GET /repos/{owner}/{repo}/issues`

---

## Historias de Usuario cubiertas

### HU1 — Crear repositorio
**Como** tester de APIs  
**Quiero** crear un repositorio mediante la API  
**Para** validar que GitHub permite registrar nuevos repositorios correctamente

**Endpoint:** `POST /user/repos`

**Criterios de aceptación:**
- El servicio responde `201 Created`
- El repositorio debe contener:
  - `id`
  - `name`
  - `owner`
  - `html_url`
- El repositorio pertenece al usuario autenticado
- El campo `name` coincide con el enviado en el request

---

### HU2 — Ver repositorios de un usuario
**Como** tester de APIs  
**Quiero** consultar los repositorios de un usuario  
**Para** validar que la API retorna correctamente la colección de repositorios

**Endpoint:** `GET /users/{username}/repos`

**Criterios de aceptación:**
- El servicio responde `200 OK`
- La respuesta es un array de repositorios
- Cada repositorio contiene:
  - `id`
  - `name`
  - `owner`
  - `visibility`
- Todos los repositorios pertenecen al usuario consultado

---

### HU3 — Ver información de un repositorio específico
**Como** tester de APIs  
**Quiero** consultar un repositorio específico  
**Para** validar la integridad de su información

**Endpoint:** `GET /repos/{owner}/{repo}`

**Criterios de aceptación:**
- El servicio responde `200 OK`
- La respuesta contiene:
  - `id`
  - `name`
  - `owner.login`
  - `description`
  - `visibility`
- El nombre del repositorio coincide con el solicitado en el endpoint

---

### HU4 — Crear issue en un repositorio
**Como** tester de APIs  
**Quiero** crear un issue en un repositorio existente  
**Para** validar que los usuarios pueden registrar incidencias

**Endpoint:** `POST /repos/{owner}/{repo}/issues`

**Criterios de aceptación:**
- El servicio responde `201 Created`
- El issue creado debe contener:
  - `id`
  - `number`
  - `title`
  - `state`
- El estado inicial debe ser `open`
- El issue queda asociado al repositorio indicado

---

### HU5 — Ver issues de un repositorio
**Como** tester de APIs  
**Quiero** consultar los issues de un repositorio  
**Para** validar que los issues pertenecen correctamente al repositorio

**Endpoint:** `GET /repos/{owner}/{repo}/issues`

**Criterios de aceptación:**
- El servicio responde `200 OK`
- La respuesta es un array de issues
- Cada issue contiene:
  - `id`
  - `number`
  - `title`
  - `state`
- Todos los issues retornados pertenecen al repositorio consultado
- Los issues creados previamente aparecen en la respuesta

---

## Alcance de pruebas

### Pruebas para la entidad Repositorios
Ejemplos de pruebas automatizadas:

- Crear repositorio válido
- Crear repositorio con nombre inválido
- Crear repositorio sin autenticación
- Consultar repositorios de un usuario existente
- Consultar repositorio específico existente
- Consultar repositorio inexistente

### Pruebas para la entidad Issues
Ejemplos de pruebas automatizadas:

- Crear issue válido
- Crear issue sin título
- Crear issue en repositorio inexistente
- Consultar issues de un repositorio
- Consultar issues de un repositorio sin issues
- Validar que un issue creado aparece en el listado

---

## Flujo End-to-End cubierto

El flujo principal del proyecto valida el siguiente escenario:

1. Crear un repositorio
2. Consultar el repositorio creado
3. Crear un issue en el repositorio
4. Consultar los issues del repositorio
5. Validar que el issue pertenece al repositorio creado

Este flujo simula un caso real de trabajo de un **QA Automation Engineer**, validando la trazabilidad entre recursos.

---

## Estructura sugerida del proyecto

```bash
mission/
├── pom.xml
├── README.md
└── src
    ├── test
    │   ├── java
    │   │   ├── runners
    │   │   │   └── TestRunner.java
    │   │   ├── stepdefinitions
    │   │   │   ├── RepositorySteps.java
    │   │   │   ├── IssueSteps.java
    │   │   │   └── Hooks.java
    │   │   ├── models
    │   │   │   ├── RepositoryRequest.java
    │   │   │   ├── RepositoryResponse.java
    │   │   │   ├── IssueRequest.java
    │   │   │   └── IssueResponse.java
    │   │   ├── utils
    │   │   │   ├── ConfigReader.java
    │   │   │   ├── RequestSpecs.java
    │   │   │   └── TestDataGenerator.java
    │   │   └── services
    │   │       ├── RepositoryService.java
    │   │       └── IssueService.java
    │   └── resources
    │       ├── features
    │       │   ├── repositories.feature
    │       │   ├── issues.feature
    │       │   └── e2e.feature
    │       └── config.properties
