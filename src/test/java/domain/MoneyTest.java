package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    Money dinero;

    @BeforeEach
    void setUp() {dinero = Money.eur(10_000);}

    @Test
    void rechazaSaldoInicialNegativo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Money.eur(-1));
        assertTrue(ex.getMessage().contains("cantidad"));
    }

    @Test
    void retirarMasQueElSaldoLanzaExcepcionYNoCambiaElSaldo() {
        assertThrows(NotEnoughBalanceException.class, () -> dinero.restar(Money.eur(dinero.cantidad()+1)));
        assertEquals(10_000, dinero.cantidad());
    }

    @Test
    void restaValorRetiradoAlSaldo() {
        assertEquals(Money.eur(9_800), dinero.restar(Money.eur(200)));
    }

    @Test
    void sumaValorDepositadoAlSaldo() {
        assertEquals(Money.eur(10_200), dinero.sumar(Money.eur(200)));
    }

    @Test
    void retirarDineroEnUnaMonedaDiferenteLanzaErrorYNoCambiaElSaldo() {
        assertThrows(DifferentCurrencyException.class, () -> dinero.restar(new Money(200, Currency.getInstance("COP"))));
        assertEquals(Money.eur(10_000), dinero);
    }

    @Test
    void depositarDineroEnUnaMonedaDiferenteLanzaErrorYNoCambiaElSaldo() {
        assertThrows(DifferentCurrencyException.class, () -> dinero.sumar(new Money(200, Currency.getInstance("COP"))));
        assertEquals(Money.eur(10_000), dinero);
    }
}
