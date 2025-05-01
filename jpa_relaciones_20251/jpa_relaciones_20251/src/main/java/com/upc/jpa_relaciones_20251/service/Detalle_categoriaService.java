package com.upc.jpa_relaciones_20251.service;

import com.upc.jpa_relaciones_20251.dto.Detalle_categoriaDTO;
import com.upc.jpa_relaciones_20251.model.Categoria;
import com.upc.jpa_relaciones_20251.model.Detalle_categoria;
import com.upc.jpa_relaciones_20251.repository.Detalle_categoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Detalle_categoriaService {
    final Detalle_categoriaRepository detalle_categoriaRepository;

    public Detalle_categoriaService(Detalle_categoriaRepository detalleCategoriaRepository) {
        detalle_categoriaRepository = detalleCategoriaRepository;
    }
    public List<Detalle_categoriaDTO> listar() {
        List<Detalle_categoria> detalle_categorias = detalle_categoriaRepository.findAll();
        List<Detalle_categoriaDTO> detalle_categoriaDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Detalle_categoria detalle_categoria : detalle_categorias) {
            Detalle_categoriaDTO detalle_categoriaDTO = modelMapper.map(detalle_categoria, Detalle_categoriaDTO.class);
            if (detalle_categoria.getCategoria() != null) {
                detalle_categoriaDTO.setCategoriaId(detalle_categoria.getCategoria().getId());
            }
            detalle_categoriaDTOS.add(detalle_categoriaDTO);
        }
        return detalle_categoriaDTOS;
    }
    public Detalle_categoriaDTO buscarPorId(Long id) {
        Detalle_categoria detalle_categoria = detalle_categoriaRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        Detalle_categoriaDTO detalle_categoriaDTO = modelMapper.map(detalle_categoria, Detalle_categoriaDTO.class);
        if (detalle_categoria.getCategoria() != null) {
            detalle_categoriaDTO.setCategoriaId(detalle_categoria.getCategoria().getId());
        }
        return detalle_categoriaDTO;
    }
    public Detalle_categoriaDTO guardar(Detalle_categoriaDTO detalle_categoriaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Detalle_categoria detalle_categoria = modelMapper.map(detalle_categoriaDTO, Detalle_categoria.class);
        if (detalle_categoriaDTO.getCategoriaId() != null) {
            Categoria categoria = new Categoria();
            categoria.setId(detalle_categoriaDTO.getCategoriaId());
            detalle_categoria.setCategoria(categoria);
        }
        detalle_categoria = detalle_categoriaRepository.save(detalle_categoria);
        detalle_categoriaDTO = modelMapper.map(detalle_categoria, Detalle_categoriaDTO.class);
        return detalle_categoriaDTO;
    }
}
