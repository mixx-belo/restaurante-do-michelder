package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Optional<Produto> cadastrar(ProdutoRequest dto) {
        if (dto.getRestauranteId() == null) {
            return Optional.empty();
        }

        Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(dto.getRestauranteId());

        if (restauranteEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = new Produto();

        produto.setRestaurante(restauranteEncontrado.get());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        return Optional.of(produtoRepository.save(produto));
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> atualizar(Integer id, ProdutoRequest dto) {
        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);

        if (produtoEncontrado.isEmpty() || dto.getRestauranteId() == null) {
            return Optional.empty();
        }

        Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(dto.getRestauranteId());

        if (restauranteEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = produtoEncontrado.get();
        produto.setRestaurante(restauranteEncontrado.get());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        return Optional.of(produtoRepository.save(produto));
    }

    public boolean excluir(Integer id) {
        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);

        if (produtoEncontrado.isEmpty()) {
            return false;
        }

        produtoRepository.delete(produtoEncontrado.get());
        return true;
    }
}
