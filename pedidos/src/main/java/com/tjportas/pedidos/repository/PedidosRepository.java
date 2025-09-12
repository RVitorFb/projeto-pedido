package com.tjportas.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tjportas.pedidos.entity.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {


}
