package dio.desafio.spring.service;

import dio.desafio.spring.model.Conta;
import dio.desafio.spring.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Contem regra de negocios
@Service
public class ContaService {

    private ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public List<Conta> listarContas(){
        return contaRepository.findAllAtivas();
    }

    public float obterSaldoPorNumConta(Integer num_conta) {
        return contaRepository.findSaldoByNumConta(num_conta);
    }

    public boolean sacar(Integer numConta, float valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }

        Conta conta = contaRepository.findByNumConta(numConta);

        if (conta == null) {
            throw new EntityNotFoundException("Conta não encontrada.");
        }

        if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }

        int rowsUpdated = contaRepository.withdraw(numConta, valor);

        return rowsUpdated > 0;
    }

    public boolean depositar(Integer numConta, float valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }

        Conta conta = contaRepository.findByNumConta(numConta);

        if (conta == null) {
            throw new EntityNotFoundException("Conta não encontrada.");
        }

        int rowsUpdated = contaRepository.deposit(numConta, valor);

        return rowsUpdated > 0;
    }

    public void salvarConta(Conta conta){
//     if(consultaCpf(conta.getCpf_cliente()) == true){} // ver se o cpf do cliente existe no banco de dados
       contaRepository.save(conta);
    }

    public boolean apagarConta(Integer num_conta) {
        if(!contaRepository.isContaAtiva(num_conta)){
            contaRepository.deleteConta(num_conta);
            return true;
        }
        else{
            return false;
        }
    }



}
