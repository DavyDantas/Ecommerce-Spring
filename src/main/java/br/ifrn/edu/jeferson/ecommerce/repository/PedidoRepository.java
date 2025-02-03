package br.ifrn.edu.jeferson.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

    public List<Pedido> findByClienteId(Long clienteId);

    
    @SuppressWarnings("null")
    @Override
    public Page<Pedido> findAll(@SuppressWarnings("rawtypes") Specification filter, Pageable pageable);

}
