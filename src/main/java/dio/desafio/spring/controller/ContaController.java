package dio.desafio.spring.controller;

import dio.desafio.spring.facade.ContaFacade;
import dio.desafio.spring.model.Conta;
import jakarta.persistence.EntityNotFoundException;
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
    private ContaFacade contaFacade;

    @PostMapping
    public void salvar(@RequestBody Conta conta){
        contaFacade.salvarConta(conta);
    }

    @GetMapping("/contas")
    public List<Conta> listar(){
        return contaFacade.listarContasAtivas();
    }

    @GetMapping("/saldo/{num_conta}")
    public float retornaSaldo(@PathVariable ("num_conta") Integer num_conta){
        return contaFacade.obterSaldoPorNumConta(num_conta);
    }

    @PostMapping("/saque/{num_conta}/{valor}")
    public ResponseEntity<String> sacar(@PathVariable ("num_conta") Integer num_conta, @PathVariable ("valor") float valor) {
        try {
            boolean saqueBemSucedido = contaFacade.sacar(num_conta, valor);

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

    @PostMapping("/deposito/{num_conta}/{valor}")
    public ResponseEntity<String> depositar(@PathVariable ("num_conta") Integer num_conta, @PathVariable ("valor") float valor) {
        try {
            boolean depositoBemSucedido = contaFacade.depositar(num_conta, valor);

            if (depositoBemSucedido) {
                return ResponseEntity.ok("Depósito realizado com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar o depósito.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/desativar/{num_conta}")
    public ResponseEntity<String> desativarConta(@PathVariable ("num_conta") Integer num_conta) {
        boolean desativacaoBemSucedida = contaFacade.desativarConta(num_conta);

        if (desativacaoBemSucedida) {
            return ResponseEntity.ok("Conta desativada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível desativar a conta.");
        }
    }

    @DeleteMapping("/{num_conta}")
    public ResponseEntity<String> deletar(@PathVariable ("num_conta") Integer num_conta){
        boolean exclusaoBemSucedida = contaFacade.apagarConta(num_conta);

        if (exclusaoBemSucedida) {
            return ResponseEntity.ok("Conta excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada ou não pôde ser excluída.");
        }
    }

}