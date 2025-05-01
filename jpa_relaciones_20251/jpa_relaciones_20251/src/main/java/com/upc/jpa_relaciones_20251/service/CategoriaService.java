package com.upc.jpa_relaciones_20251.service;

import com.upc.jpa_relaciones_20251.dto.CategoriaDTO;
import com.upc.jpa_relaciones_20251.model.Categoria;
import com.upc.jpa_relaciones_20251.repository.CategoriaRepository;
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
        ModelMapper modelMapper = new ModelMapper();
        for (Categoria categoria : categorias) {
            CategoriaDTO categoriaDTO = modelMapper.map(categoria, CategoriaDTO.class);
            categoriaDTOS.add(categoriaDTO);
        }
        return categoriaDTOS;
    }
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoria, CategoriaDTO.class);
    }
    public CategoriaDTO  guardar(CategoriaDTO categoriaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        categoria = categoriaRepository.save(categoria);
        categoriaDTO = modelMapper.map(categoria, CategoriaDTO.class);
        return categoriaDTO;
    }
}
