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

    public Money restar(Money saldoATransferir) {
        return new Money(cantidad - saldoATransferir.cantidad(), EUR);
    }
}
