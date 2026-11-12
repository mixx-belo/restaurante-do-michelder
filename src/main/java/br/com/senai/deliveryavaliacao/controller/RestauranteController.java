package br.com.senai.deliveryavaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.service.RestauranteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    public ResponseEntity<Restaurante> cadastrarRestaurante(@Valid @RequestBody Restaurante restaurante) {
        Restaurante novoRestaurante = restauranteService.cadastrar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteService.listar();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarRestaurantePorId(@PathVariable Integer id) {
        Optional<Restaurante> restaurante = restauranteService.buscarPorId(id);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizarRestaurante(
            @PathVariable Integer id,
            @Valid @RequestBody Restaurante restauranteAtualizado) {

        Optional<Restaurante> restauranteAtual = restauranteService.atualizar(id, restauranteAtualizado);

        if (restauranteAtual.isPresent()) {
            return ResponseEntity.ok(restauranteAtual.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        boolean excluido = restauranteService.excluir(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
