package com.denis.costeminimo.Infrastucture;

import com.denis.costeminimo.Aplication.OrigenDestinoCiudadesCosteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/CosteMinimo")
public class OrigenDestinoCiudadesCosteController {
    //inyeccion de datos
    private final OrigenDestinoCiudadesCosteUseCase origenDestinoCiudadesCosteUseCase ;
    @Autowired
    public OrigenDestinoCiudadesCosteController(OrigenDestinoCiudadesCosteUseCase origenDestinoCiudadesCosteUseCase) {
        this.origenDestinoCiudadesCosteUseCase = origenDestinoCiudadesCosteUseCase;
    }

    //llamada GET donde hay que proporcionar un origen y un destino y llama a getCosteMinimo
    @GetMapping("/{origen}/{destino}")
    public List<String> getMinCostPath(@PathVariable String origen, @PathVariable String destino) {
        return origenDestinoCiudadesCosteUseCase.getCosteMinimo(origen, destino);
    }


}
