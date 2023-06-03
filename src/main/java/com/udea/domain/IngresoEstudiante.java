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
 * A IngresoEstudiante.
 */
@Entity
@Table(name = "ingreso_estudiante")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IngresoEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_ingreso")
    private Instant fechaIngreso;

    @Column(name = "semestre_inscripcion")
    private String semestreInscripcion;

    @Column(name = "tipo_ingreso")
    private String tipoIngreso;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ingresoEstudiantes", "solicitudReingresos" }, allowSetters = true)
    private Estudiante estudiante;

    @ManyToOne
    @JsonIgnoreProperties(value = { "solicitudReingresos", "ingresoEstudiantes" }, allowSetters = true)
    private Carrera carrera;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ingresoEstudiantes" }, allowSetters = true)
    private Sede sede;

    @ManyToOne
    @JsonIgnoreProperties(value = { "materiasPensums", "ingresoEstudiantes" }, allowSetters = true)
    private Pensum pensum;

    @OneToMany(mappedBy = "ingresoEstudiante")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "ingresoEstudiante" }, allowSetters = true)
    private Set<DocumentoIngresoEstudiante> documentoIngresoEstudiantes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public IngresoEstudiante id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaIngreso() {
        return this.fechaIngreso;
    }

    public IngresoEstudiante fechaIngreso(Instant fechaIngreso) {
        this.setFechaIngreso(fechaIngreso);
        return this;
    }

    public void setFechaIngreso(Instant fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getSemestreInscripcion() {
        return this.semestreInscripcion;
    }

    public IngresoEstudiante semestreInscripcion(String semestreInscripcion) {
        this.setSemestreInscripcion(semestreInscripcion);
        return this;
    }

    public void setSemestreInscripcion(String semestreInscripcion) {
        this.semestreInscripcion = semestreInscripcion;
    }

    public String getTipoIngreso() {
        return this.tipoIngreso;
    }

    public IngresoEstudiante tipoIngreso(String tipoIngreso) {
        this.setTipoIngreso(tipoIngreso);
        return this;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public Estudiante getEstudiante() {
        return this.estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public IngresoEstudiante estudiante(Estudiante estudiante) {
        this.setEstudiante(estudiante);
        return this;
    }

    public Carrera getCarrera() {
        return this.carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public IngresoEstudiante carrera(Carrera carrera) {
        this.setCarrera(carrera);
        return this;
    }

    public Sede getSede() {
        return this.sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public IngresoEstudiante sede(Sede sede) {
        this.setSede(sede);
        return this;
    }

    public Pensum getPensum() {
        return this.pensum;
    }

    public void setPensum(Pensum pensum) {
        this.pensum = pensum;
    }

    public IngresoEstudiante pensum(Pensum pensum) {
        this.setPensum(pensum);
        return this;
    }

    public Set<DocumentoIngresoEstudiante> getDocumentoIngresoEstudiantes() {
        return this.documentoIngresoEstudiantes;
    }

    public void setDocumentoIngresoEstudiantes(Set<DocumentoIngresoEstudiante> documentoIngresoEstudiantes) {
        if (this.documentoIngresoEstudiantes != null) {
            this.documentoIngresoEstudiantes.forEach(i -> i.setIngresoEstudiante(null));
        }
        if (documentoIngresoEstudiantes != null) {
            documentoIngresoEstudiantes.forEach(i -> i.setIngresoEstudiante(this));
        }
        this.documentoIngresoEstudiantes = documentoIngresoEstudiantes;
    }

    public IngresoEstudiante documentoIngresoEstudiantes(Set<DocumentoIngresoEstudiante> documentoIngresoEstudiantes) {
        this.setDocumentoIngresoEstudiantes(documentoIngresoEstudiantes);
        return this;
    }

    public IngresoEstudiante addDocumentoIngresoEstudiante(DocumentoIngresoEstudiante documentoIngresoEstudiante) {
        this.documentoIngresoEstudiantes.add(documentoIngresoEstudiante);
        documentoIngresoEstudiante.setIngresoEstudiante(this);
        return this;
    }

    public IngresoEstudiante removeDocumentoIngresoEstudiante(DocumentoIngresoEstudiante documentoIngresoEstudiante) {
        this.documentoIngresoEstudiantes.remove(documentoIngresoEstudiante);
        documentoIngresoEstudiante.setIngresoEstudiante(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IngresoEstudiante)) {
            return false;
        }
        return id != null && id.equals(((IngresoEstudiante) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IngresoEstudiante{" +
            "id=" + getId() +
            ", fechaIngreso='" + getFechaIngreso() + "'" +
            ", semestreInscripcion='" + getSemestreInscripcion() + "'" +
            ", tipoIngreso='" + getTipoIngreso() + "'" +
            "}";
    }
}
