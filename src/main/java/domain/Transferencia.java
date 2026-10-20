package domain;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Transferencia {

    UUID id;
    UUID cuentaOrigen;
    UUID cuentaDestino;
    Money importe;
    LocalDateTime fecha;
    EstadoTransferencia estado;
    String descripcion;

    public Transferencia(UUID id, UUID cuentaOrigen, UUID cuentaDestino, Money importe, LocalDateTime fecha, EstadoTransferencia estado, String descripcion) {
        this.id = id;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.importe = importe;
        this.fecha = fecha;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Transferencia(UUID cuentaOrigen, UUID cuentaDestino, Money importe, LocalDateTime fecha, EstadoTransferencia estado, String descripcion) {
        setId(UUID.randomUUID());
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.importe = importe;
        this.fecha = fecha;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Transferencia(UUID cuentaOrigen, UUID cuentaDestino, Money importe, EstadoTransferencia estado, String descripcion) {
        setId(UUID.randomUUID());
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.importe = importe;
        setFecha(LocalDateTime.now());
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUUIDOrigen() {
        return cuentaOrigen;
    }

    public void setUUIDOrigen(UUID cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public UUID getUUIDDestino() {
        return cuentaDestino;
    }

    public void setUUIDDestino(UUID cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Money getImporte() {
        return importe;
    }

    public void setImporte(Money importe) {
        this.importe = importe;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoTransferencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransferencia estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
