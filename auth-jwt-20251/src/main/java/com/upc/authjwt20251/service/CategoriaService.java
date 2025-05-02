package com.upc.authjwt20251.service;

import com.upc.authjwt20251.dto.CategoriaDTO;
import com.upc.authjwt20251.model.Categoria;
import com.upc.authjwt20251.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    public List<CategoriaDTO> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Categoria categoria : categorias) {
            CategoriaDTO categoriaDTO = mapper.map(categoria, CategoriaDTO.class);
            categoriaDTOS.add(categoriaDTO);
        }
        return categoriaDTOS;
    }
    public Categoria agregar(CategoriaDTO categoriaDTO) {
        ModelMapper mapper = new ModelMapper();
        Categoria categoria = mapper.map(categoriaDTO, Categoria.class);
        return categoriaRepository.save(categoria);
    }
}
