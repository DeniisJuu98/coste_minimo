package com.denis.costeminimo.Aplication;

import com.denis.costeminimo.Aplication.Interface.OrigenDestinoCiudadesCosteInterface;
import com.denis.costeminimo.Domain.CiudadesCoste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrigenDestinoCiudadesCosteUseCase implements OrigenDestinoCiudadesCosteInterface {
    //inyeccion de datos
    private final CiudadesCoste ciudadesCoste;
    @Autowired
    public OrigenDestinoCiudadesCosteUseCase(CiudadesCoste ciudadesCoste) {
        this.ciudadesCoste = ciudadesCoste;
    }

    //metodo que calcula la ruta de coste minimo entre dos ciudades proporcionadas
    public List<String> getCosteMinimo(String origen, String destino) {
        //lista para la devolucion de datos
        LinkedList<String> ruta = new LinkedList<>();
        //se cogen los datos proporcionados y se insertan en nuevas variables
        String[] ciudades = ciudadesCoste.getCiudades();

        //se almacenan datos: si se encuentra devuelve la posicion, si no devuelve -1
        int origenDatos = Arrays.asList(ciudades).indexOf(origen);
        int destinoDatos = Arrays.asList(ciudades).indexOf(destino);

        // Comprobación si existen los datos aportados por el usuario, en la lista de datos
        if (origenDatos == -1 || destinoDatos == -1) {
            throw new IllegalArgumentException("Datos de Origen o Destino erroneos");
        }
        //generar los datos necesario despues de la comprobacion inicial
        int numCiudades = ciudades.length;
        //coge los costes de los datos de CiudadCostes
        int[][] costes = Arrays.copyOf(ciudadesCoste.getCostes(), numCiudades);
        int[][] anteriores = new int[numCiudades][numCiudades]; // equivaldria a una matriz del numero de ciudades

        //metodo que actualiza "anteriores", donde los 0 (caminos que no se pueden coger) los iguala a MAX_VALUE
        crearNuevaMatriz(numCiudades, costes,anteriores);
        //metodo que usa el algoritmo de Floyd-Warshall para calcular los costes mínimos y actualizar "anteriores".
        algoritmoFloyd(numCiudades,costes,anteriores);
        //metodo que calcula y devuelve la ruta de coste mínimo agregandolo a "ruta"
        ruta = devolverRuta(ruta,anteriores,costes,origenDatos,destinoDatos,ciudades);
        return ruta;
    }

    private void crearNuevaMatriz(int numCiudades,int[][] costes,int[][] anteriores){
        // Inicializar la matriz de anteriores con los valores iniciales
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if (costes[i][j] == 0 && i != j) {
                    costes[i][j] = Integer.MAX_VALUE;
                }
                if (costes[i][j] != Integer.MAX_VALUE) {
                    anteriores[i][j] = i;
                } else {
                    anteriores[i][j] = -1;
                }
            }
        }
    }
    private void algoritmoFloyd(int numCiudades,int[][] costes,int[][] anteriores){
        int nuevoCosto;
        for (int k = 0; k < numCiudades; k++) {
            for (int i = 0; i < numCiudades; i++) {
                for (int j = 0; j < numCiudades; j++) {
                    if (costes[i][k] != Integer.MAX_VALUE && costes[k][j] != Integer.MAX_VALUE) {
                        nuevoCosto = costes[i][k] + costes[k][j];

                        if (nuevoCosto < costes[i][j]) {
                            costes[i][j] = nuevoCosto;
                            anteriores[i][j] = anteriores[k][j];
                        }
                    }
                }
            }
        }
    }
    private LinkedList<String> devolverRuta(LinkedList<String> ruta,int[][] anteriores,int[][] costes,int origenDatos,int destinoDatos,String[] ciudades){
        int actual = destinoDatos;
        int ciudadAnterior = anteriores[origenDatos][actual];
        int costoAnterior = costes[ciudadAnterior][actual];
        int ciudadActual,costo,sumaCostes = 0;
        sumaCostes += costoAnterior;
        ruta.addFirst(ciudades[actual] + ": " + costoAnterior);

        //recorrer las rutas y añade los costes y los suma en un total
        while (actual != origenDatos) {
            ciudadActual = ciudadAnterior;
            ciudadAnterior = anteriores[origenDatos][ciudadActual];
            costo = costes[ciudadAnterior][ciudadActual];
            sumaCostes += costo;

            if (ciudadActual != destinoDatos) {
                ruta.addFirst(ciudades[ciudadActual] + ": " + costo);
            }
            actual = ciudadActual;
        }
        ruta.add("Coste Total: " + sumaCostes);
        return ruta;
    }

}
