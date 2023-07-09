package com.denis.costeminimo.Aplication;
import com.denis.costeminimo.Domain.CiudadesCoste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

public class OrigenDestinoCiudadesCosteUseCaseTest {
    @Mock
    private CiudadesCoste ciudadesCoste;

    private OrigenDestinoCiudadesCosteUseCase origenDestinoCiudadesCosteUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        origenDestinoCiudadesCosteUseCase = new OrigenDestinoCiudadesCosteUseCase(ciudadesCoste);
    }

    @Test
    public void testGetCosteMinimo() {
        // Arrange
        String origen = "Vitoria";
        String destino = "Lleida";
        String[] ciudades = {"Vitoria", "Zaragoza", "Teruel", "Madrid", "Lleida", "Alicante", "Valencia", "Segovia", "Albacete"};
        int[][] costes = {
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

        ciudadesCoste = Mockito.mock(CiudadesCoste.class);
        when(ciudadesCoste.getCiudades()).thenReturn(ciudades);
        when(ciudadesCoste.getCostes()).thenReturn(costes);

        origenDestinoCiudadesCosteUseCase = new OrigenDestinoCiudadesCosteUseCase(ciudadesCoste);


        List<String> costeMinimoAct = origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, destino);


        List<String> costeMinimoEx = new LinkedList<>(Arrays.asList(
                "Vitoria: 0", "Zaragoza: 4", "Lleida: 2", "Coste Total: 6"
        ));
        Assertions.assertEquals(costeMinimoEx, costeMinimoAct);
    }
}
