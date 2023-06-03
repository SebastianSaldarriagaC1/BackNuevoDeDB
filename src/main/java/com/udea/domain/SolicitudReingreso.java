package com.udea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SolicitudReingreso.
 */
@Entity
@Table(name = "solicitud_reingreso")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolicitudReingreso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_solicitud")
    private Instant fechaSolicitud;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "carrera_solicitada")
    private Long carreraSolicitada;

    @ManyToOne
    @JsonIgnoreProperties(value = { "solicitudReingresos", "ingresoEstudiantes" }, allowSetters = true)
    private Carrera carrera;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ingresoEstudiantes", "solicitudReingresos" }, allowSetters = true)
    private Estudiante estudiante;

    @OneToMany(mappedBy = "solicitudReingreso")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "solicitudReingreso" }, allowSetters = true)
    private Set<DocumentoReingresoEstudiante> documentoReingresoEstudiantes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SolicitudReingreso id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    public SolicitudReingreso fechaSolicitud(Instant fechaSolicitud) {
        this.setFechaSolicitud(fechaSolicitud);
        return this;
    }

    public void setFechaSolicitud(Instant fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public SolicitudReingreso motivo(String motivo) {
        this.setMotivo(motivo);
        return this;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getCarreraSolicitada() {
        return this.carreraSolicitada;
    }

    public SolicitudReingreso carreraSolicitada(Long carreraSolicitada) {
        this.setCarreraSolicitada(carreraSolicitada);
        return this;
    }

    public void setCarreraSolicitada(Long carreraSolicitada) {
        this.carreraSolicitada = carreraSolicitada;
    }

    public Carrera getCarrera() {
        return this.carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public SolicitudReingreso carrera(Carrera carrera) {
        this.setCarrera(carrera);
        return this;
    }

    public Estudiante getEstudiante() {
        return this.estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public SolicitudReingreso estudiante(Estudiante estudiante) {
        this.setEstudiante(estudiante);
        return this;
    }

    public Set<DocumentoReingresoEstudiante> getDocumentoReingresoEstudiantes() {
        return this.documentoReingresoEstudiantes;
    }

    public void setDocumentoReingresoEstudiantes(Set<DocumentoReingresoEstudiante> documentoReingresoEstudiantes) {
        if (this.documentoReingresoEstudiantes != null) {
            this.documentoReingresoEstudiantes.forEach(i -> i.setSolicitudReingreso(null));
        }
        if (documentoReingresoEstudiantes != null) {
            documentoReingresoEstudiantes.forEach(i -> i.setSolicitudReingreso(this));
        }
        this.documentoReingresoEstudiantes = documentoReingresoEstudiantes;
    }

    public SolicitudReingreso documentoReingresoEstudiantes(Set<DocumentoReingresoEstudiante> documentoReingresoEstudiantes) {
        this.setDocumentoReingresoEstudiantes(documentoReingresoEstudiantes);
        return this;
    }

    public SolicitudReingreso addDocumentoReingresoEstudiante(DocumentoReingresoEstudiante documentoReingresoEstudiante) {
        this.documentoReingresoEstudiantes.add(documentoReingresoEstudiante);
        documentoReingresoEstudiante.setSolicitudReingreso(this);
        return this;
    }

    public SolicitudReingreso removeDocumentoReingresoEstudiante(DocumentoReingresoEstudiante documentoReingresoEstudiante) {
        this.documentoReingresoEstudiantes.remove(documentoReingresoEstudiante);
        documentoReingresoEstudiante.setSolicitudReingreso(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SolicitudReingreso)) {
            return false;
        }
        return id != null && id.equals(((SolicitudReingreso) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SolicitudReingreso{" +
            "id=" + getId() +
            ", fechaSolicitud='" + getFechaSolicitud() + "'" +
            ", motivo='" + getMotivo() + "'" +
            ", carreraSolicitada=" + getCarreraSolicitada() +
            "}";
    }
}
