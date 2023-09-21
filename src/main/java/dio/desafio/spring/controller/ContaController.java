package dio.desafio.spring.controller;

import dio.desafio.spring.model.Conta;
import dio.desafio.spring.serive.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Contem os paths que farao o controle da Conta

@Controller
@ResponseBody
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public void salvar(@RequestBody Conta conta){
        contaService.salvarConta(conta);
    }

    @GetMapping("/contas")
    public List<Conta> listar(){
        return contaService.listarContas();
    }

    @GetMapping("/saldo/{num_conta}")
    public float retornaSaldo(@PathVariable ("num_conta") Integer num_conta){
        return contaService.obterSaldoPorNumConta(num_conta);
    }
}

//    Sistema WEB/ API REST
//        CRUD
//
//        Banco de dados: postgresql
//
//        API para Create, Read, Update, Delete Conta bancária
//
//        Os Principais dados de alteração serão o valor do saldo através de depósitos e saques
//
//        -----------------------------------
//        Etapas
//
//        Criação das Classes Conta, Controller, Service, Model, Repository

