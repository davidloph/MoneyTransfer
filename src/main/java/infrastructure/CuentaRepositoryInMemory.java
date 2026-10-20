package infrastructure;

import domain.Cuenta;
import domain.CuentaRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CuentaRepositoryInMemory implements CuentaRepository {

    private final ConcurrentMap<UUID, Cuenta> cuentas = new ConcurrentHashMap<>();

    @Override
    public Optional<Cuenta> findById(UUID cuentaId) {
        return Optional.ofNullable(cuentas.get(cuentaId));
    }

    @Override
    public void save(Cuenta cuenta) {
        Objects.requireNonNull(cuenta, "La cuenta es necesaria.");
        cuentas.put(cuenta.getId(), cuenta);
    }
}
