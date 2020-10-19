
Prerequisitos
Jdk11
Gradle 6.6.1


Se deberá usar gradle que automaticamente descargará las dependencias y la aplicación está por defecto para 
ser levantada en el puerto 8090
	
Una vez descargadas las dependencias desde la ruta de los fuentes se debe ejecutar el siguiente comando

./gradlew bootRun

El cual desplegará la aplicación. Está puede ser ejecutada mediante Postman o Insomnia.



Autenticación(POST)

1. La url para autenticarse es la siguiente donde se obtiene el token:

http://localhost:8090/api/authenticate


El siguiente JSON se utilizará para enviar al servicio y genere el token, estos datos se genera automáticamente al iniciar
la aplicación.
{
    "username":"admin",
    "password":"Hunter22"
}



Agregar Usuario(POST)
2.  Para agregar un usuario se debe agregar al HEADER el token obtenido desde /authenticate

el header se debe agregar con la key -> Authorization
y el value debe tener la palabra Bearer seguida de un espacio y el token como en el siguiente ejemplo:
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYwMzA4NzUxOCwiaWF0IjoxNjAzMDY5NTE4fQ.JXeHk5bWRCt21TyoaVjEOPK-BdLIWZlh2Mylq9tGDhUHx70vB9sz9SIlO8QBERfZJ_PQoAwGj7Nrz38p5dI3FA

Esta es la url para agregar el usuario y se adjunta request de ejemplo
http://localhost:8090/api/addUser
{
    "name":"Tiger 2",
    "email":"tigers@gmail.com",
    "password":"Hunter23",
    "phones": [
    {
       "number":"1234567",
       "cityCode":"1",
       "countryCode":"57"
    }
    ]
}


Obtener Usuarios(GET)
3. Este es la url para obtener los usuarios y se debe agregar el mismo header que en el punto 2
http://localhost:8090/api/getUsers


Eliminar Usuario(DELETE)
4. Esta es la url para eliminar usuario y se debe agregar el mismo header que en el punto 2
http://localhost:8090/api/deleteUser/

Al final de la url después del / se agrega el id del usuario a eliminar como en el ejemplo
http://localhost:8090/api/deleteUser/2

Ojo: si se elimina el usuario 1, ya no se pueden hacer más consultas ya que el token no funcionaría 
al no existir el usuario en bd, se debería reiniciar el aplicativo y ahí el usuario se crearía nuevamente


Actualizar Usuario
5. Esta es la url para actualizar usuario y se debe agregar el mismo header que en el punto 2

http://localhost:8090/api/updateUser

Adjunto Json de ejemplo, este debe llevar el id del usuario a modificar

{
    "id": 2,
    "name":"Tiger 2",
    "email":"Tigersin@gmail.com",
    "password":"Hunter23",
    "phones": [
    {
       "number":"1234567",
       "cityCode":"1",
       "countryCode":"57"       },
     {
       "number":"77777",
       "cityCode":"2",
       "countryCode":"77"
    }
    ]
}


El diagrama de componentes se encuentra en el archivo DiagramaComponentes.pdf

El diagrama de secuencia para la autenticacion se encuentra en el achivo DiagramSecuenciaAuthenticate.pdf

