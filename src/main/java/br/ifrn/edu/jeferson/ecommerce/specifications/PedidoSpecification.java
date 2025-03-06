package br.ifrn.edu.jeferson.ecommerce.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoFilterDTO;
import jakarta.persistence.criteria.Predicate;

public class PedidoSpecification {

    public static Specification<Pedido> filtrar(PedidoFilterDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getStatusPedido() != null && !filtro.getStatusPedido().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("statusPedido"), StatusPedido.valueOf(filtro.getStatusPedido())));
            }

            if (filtro.getDataInicio() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataPedido"), filtro.getDataInicio().atStartOfDay()));
            }

            if (filtro.getDataFim() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataPedido"), filtro.getDataFim().atTime(23, 59, 59)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}