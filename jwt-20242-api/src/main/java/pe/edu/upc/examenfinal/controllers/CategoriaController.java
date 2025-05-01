package pe.edu.upc.examenfinal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.CategoriaDTO;
import pe.edu.upc.examenfinal.entities.Categoria;
import pe.edu.upc.examenfinal.serviceimplements.CategoriaService;

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
