package br.ifrn.edu.jeferson.ecommerce.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoFilterDTO;
import jakarta.persistence.criteria.Predicate;

public class ProdutoSpecification {
    public static Specification<Produto> filtrar(ProdutoFilterDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNome() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }

            if (filtro.getPrecoMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("preco"), filtro.getPrecoMin()));
            }

            if (filtro.getPrecoMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("preco"), filtro.getPrecoMax()));
            }

            if (filtro.getCategoriaId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("categorias").get("id"), filtro.getCategoriaId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
