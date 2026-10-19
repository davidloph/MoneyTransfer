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
}
