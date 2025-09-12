package com.tjportas.pedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tjportas.pedidos.entity.Pedidos;
import com.tjportas.pedidos.entity.TipoPedido;
import com.tjportas.pedidos.repository.PedidosRepository;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class PedidosController {
    
    @Autowired
    PedidosRepository repository;

    //Create
    @PostMapping("/pedidos")
    public ResponseEntity<Pedidos> salvar(@RequestBody Pedidos pedidos) {
        Pedidos pedidosalvo = repository.save(pedidos);

        return new ResponseEntity<>(pedidosalvo, HttpStatus.OK);
    }
    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedidos>> listar() {
        List<Pedidos> pedidos = new ArrayList<>();
        repository.findAll().forEach(pedidos::add);

        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    
    }
    // Update
    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Pedidos> atualizar(@PathVariable Long id, @RequestBody Pedidos pedidos) {

        Optional<Pedidos> pedidosDesatualizado = repository.findById(id);

        if (pedidosDesatualizado.isPresent()) {
            Pedidos pedidoAtualizado = pedidosDesatualizado.get();
            pedidoAtualizado.setNome(pedidos.getNome());
            pedidoAtualizado.setTelefone(pedidos.getTelefone());
            pedidoAtualizado.setRegiao(pedidos.getRegiao());
            pedidoAtualizado.setProduto(pedidos.getProduto());
            pedidoAtualizado.setQuantidade(pedidos.getQuantidade());
            
            return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Pedidos> deletar(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
/* 
    @RequestMapping("/pedidos")
    public List<pedidos> listar() {

        Clientes c1 = new Clientes(1L, "Victor", 123456789, 999999999, Regiao.PORTO_RICO);
        Clientes c2 = new Clientes(2L, "Davide", 987654321, 888888888, Regiao.MARINGA);
        Clientes c3 = new Clientes(3L, "Gustavo", 456789123, 777777777, Regiao.PARANAVAI);
        Clientes c4 = new Clientes(4L, "Gabriel", 789123456, 666666666, Regiao.CIANORTE);
        Clientes c5 = new Clientes(5L, "Raul", 321654987, 555555555, Regiao.LOANDA);
        

        return Arrays.asList(c1, c2, c3, c4, c5);

    }
        */
}
