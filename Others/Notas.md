# PSP : Conexiones en red

Los modelos distribuidos NO comparten memoria, NO estan sincronizados y se comunican entre si

## Elementos:
  - Mensaje
  - Emisor
  - Receptor
  - Paquete (unidad básica de información)
  - Canal de comunicacion
  - Protocolo de comunicacion (Define reglas, formato,... Ej: TCP/IP,...)
  
### TCP: 
 - Cliente-Servidor 
 - Protocolo mas usado, **orientado a conexion**
 - Garantiza que los datos no se pierdan siempre que exista una conexion
 - Los mensajes llegan en ordeN
 - Funcionamiento: Divide los mensajes en paquetes
 - Se establece siempre la conexion al principio, se enviaran los mensajes que queramos y finalizara al cerrar la conexión

### UDP: 
 - **NO es orientado a conexion** 
 - Más rapido
 - No es necesario abrir conexion ni cerrarla
 - No garantiza que los mensajes lleguen
 - Los mensajes no guardan un orden
 - Solo permite mensajes pequeños (64KB)
 - Los mensajes se denominan datagramas
 - Es menos fiable que TCP
 - Más ligero y eficiente que TCP

## Programación en red con Java (TCP)

 - La clase `InetAddress` se utiliza para representar una dirección IP
 - Se utilizan sockets (Es un conector entre procesos)
 - Para establecer la conexion es necesario la ip  puerto
 - ServerSocket para el servidor.
 - Socket para el cliente.

<div align="center">
  <img src="https://i.imgur.com/CIKrex4.png" alt="" height="300px">
</div>

### Socket Stream: pasos parte servidor
 - Creación del socket: Instanciamos “socket servidor”.
 - Bindeamos la ip y el puerto.
 - Escucha: El socket servidor escuchara el puerto asignado. 
 - Aceptación de conexiones: accept() bloquea el proceso hasta que un cliente se conecte y El socket servidor queda libre 
 - Envío y recepción de mensajes: se utilizan operaciones de lectura y escritura sobre el nuevo socket. No se usa el socket servidor.
 - Cierre de la conexión: puede cerrar la conexión con un cliente. 

### Socket Stream: pasos parte cliente
 - Creación del socket: Instanciamos el “socket cliente” 
 - Conexión del socket: Se localiza el socket del servidor , necesitamos la ip y puerto
 - Envío y recepción de mensajes
 - Cierre de la conexión:

## Programación en red con Java (UDP)

 - Sencillo y eficiente
 - Protocolo UDP  
 - No garantiza la entrega de paquetes (No es fiable)
 - Se tranmite de emisor a receptor sin establecer conexion
 - Ambos necesitan la ip y puerto del otro
 - **No** es cliente-servidor
 - Usa DatagramPacket y DatagramSocket

<div align="center">
  <img src="https://i.imgur.com/d0AdGnu.png" alt="" height="300px">
</div>

### Gestión de socket datagram
 - El emisor crea un socket asociado a un puerto local para escuchar peticiones y permanece a la espera
 - El receptor crea un socket para comunicarse con el emisor, se necesita ip y puerto. Con send() del socket se envia peticion en forma de datagrama
 - El emisor recibe peticiones con receive() de socket, con eso sabemos puerto, ip, con send() enviamos respuesta
 - El receptor recibe la respuesta con receive()
 - El servidor permanece a la espera de mas conexiones

### Socket datagram
 - Creacion de socket: Instanciamos socket
 - Asignacion de ip y puerto: Con bind() asignamos la ip y el puerto 
 - Envio y recepcion: La operacion enviar necesita la IP y puerto 
 - Cierre de conexion: El proceso cliente cierra el socket para terminar la comunicacion


