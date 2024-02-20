# Ejercicio formulario

## Enunciado
Codifica una pareja de clases Java, EncuestaServidor y EncuestaCliente, que se comuniquen mediante sockets TCP. El objetivo es crear un servidor que se ejecute de forma indefinida y gestione una encuesta con varias preguntas de respuesta sí/no.

El funcionamiento de cada uno de los programas será el siguiente:

·      Servidor: tendrá almacenadas 3 o 4 preguntas de respuesta sí/no. De cada una de ellas se almacena el texto y se contabilizará la cantidad de respuestas afirmativas y la cantidad de respuestas negativas de quienes respondan la encuesta.

Este programa estará preparado para atender a un número indeterminado de clientes. La ejecución de cada uno de ellos se realizará de forma simultánea.

Para cada uno de los clientes realizarán las siguientes acciones:

o    Enviar cada uno de los textos de las preguntas.

o    Recibir la respuesta del cliente a cada una de las preguntas y contabilizar las mismas.

o    Una vez respondidas todas las preguntas, enviar un resumen del resultado de la encuesta.

·      Cliente: se conectará al servidor y esperará a recibir el texto de cada una de las preguntas. Para cada una de ellas, se pedirá al usuario su respuesta y se enviará al servidor.

Finalmente, recibirá un resumen de los resultados de la encuesta que mostrará por pantalla.

### Servidor:

- -Iterar preguntas Para cada pregunta
	- +Enviar texto
	- +Recibir respuesta. Contabilizar
- -Enviar resumen

### Cliente

- -Recibir pregunta
- +Enviar respuesta 