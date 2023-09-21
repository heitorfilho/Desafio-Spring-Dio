package dio.desafio.spring.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_conta")
    private Integer num_conta;
    @Column(length = 11, nullable = false)
    private String cpf_cliente;
    @Column(length = 10, nullable = false)
    private float saldo;
    @Column(length = 1, nullable = false)
    private boolean ativa;

    public Conta(Integer num_conta, String cpf_cliente, float saldo, boolean ativa) {
        this.num_conta = num_conta;
        this.cpf_cliente = cpf_cliente;
        this.saldo = saldo;
        this.ativa = ativa;
    }

    public Conta() {
    }

    public Integer getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(Integer num_conta) {
        this.num_conta = num_conta;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Float.compare(conta.saldo, saldo) == 0 && ativa == conta.ativa && Objects.equals(num_conta, conta.num_conta) && Objects.equals(cpf_cliente, conta.cpf_cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num_conta, cpf_cliente, saldo, ativa);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "num_conta=" + num_conta +
                ", cpf_cliente=" + cpf_cliente +
                ", saldo=" + saldo +
                ", ativa=" + ativa +
                '}';
    }
}
