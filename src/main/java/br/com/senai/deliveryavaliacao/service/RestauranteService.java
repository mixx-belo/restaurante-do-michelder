package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante cadastrar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscarPorId(Integer id) {
        return restauranteRepository.findById(id);
    }

    public Optional<Restaurante> atualizar(Integer id, Restaurante restauranteAtualizado) {
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);
        if (restauranteExistente.isPresent()) {
            Restaurante restaurante = restauranteExistente.get();
            restaurante.setNome(restauranteAtualizado.getNome());
            restaurante.setEndereco(restauranteAtualizado.getEndereco());
            return Optional.of(restauranteRepository.save(restaurante));
        } else {
            return Optional.empty();
        }
    }

    public boolean excluir(Integer id) {

        if (!restauranteRepository.existsById(id)) {
            return false;
        }

        restauranteRepository.deleteById(id);
        return true;
    }
}
