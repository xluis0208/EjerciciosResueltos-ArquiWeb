package com.upc.authjwt20251.service;

import com.upc.authjwt20251.dto.CategoriaTransferidaDTO;
import com.upc.authjwt20251.dto.ListadoDTO;
import com.upc.authjwt20251.dto.ProductoDTO;
import com.upc.authjwt20251.model.Categoria;
import com.upc.authjwt20251.model.Producto;
import com.upc.authjwt20251.repository.CategoriaRepository;
import com.upc.authjwt20251.repository.ProductoRepository;
import jakarta.persistence.Tuple;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    final ProductoRepository productoRepository;
    final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }
    public ProductoDTO agregar(ProductoDTO productoDTO) {
        Producto productoNuevo = new Producto();
        ModelMapper mapper = new ModelMapper();
        productoNuevo = mapper.map(productoDTO, Producto.class);
        // Agregar la Categoria
        productoNuevo.setCategoria(categoriaRepository.findById(productoDTO.getCategoriaId()).get());

        productoRepository.save(productoNuevo);
        return mapper.map(productoNuevo, ProductoDTO.class);
    }
    public List<ProductoDTO> listar() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Producto producto : productos) {
            ProductoDTO productoDTO = mapper.map(producto, ProductoDTO.class);
            productoDTO.setCategoriaId(producto.getCategoria().getId());
            productosDTO.add(productoDTO);
        }
        return productosDTO;
    }
    public ProductoDTO transferir(Long id, CategoriaTransferidaDTO categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId.getCategoriaId()).get();
        Producto producto = productoRepository.findById(id).get();
        ProductoDTO productoDTO = new ProductoDTO();
        producto.setCategoria(categoria);
        productoRepository.save(producto);
        ModelMapper mapper = new ModelMapper();
        productoDTO = mapper.map(producto, ProductoDTO.class);
        productoDTO.setCategoriaId(categoriaId.getCategoriaId());
        return productoDTO;
    }
    public List<ListadoDTO> listado() {
        List<Tuple> tuplas = productoRepository.listado();
        List<ListadoDTO> listadoDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Tuple tuple : tuplas) {
            ListadoDTO listadoDTO = new ListadoDTO();
            listadoDTO.setNombre(tuple.get(0, String.class));
            listadoDTO.setDescripcion(tuple.get(1, String.class));
            listadoDTOS.add(listadoDTO);
        }
        return listadoDTOS;
    }
}
