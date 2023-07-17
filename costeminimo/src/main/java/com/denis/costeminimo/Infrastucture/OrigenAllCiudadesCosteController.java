package com.denis.costeminimo.Infrastucture;

import com.denis.costeminimo.Aplication.OrigenAllCiudadesCosteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/CosteMinimo/OrigenAll")
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
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
