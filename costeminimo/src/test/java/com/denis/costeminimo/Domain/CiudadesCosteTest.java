package com.denis.costeminimo.Domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
class CiudadesCosteTest {

    @Test
    public void testGetCiudades() {
        // Arrange
        String[] ciudadesEx = {"Vitoria", "Zaragoza", "Teruel", "Madrid", "Lleida", "Alicante", "Valencia", "Segovia", "Albacete"};
        String[] costes = {"0,4,6,8,0,0,0,0,0","4,0,2,0,2,0,0,0,0","6,2,0,3,5,7,0,0,0","8,0,3,0,0,0,0,0,0","0,2,5,0,0,0,4,8,0","0,0,7,0,0,0,3,0,7","0,0,0,0,4,3,0,0,6","0,0,0,0,8,0,0,0,4","0,0,0,0,0,7,6,4,0"};
        int[][] costesEx = parseCostes(costes);
        CiudadesCoste ciudadesCoste = new CiudadesCoste(ciudadesEx, costesEx);

        String[] ciudadesAct = ciudadesCoste.getCiudades();

        Assertions.assertArrayEquals(ciudadesEx, ciudadesAct);
    }

    @Test
    public void testGetCostes() {
        // Arrange
        int[][] costesEx = {
                {0, 4, 6, 8, 0, 0, 0, 0, 0},
                {4, 0, 2, 0, 2, 0, 0, 0, 0},
                {6, 2, 0, 3, 5, 7, 0, 0, 0},
                {8, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 2, 5, 0, 0, 0, 4, 8, 0},
                {0, 0, 7, 0, 0, 0, 3, 0, 7},
                {0, 0, 0, 0, 4, 3, 0, 0, 6},
                {0, 0, 0, 0, 8, 0, 0, 0, 4},
                {0, 0, 0, 0, 0, 7, 6, 4, 0}
        };
        String[] ciudades = {"Vitoria", "Zaragoza", "Teruel", "Madrid", "Lleida", "Alicante", "Valencia", "Segovia", "Albacete"};
        CiudadesCoste ciudadesCoste = new CiudadesCoste(ciudades, costesEx);

        int[][] costesActu = ciudadesCoste.getCostes();

        Assertions.assertArrayEquals(costesEx, costesActu);
    }

    private int[][] parseCostes(String[] costesStr) {
        int[][] costes = new int[costesStr.length][];
        for (int i = 0; i < costesStr.length; i++) {
            String[] filaStr = costesStr[i].split(",");
            int[] fila = new int[filaStr.length];
            for (int j = 0; j < filaStr.length; j++) {
                fila[j] = Integer.parseInt(filaStr[j]);
            }
            costes[i] = fila;
        }
        return costes;
    }
}
