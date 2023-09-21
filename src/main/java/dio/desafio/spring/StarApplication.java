package dio.desafio.spring;

import dio.desafio.spring.model.Conta;
import dio.desafio.spring.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StarApplication implements CommandLineRunner {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void run(String... args) throws Exception{
        Conta conta = new Conta();

        conta.setNum_conta(1);
        conta.setCpf_cliente("12345678912");
        conta.setSaldo(10000);
        conta.setAtiva(true);

        contaRepository.save(conta);

//        for(Conta u: contaRepository.findAll()){
//            System.out.println(u);
//        }
    }
}
