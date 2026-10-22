package service;

import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceTest {

    @Mock
    private CuentaRepository cuentas;

    private Cuenta origen;
    private Cuenta destino;

    @BeforeEach
    void setUp() {
        origen = new Cuenta(UUID.randomUUID(), "Ana", Money.eur(10_000));
        destino = new Cuenta(UUID.randomUUID(), "Luis", Money.eur(500));

        when(cuentas.findById(origen.getId())).thenReturn(Optional.of(origen));
        when(cuentas.findById(destino.getId())).thenReturn(Optional.of(destino));
    }

    @Test
    void transferirEntreCuentasExistentesMueveElDineroYDevuelveTransferenciaCompletada() {
        TransferenciaService service = new TransferenciaService(cuentas);
        Transferencia transferencia = service.transferir(origen.getId(), destino.getId(), Money.eur(500));
        assertEquals(Money.eur(500), transferencia.getImporte());
        assertEquals(EstadoTransferencia.COMPLETADA, transferencia.getEstado());
        assertEquals(Money.eur(9_500), origen.getSaldo());
        assertEquals(Money.eur(1_000), destino.getSaldo());
    }

    @Test
    void rechazaTransferenciaPorSaldoInsuficienteDeOrigen() {
        TransferenciaService service = new TransferenciaService(cuentas);
        Transferencia transferencia = service.transferir(origen.getId(), destino.getId(), Money.eur(origen.getSaldo().cantidad()+1));
        assertEquals(EstadoTransferencia.RECHAZADA, transferencia.getEstado());
        assertEquals(Money.eur(10_000), origen.getSaldo());
        assertEquals(Money.eur(500), destino.getSaldo());
    }
}
