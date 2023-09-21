package dio.desafio.spring.repository;

import dio.desafio.spring.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Contem os metodos atribuidos para salvar no banco de dados

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query("SELECT c FROM Conta c WHERE c.ativa = true")
    List<Conta> findAllAtivas();

    @Query("SELECT c.saldo FROM Conta c WHERE c.num_conta = :num_conta")
    float findSaldoByNumConta(@Param("num_conta") Integer num_conta);

}
