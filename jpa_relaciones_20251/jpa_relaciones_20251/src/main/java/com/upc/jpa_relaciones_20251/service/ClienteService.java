package com.upc.jpa_relaciones_20251.service;

import com.upc.jpa_relaciones_20251.dto.ClienteDTO;
import com.upc.jpa_relaciones_20251.dto.ClienteProductoDTO;
import com.upc.jpa_relaciones_20251.dto.ClienteProductoResumenDTO;
import com.upc.jpa_relaciones_20251.model.Cliente;
import com.upc.jpa_relaciones_20251.model.Producto;
import com.upc.jpa_relaciones_20251.repository.ClienteRepository;
import com.upc.jpa_relaciones_20251.repository.ProductoRepository;
import jakarta.persistence.Tuple;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ClienteService {
    final ClienteRepository clienteRepository;
    final ProductoService productoService;
    private final ProductoRepository productoRepository;

    Logger log = Logger.getLogger(ClienteService.class.getName());

    public ClienteService(ClienteRepository clienteRepository, ProductoService productoService, ProductoRepository productoRepository) {
        this.clienteRepository = clienteRepository;
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }
    public List<ClienteDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Cliente cliente : clientes) {
            ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
            clienteDTOS.add(clienteDTO);
        }
        return clienteDTOS;
    }
    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cliente, ClienteDTO.class);
    }
    public ClienteDTO guardar(ClienteDTO clienteDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente = clienteRepository.save(cliente);
        clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        return clienteDTO;

    }
    public ClienteProductoDTO agregarProductoACliente(ClienteProductoDTO clienteProductoDTO) {
        Cliente cliente = clienteRepository.findById(clienteProductoDTO.getClienteId()).orElseThrow(()-> new RuntimeException("No existe el cliente con id: "+clienteProductoDTO.getClienteId()));
        log.info("usar el cliente "+cliente.getId().toString());

        Producto producto = productoRepository.findById(clienteProductoDTO.getProductoId()).orElseThrow(()-> new RuntimeException("No existe el producto con id: "+clienteProductoDTO.getProductoId()));
        log.info("usar el producto "+producto.getId().toString());

        cliente.getProductos().add(producto);
        log.info("agregar el producto "+producto.getId().toString()+" al cliente "+cliente.getId().toString());

        Cliente cli = clienteRepository.save(cliente);
        log.info("guardar el cliente "+cli.getId().toString());
        return clienteProductoDTO;
    }
    public List<ClienteProductoResumenDTO> resumen() {
        List<Tuple> resumen = clienteRepository.productosDeCliente();
        List<ClienteProductoResumenDTO> clienteProductoResumenDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Tuple tuple : resumen) {
            String cliente = tuple.get(0, String.class);
            String producto = tuple.get(1, String.class);
            ClienteProductoResumenDTO clienteProductoResumenDTO = new ClienteProductoResumenDTO(cliente, producto);
            clienteProductoResumenDTOS.add(clienteProductoResumenDTO);
        }
        return clienteProductoResumenDTOS;
    }
    public List<ClienteDTO> habilitados(boolean habilitado) {
        List<Cliente> clientes = clienteRepository.findByHabilitado(habilitado);
        List<ClienteDTO> clienteDTOs = new ArrayList<>();
        ClienteDTO clienteDTO;
        ModelMapper modelMapper = new ModelMapper();
        for (Cliente cliente : clientes) {
            clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
            clienteDTOs.add(clienteDTO);
        }
        return clienteDTOs;

    }
}
