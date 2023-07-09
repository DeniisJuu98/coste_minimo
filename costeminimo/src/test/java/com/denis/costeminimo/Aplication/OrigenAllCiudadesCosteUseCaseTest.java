package com.denis.costeminimo.Aplication;
import com.denis.costeminimo.Domain.CiudadesCoste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

public class OrigenAllCiudadesCosteUseCaseTest {
    @Mock
    private CiudadesCoste ciudadesCoste;

    @Mock
    private OrigenDestinoCiudadesCosteUseCase origenDestinoCiudadesCosteUseCase;

    private OrigenAllCiudadesCosteUseCase origenAllCiudadesCosteUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        origenAllCiudadesCosteUseCase = new OrigenAllCiudadesCosteUseCase(ciudadesCoste, origenDestinoCiudadesCosteUseCase);
    }

    @Test
    public void testGetCosteMinimoDesdeOrigen() {
        String origen = "Vitoria";
        String[] ciudades = {"Vitoria", "Zaragoza", "Teruel", "Madrid", "Lleida", "Alicante", "Valencia", "Segovia", "Albacete"};
        when(ciudadesCoste.getCiudades()).thenReturn(ciudades);

        List<String> ruta1 = new LinkedList<>(Arrays.asList("Vitoria: 0", "Zaragoza: 4", "Coste Total: 4"));
        List<String> ruta2 = new LinkedList<>(Arrays.asList("Vitoria: 0", "Madrid: 8", "Coste Total: 8"));
        List<String> ruta3 = new LinkedList<>(Arrays.asList("Vitoria: 0", "Zaragoza: 4", "Lleida: 2", "Valencia: 4", "Coste Total: 10"));
        List<String> ruta4 = new LinkedList<>(Arrays.asList("Vitoria: 0", "Teruel: 6", "Coste Total: 6"));
        List<String> ruta5 = new LinkedList<>(Arrays.asList("Vitoria: 0", "Zaragoza: 4", "Lleida: 2", "Coste Total: 6"));
        List<String> ruta6 = new LinkedList<>(Arrays.asList("Vitoria: 0", "Teruel: 6", "Alicante: 7", "Coste Total: 13"));
        List<String> ruta7 = new LinkedList<>(Arrays.asList("Vitoria: 0", "Zaragoza: 4", "Lleida: 2", "Segovia: 8", "Coste Total: 14"));
        List<String> ruta8 = new LinkedList<>(Arrays.asList("Vitoria: 0", "Zaragoza: 4", "Lleida: 2", "Valencia: 4", "Albacete: 6", "Coste Total: 16"));
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, "Zaragoza")).thenReturn(ruta1);
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, "Madrid")).thenReturn(ruta2);
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, "Valencia")).thenReturn(ruta3);
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, "Teruel")).thenReturn(ruta4);
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, "Lleida")).thenReturn(ruta5);
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, "Alicante")).thenReturn(ruta6);
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, "Segovia")).thenReturn(ruta7);
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, "Albacete")).thenReturn(ruta8);

        List<String> costeMinimoAct = origenAllCiudadesCosteUseCase.getCosteMinimoDesdeOrigen(origen);

        List<String> costeMinimoEx = new LinkedList<>(Arrays.asList(
                "Coste minimo de Vitoria a Zaragoza: [Vitoria: 0, Zaragoza: 4, Coste Total: 4]",
                "Coste minimo de Vitoria a Teruel: [Vitoria: 0, Teruel: 6, Coste Total: 6]",
                "Coste minimo de Vitoria a Madrid: [Vitoria: 0, Madrid: 8, Coste Total: 8]",
                "Coste minimo de Vitoria a Lleida: [Vitoria: 0, Zaragoza: 4, Lleida: 2, Coste Total: 6]",
                "Coste minimo de Vitoria a Alicante: [Vitoria: 0, Teruel: 6, Alicante: 7, Coste Total: 13]",
                "Coste minimo de Vitoria a Valencia: [Vitoria: 0, Zaragoza: 4, Lleida: 2, Valencia: 4, Coste Total: 10]",
                "Coste minimo de Vitoria a Segovia: [Vitoria: 0, Zaragoza: 4, Lleida: 2, Segovia: 8, Coste Total: 14]",
                "Coste minimo de Vitoria a Albacete: [Vitoria: 0, Zaragoza: 4, Lleida: 2, Valencia: 4, Albacete: 6, Coste Total: 16]"
        ));
        Assertions.assertEquals(costeMinimoEx, costeMinimoAct);
    }
}
