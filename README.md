# Webflux Routers y MongoDB Reactivo

Vamos a crear una versión actualizada de nuestro gestor de un biblioteca publica.

Crea un nuevo proyecto con Spring Boot que mantenga los servicios ofrecidos por el proyecto anterior pero que use WebFlux usando routers y un repositorio reactivo. Puedes reutilizar el código que consideres necesario y realizar los ajustes necesarios. 

Recuerda que se deben exponer los siguientes servicios para administrar la biblioteca

* Consultar disponibilidad de un recurso indicando en un mensaje si esta disponible o no. en caso de no estar disponible presentar la fecha del préstamo actual del ultimo ejemplar.
* Prestar un recurso, se debe comprobar si esta prestado o no, indicarlo mediante un mensaje. Si se encuentra disponible debemos marcarlo como prestado y registrar la fecha del préstamo (no es necesario llevar el historia de prestamos).
* Recomendar un listado de recursos al usuario a partir del tipo de recurso, del área temática o de los dos. Los recursos están clasificados por tipo de recurso (libros, revistas, fichas, etc) pero también por área temática (ciencias, naturaleza, historia, etc).
* Devolver un recurso que se encontraba prestado, obviamente si un recurso no se encuentra en préstamo no podrá ser devuelto. Indicar el resultado con un mensaje.
* Servicios para hacer CRUD de los recursos. 

No olvide probar el desarrollo con Postman e implementa pruebas unitarias. 

Desarrolla y comparte tu repositorio git, ten en cuenta que tu solución debe administrar una base de datos mongodb en el cluster de mongoatlas.