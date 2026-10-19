package domain;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Cuenta {

    private UUID id;
    private String titular;
    private AtomicReference<Money> saldo;

    public Cuenta(UUID uuid, String titular, Money saldo) {
        setId(uuid);
        setTitular(titular);
        setSaldoInicial(saldo);
    }

    public Cuenta(String titular, Money saldo) {
        setId(UUID.randomUUID());
        setTitular(titular);
        setSaldoInicial(saldo);
    }

    public UUID getUuid() {
        return id;
    }

    private void setId(UUID uuid) {
        this.id = uuid;
    }

    public String getTitular() {
        return titular;
    }

    private void setTitular(String titular) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("El titular no puede ser nulo.");
        }
        this.titular = titular;
    }

    public Money getSaldo() {
        return  saldo.get();
    }

    private void setSaldoInicial(Money saldoInicial) {
        this.saldo = new AtomicReference<>(saldoInicial);
    }
}
