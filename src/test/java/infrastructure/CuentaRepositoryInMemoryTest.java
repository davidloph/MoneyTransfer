package infrastructure;

import domain.Cuenta;
import domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CuentaRepositoryInMemoryTest {

    CuentaRepositoryInMemory cuentaRepository;
    Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuentaRepository = new CuentaRepositoryInMemory();
        cuenta = new Cuenta(UUID.randomUUID(), "Ana", Money.eur(10_000));
    }

    @Test
    void seGuardanYEncuentranCuentasCorrectamente() {
        cuentaRepository.save(cuenta);
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(cuenta.getId());
        assertEquals(cuenta, cuentaOpt.get());
    }
}
