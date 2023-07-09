package com.denis.costeminimo.Infrastucture;
import com.denis.costeminimo.Aplication.OrigenDestinoCiudadesCosteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

public class OrigenDestinoCiudadesCosteControllerTest {
    @Mock
    private OrigenDestinoCiudadesCosteUseCase origenDestinoCiudadesCosteUseCase;

    private OrigenDestinoCiudadesCosteController origenDestinoCiudadesCosteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        origenDestinoCiudadesCosteController = new OrigenDestinoCiudadesCosteController(origenDestinoCiudadesCosteUseCase);
    }

    @Test
    public void testGetMinCostPath() {
        String origen = "Vitoria";
        String destino = "Lleida";
        List<String> costeMinimoAct = new LinkedList<>(Arrays.asList(
                "Vitoria: 0", "Zaragoza: 4", "Lleida: 2", "Coste Total: 6"
        ));
        when(origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, destino)).thenReturn(costeMinimoAct);

        List<String> costeMinimoEx = origenDestinoCiudadesCosteController.getMinCostPath(origen, destino);

        Assertions.assertEquals(costeMinimoAct, costeMinimoEx);
    }
}