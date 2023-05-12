package com.felix.market.persistence.crud;

import com.felix.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, String> {

    Optional<List<Compra>> findByIdCliente(String idCliente);
}
