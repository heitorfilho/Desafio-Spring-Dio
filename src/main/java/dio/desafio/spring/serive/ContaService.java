package dio.desafio.spring.serive;

import dio.desafio.spring.model.Conta;
import dio.desafio.spring.repository.ContaRepository;
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

    public void salvarConta(Conta conta){

//     if(consultaCpf(conta.getCpf_cliente()) == true){} // ver se o cpf do cliente existe no banco de dados
       contaRepository.save(conta);

    }
}
