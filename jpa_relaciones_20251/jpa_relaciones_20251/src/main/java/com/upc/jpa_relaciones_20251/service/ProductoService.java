package com.upc.jpa_relaciones_20251.service;

import com.upc.jpa_relaciones_20251.dto.ProductoDTO;
import com.upc.jpa_relaciones_20251.model.Categoria;
import com.upc.jpa_relaciones_20251.model.Producto;
import com.upc.jpa_relaciones_20251.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    public List<ProductoDTO> listar() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productoDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Producto producto : productos) {
            ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);
            if (producto.getCategoria() != null) {
                productoDTO.setCategoriaId(producto.getCategoria().getId());
            }
            productoDTOS.add(productoDTO);
        }
        return productoDTOS;
    }
    public ProductoDTO buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);
        if (producto.getCategoria() != null) {
            productoDTO.setCategoriaId(producto.getCategoria().getId());
        }
        return productoDTO;
    }
    public ProductoDTO guardar(ProductoDTO productoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = new Categoria();
            categoria.setId(productoDTO.getCategoriaId());
            producto.setCategoria(categoria);
        }
        producto = productoRepository.save(producto);
        productoDTO = modelMapper.map(producto, ProductoDTO.class);
        return productoDTO;
    }
}
