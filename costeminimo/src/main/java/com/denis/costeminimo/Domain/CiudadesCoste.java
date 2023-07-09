package com.denis.costeminimo.Domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CiudadesCoste {
    private final String[] ciudades;
    private final int[][] costes;

    //cogemos los datos almacenados en resources/application.properties
    public CiudadesCoste(@Value("${costeminimo.ciudades}") String[] ciudades,
                         @Value("#{'${coste}'.split(';')}") int[][] costes) {
        this.ciudades = Arrays.copyOf(ciudades, ciudades.length);
        this.costes = copiaSegura(costes);
    }
    //devolvemos los valores de manera segura, para que no sean alterados
    public String[] getCiudades() {
        return Arrays.copyOf(ciudades, ciudades.length);
    }
    public int[][] getCostes() {
        return copiaSegura(costes);
    }

    //metodo de creacion de copia defensiva de datos al devolverlos
    private int[][] copiaSegura(int[][] original) {
        int[][] copia = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copia[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copia;
    }
}
