package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.UUID;

public class CuentaTest {

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {cuenta = new Cuenta(UUID.randomUUID(), "David Lopera Henao", Money.eur(0));}

    @Test
    void nuevaCuentaAlmacenaTitularYSaldoInicial() {
        assertEquals("David Lopera Henao", cuenta.getTitular());
        assertEquals(Money.eur(0), cuenta.getSaldo());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "       "})
    void rechazaNuevaCuentaSiNoTieneTitular(String titular) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Cuenta("", Money.eur(10_000)));
        assertTrue(ex.getMessage().contains("titular"));
    }

    @Test
    void rechazaSaldoInicialNegativo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Cuenta("David Lopera Henao", Money.eur(-10_000)));
        assertTrue(ex.getMessage().contains("cantidad"));
    }

    @Test
    void rechazaTransferenciaMayorAlSaldo() {
        NotEnoughBalanceException ex = assertThrows(NotEnoughBalanceException.class, () -> cuenta.transferir(UUID.fromString("fe3fd0d3-7fba-48ee-b9a9-6afc5efd1ff0"), Money.eur(10_001)));
    }
}
