# coste_minimo
## API COSTE MINIMO
* JAVA (17)
* SPRING BOOT (3.1.1)
* DATOS (application.properties)

## USO
* Llamada GET \
  /api/CosteMinimo/{origen}/{destino} 

Descripción: Entrada de datos de una ciudad origen y una ciudad destino, donde se mostrara en formato JSON, la ruta
desde la ciudad de origen hasta la ciudad destino, añadiendo el coste de cada una y el coste total, siempre eligiendo
el menor coste.



* Llamada GET \
  /api/CosteMinimo/{origen} 

Descripción: Entrada de datos de una ciudad origen , donde se mostrara en formato JSON, la ruta desde la ciudad de origen 
al resto de ciudades disponibles, mostrando la ruta, el coste y el coste total, siempre eligiendo el menor coste.

Se pueden ver mejor las llamadas en el SWAGGER: http://localhost:8080/doc/swagger-ui/index.html#/ \
(Hay que iniciar la aplicación primero para poder usarlo)

## DATOS A INTRODUCIR EN ORIGEN O DESTINO
  * "Vitoria", "Zaragoza", "Teruel", "Madrid", "Lleida", "Alicante", "Valencia", "Segovia", "Albacete"

## EXPLICACIONES

* ESTRUCTURA\
La api se ha desarrollado con una estructura hexagonal, gracias a los beneficios que ofrece:\
Separación de capas:
  * Domain: donde se almacena el nucleo de la API, y son los datos que se van a manejar, es decir las entidades. Los datos 
  proporcionados para la prueba, al no estar en base de datos, se ha intentado emular una, guardandolos en application.properties
  y sacandolos de ahi con @value, en las propiedades.
  
  * Aplication: donde hemos creado las interfaces y las hemos implementado en las clases. Dichas clases solo van a almacenar metodos 
  para luego ser llamadas desde la capa externa donde se muestran los datos y se hacen las llamadas.

  * Infrastructure: es donde entran y salen los datos, y llamamos a las clases de Aplication para que gestionen dichos datos.

A parte de la separación de capas, una de las ventajas mas destacadas es la flexibilidad y la esclabilidad, que facilita 
la introducción de nuevas funcionalidades y cambios en el sistema, gracias a la separación de capas. Además, destaca la
mantenibilidad a largo plazo, debido a lograr un código más limpio, modular y fácil de mantener.

Sin olvidar la incorporacion de pruebas unitarias, que gracias a la separacion de capas se consiguen pruebas eficaces y aisladas.


* METODOS IMPORTANTES
  * Clase : /Aplication/OrigenDestinoCiudadesCosteUseCase.class \
    * Explicación : Es la clase mas importante, ya que tambien es usada en la clase "OrigenAllCiudadesCosteUseCase", donde se llama al metodo de la misma.
    "getCosteMinimo". \
    Dicho metodo, llama a otros creados dentro de la clase, "crearNuevaMatriz" y "algoritmoFloyd", que son los metodos que van a gestionar los datos, del array bidimensional,
    
    para sacar el coste mas bajo. Y para hacer esto he desarrollado un metodo aplicando el algoritmo de Floyd Warshall. \
    En el primer metodo "crearNuevaMatriz", aplicamos lo siguiente: \
    El valor del grafo [i,j] es cero si i==j, usamos infinito o el mavlor mas alto posible si no hay aristas de i a j. i es el inicio , j es el destino y k es de intermedio. \
    grafo[,] { { 0, 9 , Inf , 10  }, \
 ............{ { Inf, 0 , 7 , Inf  }, \
 ............{ { Inf, Inf , 0 , 11  }, \
 ............{ { Inf, Inf , Inf , 0  }, \
    El resultado es una matriz de distancias \
    0     9     16    10 \
    Inf   0     7     18 \
    Inf   Inf   0     11 \
    Inf   Inf   Inf   0 \
  
   En el segundo y ultimo metodo "algoritmoFloyd", aplicamos lo siguiente: \
    Inicialzamos la matriz de solucion(distancias) con los valores del grafo. Luego vamos actualizando la matriz de soluciones considerando los vertices, como vertices intermedios.
    Vamos a ir vertice por vertice y actualizar todos los caminos mas cortos que incluyen el vertice seleccionado como un vertice intermedio en el camino mas corto.

    Cuando seleccionamos un vertice intermecio K hemos considerado los vertice { 0,1,2,... k-1 } como vertices intermedios  i--------k-------j  \
   
    Para cada par (i, j) de los vertices de inicio y destino tenemos dos posibilidades \
    * K no es un vertice intermedio en el comino mas corto entre i y j. Conservamos distancia [i , j]
    * K es un vertice intermecio en el camino mas corto entre i y j. Actualizamos el valor de distancias [i, j] si distancia [i , j] > distancia [i, k]+ distancia [k, j]
    con distancia [i, k] + distancia [k, j] \
    

* Enlace al algoritmo Floyd-Warshall(1959) : https://es.wikipedia.org/wiki/Algoritmo_de_Floyd-Warshall


  * Clase : /Aplication/OrigenAllCiudadesCosteUseCase.class \
  * * Explicación : En el metodo "getCosteMinimoDesdeOrigen", le pasamos por parametro un origen, y llamara al metodo "getCosteMinimo", de la clase "OrigenDestinoCiudadesCosteUseCase"
  que nos proporciona una lista del menor coste desde un origen a un destino, entonces en este metodo llamamos a todas las ciudades existentes, e iteramos desde la ciudad de origen 
  mandada por el usuario, que esa misma ciudad, se comprueba en un if para que no sea iterada, y saca una lista con el origen a todos los destinos.

## TEST

  * Por cada clase, y carpeta de la API, se ha creado un test, para comprobar los datos de la API. Estan en la carpeta "test"

## CONTROL DE ERRORES

 * Al ser calculos matematicos, y no afectar datos introducidos o inyeccion de codigo, solo se ha controlado, que los datos de origen y destino, existen en la lista de
ciudades disponibles. Si los datos introducidos, no son correctos salta un error y no se ejecuta mas codigo.

