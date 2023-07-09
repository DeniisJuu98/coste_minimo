package com.denis.costeminimo.Infrastucture;
import com.denis.costeminimo.Aplication.OrigenAllCiudadesCosteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.when;

public class OrigenAllCiudadesCosteControllerTest {
    @Mock
    private OrigenAllCiudadesCosteUseCase origenAllCiudadesCosteUseCase;

    private OrigenAllCiudadesCosteController origenAllCiudadesCosteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        origenAllCiudadesCosteController = new OrigenAllCiudadesCosteController(origenAllCiudadesCosteUseCase);
    }

    @Test
    public void testGetMinCostPath2() {
        String origen = "Vitoria";
        List<String> costeMinimoAct = new LinkedList<>(Arrays.asList(
                "Coste minimo de Vitoria a Zaragoza: [Vitoria: 0, Zaragoza: 4, Coste Total: 4]",
                "Coste minimo de Vitoria a Teruel: [Vitoria: 0, Teruel: 6, Coste Total: 6]",
                "Coste minimo de Vitoria a Madrid: [Vitoria: 0, Madrid: 8, Coste Total: 8]",
                "Coste minimo de Vitoria a Lleida: [Vitoria: 0, Zaragoza: 4, Lleida: 2, Coste Total: 6]",
                "Coste minimo de Vitoria a Alicante: [Vitoria: 0, Teruel: 6, Alicante: 7, Coste Total: 13]",
                "Coste minimo de Vitoria a Valencia: [Vitoria: 0, Zaragoza: 4, Lleida: 2, Valencia: 4, Coste Total: 10]",
                "Coste minimo de Vitoria a Segovia: [Vitoria: 0, Zaragoza: 4, Lleida: 2, Segovia: 8, Coste Total: 14]",
                "Coste minimo de Vitoria a Albacete: [Vitoria: 0, Zaragoza: 4, Lleida: 2, Valencia: 4, Albacete: 6, Coste Total: 16]"
        ));
        when(origenAllCiudadesCosteUseCase.getCosteMinimoDesdeOrigen(origen)).thenReturn(costeMinimoAct);


        List<String> costeMinimoEx = origenAllCiudadesCosteController.getMinCostPath2(origen);


        Assertions.assertEquals(costeMinimoAct, costeMinimoEx);
    }
}
