package domain;

import java.util.Optional;
import java.util.UUID;

public interface CuentaRepository {
    Optional<Cuenta> findById(UUID id);
    void save(Cuenta cuenta);
}
