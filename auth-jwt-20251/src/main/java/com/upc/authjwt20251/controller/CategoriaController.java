package com.upc.authjwt20251.controller;

import com.upc.authjwt20251.dto.CategoriaDTO;
import com.upc.authjwt20251.model.Categoria;
import com.upc.authjwt20251.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin

public class CategoriaController {
    final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;

    }
    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaDTO>> getAll(){
        return ResponseEntity.ok(categoriaService.listar());
    }
    @PostMapping("/agregar")
    public ResponseEntity<Categoria> create(@RequestBody CategoriaDTO categoriaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.agregar(categoriaDTO));
    }
}
