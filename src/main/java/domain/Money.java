package domain;

import java.util.Currency;
import java.util.Objects;

public record Money(long cantidad, Currency moneda) {

    public static final Currency EUR = Currency.getInstance("EUR");

    public Money {
        Objects.requireNonNull(moneda, "La moneda no puede ser nula.");
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva.");
        }
    }

    public static Money eur(long cantidad) {
        return new Money(cantidad, EUR);
    }

    public Money restar(Money saldo) {
        if (!isMonedaIgual(saldo)) throw new DifferentCurrencyException("La moneda no puede ser diferente");
        if (saldo.cantidad() > cantidad) {
            throw new NotEnoughBalanceException("No tiene sueldo suficiente para la transferencia.");
        }
        return new Money(cantidad - saldo.cantidad(), EUR);
    }

    public Money sumar(Money saldo) {
        if (!isMonedaIgual(saldo)) throw new DifferentCurrencyException("La moneda no puede ser diferente");
        return new Money(cantidad + saldo.cantidad(), EUR);
    }

    private boolean isMonedaIgual(Money otro) {
        return moneda.equals(otro.moneda);
    }
}
