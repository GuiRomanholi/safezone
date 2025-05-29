package br.com.fiap.safezone.repository;

import br.com.fiap.safezone.entity.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long> {
}
