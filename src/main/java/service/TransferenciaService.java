package service;


import domain.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class TransferenciaService {

    private final CuentaRepository cuentas;

    public TransferenciaService(CuentaRepository cuentas) {
        this.cuentas = Objects.requireNonNull(cuentas);
    }

    public Transferencia transferir(UUID idOrigen, UUID idDestino, Money monto) {
        if (idOrigen == null || idDestino == null) {
            throw new IllegalArgumentException("Cuenta de Origen y Destino no pueden ser nulos.");
        }
        if (monto == null) {
            throw new IllegalArgumentException("Monto no puede ser nulo.");
        }
        if (idOrigen.equals(idDestino)) {
            return rechazada(idOrigen, idDestino, monto, "Las cuentas de origen y destino deben ser diferentes.");
        }
        if (monto.cantidad() == 0) {
            return rechazada(idOrigen, idDestino, monto, "El monto debe ser mayor a 0.");
        }
        Optional<Cuenta> cuentaOrigenOpt = cuentas.findById(idOrigen);
        if (cuentaOrigenOpt.isEmpty()) {
            return rechazada(idOrigen, idDestino, monto, "No se encontró la cuenta de origen.");
        }
        Cuenta cuentaOrigen = cuentaOrigenOpt.get();
        Optional<Cuenta> cuentaDestinoOpt = cuentas.findById(idDestino);
        if (cuentaDestinoOpt.isEmpty()) {
            return rechazada(idOrigen, idDestino, monto, "No se encontró la cuenta de destino.");
        }
        Cuenta cuentaDestino = cuentaDestinoOpt.get();
        try {
            cuentaOrigen.retirar(monto);
        } catch (DifferentCurrencyException | NotEnoughBalanceException ex) {
            return rechazada(idOrigen, idDestino, monto, ex.getMessage());
        }
        try {
            cuentaDestino.depositar(monto);
        } catch (DifferentCurrencyException ex) {
            cuentaOrigen.depositar(monto);
            return rechazada(idOrigen, idDestino, monto, ex.getMessage());
        }
        return new Transferencia(idOrigen, idDestino, monto, EstadoTransferencia.COMPLETADA, "La transferencia se ha realizado correctamente.");
    }

    private Transferencia rechazada(UUID idOrigen, UUID idDestino, Money monto, String motivo) {
        return new Transferencia(idOrigen, idDestino, monto, EstadoTransferencia.RECHAZADA, motivo);
    }
}
