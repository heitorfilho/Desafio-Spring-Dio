package dio.desafio.spring.facade;

import dio.desafio.spring.model.Conta;
import dio.desafio.spring.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaFacade {

    private ContaService contaService;

    @Autowired
    public ContaFacade(ContaService contaService) {
        this.contaService = contaService;
    }

    public List<Conta> listarContasAtivas() {
        return contaService.listarContas();
    }

    public float obterSaldoPorNumConta(Integer num_conta) {
        return contaService.obterSaldoPorNumConta(num_conta);
    }

    public boolean sacar(Integer num_conta, float valor) {
        return contaService.sacar(num_conta, valor);
    }

    public boolean depositar(Integer num_conta, float valor) {
        return contaService.depositar(num_conta, valor);
    }

    public void salvarConta(Conta conta) {
        contaService.salvarConta(conta);
    }

    public boolean desativarConta(Integer num_conta) {
        return contaService.desativarConta(num_conta);
    }

    public boolean apagarConta(Integer num_conta) {
        return contaService.apagarConta(num_conta);
    }
}