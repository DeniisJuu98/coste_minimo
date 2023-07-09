package com.denis.costeminimo.Infrastucture;

import com.denis.costeminimo.Aplication.OrigenAllCiudadesCosteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/CosteMinimo")
public class OrigenAllCiudadesCosteController {
    //inyeccion de datos
    private final OrigenAllCiudadesCosteUseCase origenAllCiudadesCosteUseCase ;

    @Autowired
    public OrigenAllCiudadesCosteController(OrigenAllCiudadesCosteUseCase origenAllCiudadesCosteUseCase) {
        this.origenAllCiudadesCosteUseCase = origenAllCiudadesCosteUseCase;
    }
    //llamada GET donde hay que proporcionar un origen y llama a getCosteMinimo
    @GetMapping("/{origen}")
    public List<String> getMinCostPath2(@PathVariable String origen) {
        return origenAllCiudadesCosteUseCase.getCosteMinimoDesdeOrigen(origen);
    }
}
