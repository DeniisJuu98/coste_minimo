package com.denis.costeminimo.Aplication;

import com.denis.costeminimo.Aplication.Interface.OrigenAllCiudadesCosteInterface;
import com.denis.costeminimo.Domain.CiudadesCoste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrigenAllCiudadesCosteUseCase implements OrigenAllCiudadesCosteInterface {
    //inyeccion de datos
    private final CiudadesCoste ciudadesCoste;
    private final OrigenDestinoCiudadesCosteUseCase origenDestinoCiudadesCosteUseCase;

    @Autowired
    public OrigenAllCiudadesCosteUseCase(CiudadesCoste ciudadesCoste, OrigenDestinoCiudadesCosteUseCase origenDestinoCiudadesCosteUseCase) {
        this.ciudadesCoste = ciudadesCoste;
        this.origenDestinoCiudadesCosteUseCase = origenDestinoCiudadesCosteUseCase;
    }

    public List<String> getCosteMinimoDesdeOrigen(String origen) {
        // Obtener todas las ciudades de destino
        String[] ciudades = ciudadesCoste.getCiudades();
        List<String> destinos = Arrays.asList(ciudades);
        List<String> rutas = new ArrayList<>();
        int origenDatos = Arrays.asList(ciudades).indexOf(origen);

        //Comprobar si los datos de origen introducidos son correctos
        if (origenDatos == -1 ) {
            throw new IllegalArgumentException("Datos de Origen o Destino erroneos");
        }

        // Calcular el costo mínimo desde el origen a todos los destinos
        for (String destino : destinos) {
            if (!destino.equals(origen)) {
                List<String> ruta = origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, destino);
                //por cada iteracion se añade a la variable rutas
                rutas.add("Coste minimo de " + origen + " a " + destino + ": " + ruta);
            }
        }
        return rutas;
    }
}