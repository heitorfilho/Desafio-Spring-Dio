package dio.desafio.spring.repository;

import dio.desafio.spring.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Contem os metodos atribuidos para salvar no banco de dados

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query("SELECT c FROM Conta c WHERE c.num_conta = :num_conta")
    Conta findByNumConta(@Param("num_conta") Integer num_conta);

    @Query("SELECT c FROM Conta c WHERE c.ativa = true")
    List<Conta> findAllAtivas();

    @Query("SELECT c.ativa FROM Conta c WHERE c.num_conta = :num_conta")
    Boolean isContaAtiva(@Param("num_conta") Integer num_conta);

    @Query("SELECT c.saldo FROM Conta c WHERE c.num_conta = :num_conta")
    float findSaldoByNumConta(@Param("num_conta") Integer num_conta);

    // Sacar valor
    @Modifying
    @Query("UPDATE Conta c SET c.saldo = c.saldo - :valor WHERE c.num_conta = :num_conta")
    int withdraw(@Param("num_conta") Integer num_conta, @Param("valor") float valor);

    // Depositar valor
    @Modifying
    @Query("UPDATE Conta c SET c.saldo = c.saldo + :valor WHERE c.num_conta = :num_conta")
    int deposit(@Param("num_conta") Integer num_conta, @Param("valor") float valor);

    @Modifying
    @Query("UPDATE Conta c SET c.ativa = false WHERE c.num_conta = :num_conta")
    int disableConta(@Param("num_conta") Integer num_conta);

    @Modifying
    @Query("DELETE FROM Conta c WHERE c.num_conta = :num_conta")
    void deleteConta(@Param("num_conta") Integer num_conta);
}
