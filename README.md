# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

| Método | Ruta                  | Descripción                                                     | Respuestas       |
|--------|-----------------------|-----------------------------------------------------------------|------------------|
| POST   | /api/users            | Registra a un nuevo usuario con los datos proporcionados        | 201 (Created)    |
| POST   | /api/users/me/session | Inicia una sesion con email y contraseña. Crea cookie de sesion | 201 (Created)    |
| DELETE | /api/users/me/session | Elimina la cookie de sesion para cerrar sesipon                 | 204 (No content) |
| GET    | /api/users/me         | Devuelve el perfil del usuario autenticado                      | 200 (OK)         |
| PUT    | /api/users/me         | Actualiza los datos del perfil                                  | 200 (OK)         |
| DELETE | /api/users/me         | Elimina la cuenta del usuario                                   | 204 (No content) |


## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
