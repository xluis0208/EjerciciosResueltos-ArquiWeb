package com.upc.jpa_relaciones_20251.repository;

import com.upc.jpa_relaciones_20251.model.Cliente;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "select c.nombre as cliente, p.nombre as producto from Cliente c JOIN cliente_producto cp ON cp.cliente_id = c.id\n" +
            "       JOIN producto p ON cp.producto_id = p.id", nativeQuery = true)
    List<Tuple> productosDeCliente();

    List<Cliente> findByHabilitado(boolean habilitado);
}
