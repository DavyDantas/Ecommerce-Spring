package br.ifrn.edu.jeferson.ecommerce.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoFilterDTO;
import jakarta.persistence.criteria.Predicate;

public class PedidoSpecification {
    public static Specification<Pedido> filtrar(PedidoFilterDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getStatusPedido() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filtro.getStatusPedido()));
            }

            if (filtro.getDataInicio() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataInicio()));
            }

            if (filtro.getDataFim() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataFim()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

