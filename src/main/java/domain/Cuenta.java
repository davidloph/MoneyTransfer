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
        setSaldo(saldo);
    }

    public Cuenta(String titular, Money saldo) {
        setId(UUID.randomUUID());
        setTitular(titular);
        setSaldo(saldo);
    }

    public Cuenta() {
        setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        setTitular("Notfound");
        setSaldo(Money.eur(0));
    }

    public UUID getId() {
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

    private void setSaldo(Money saldo) {
        this.saldo = new AtomicReference<>(saldo);
    }

    public void retirar(Money saldoARetirar) {
            this.saldo.updateAndGet(saldoActual -> saldoActual.restar(saldoARetirar));
    }

    public void depositar(Money saldoADepositar) {
        this.saldo.updateAndGet(saldoActual -> saldoActual.sumar(saldoADepositar));
    }
}
