package dio.desafio.spring.controller;

import dio.desafio.spring.model.Conta;
import dio.desafio.spring.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/saque/{num_conta}/{valor}")
    public ResponseEntity<String> sacar(@PathVariable ("num_conta") Integer numConta, @PathVariable ("valor") float valor) {
        try {
            boolean saqueBemSucedido = contaService.sacar(numConta, valor);

            if (saqueBemSucedido) {
                return ResponseEntity.ok("Saque realizado com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar o saque.");
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível realizar o saque.", e);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada.", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor.", e);
        }
    }

    // Falta deposito

    @DeleteMapping("/{num_conta}")
    public ResponseEntity<String> deletar(@PathVariable ("num_conta") Integer num_conta){
        boolean exclusaoBemSucedida = contaService.apagarConta(num_conta);

        if (exclusaoBemSucedida) {
            return ResponseEntity.ok("Conta excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada ou não pôde ser excluída.");
        }
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

