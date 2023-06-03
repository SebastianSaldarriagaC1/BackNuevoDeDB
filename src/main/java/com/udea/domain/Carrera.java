package com.udea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Carrera.
 */
@Entity
@Table(name = "carrera")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Carrera implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_carrera")
    private String nombreCarrera;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "facultad")
    private String facultad;

    @OneToMany(mappedBy = "carrera")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "carrera", "estudiante", "documentoReingresoEstudiantes" }, allowSetters = true)
    private Set<SolicitudReingreso> solicitudReingresos = new HashSet<>();

    @OneToMany(mappedBy = "carrera")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "estudiante", "carrera", "sede", "pensum", "documentoIngresoEstudiantes" }, allowSetters = true)
    private Set<IngresoEstudiante> ingresoEstudiantes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Carrera id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCarrera() {
        return this.nombreCarrera;
    }

    public Carrera nombreCarrera(String nombreCarrera) {
        this.setNombreCarrera(nombreCarrera);
        return this;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getModalidad() {
        return this.modalidad;
    }

    public Carrera modalidad(String modalidad) {
        this.setModalidad(modalidad);
        return this;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getFacultad() {
        return this.facultad;
    }

    public Carrera facultad(String facultad) {
        this.setFacultad(facultad);
        return this;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public Set<SolicitudReingreso> getSolicitudReingresos() {
        return this.solicitudReingresos;
    }

    public void setSolicitudReingresos(Set<SolicitudReingreso> solicitudReingresos) {
        if (this.solicitudReingresos != null) {
            this.solicitudReingresos.forEach(i -> i.setCarrera(null));
        }
        if (solicitudReingresos != null) {
            solicitudReingresos.forEach(i -> i.setCarrera(this));
        }
        this.solicitudReingresos = solicitudReingresos;
    }

    public Carrera solicitudReingresos(Set<SolicitudReingreso> solicitudReingresos) {
        this.setSolicitudReingresos(solicitudReingresos);
        return this;
    }

    public Carrera addSolicitudReingreso(SolicitudReingreso solicitudReingreso) {
        this.solicitudReingresos.add(solicitudReingreso);
        solicitudReingreso.setCarrera(this);
        return this;
    }

    public Carrera removeSolicitudReingreso(SolicitudReingreso solicitudReingreso) {
        this.solicitudReingresos.remove(solicitudReingreso);
        solicitudReingreso.setCarrera(null);
        return this;
    }

    public Set<IngresoEstudiante> getIngresoEstudiantes() {
        return this.ingresoEstudiantes;
    }

    public void setIngresoEstudiantes(Set<IngresoEstudiante> ingresoEstudiantes) {
        if (this.ingresoEstudiantes != null) {
            this.ingresoEstudiantes.forEach(i -> i.setCarrera(null));
        }
        if (ingresoEstudiantes != null) {
            ingresoEstudiantes.forEach(i -> i.setCarrera(this));
        }
        this.ingresoEstudiantes = ingresoEstudiantes;
    }

    public Carrera ingresoEstudiantes(Set<IngresoEstudiante> ingresoEstudiantes) {
        this.setIngresoEstudiantes(ingresoEstudiantes);
        return this;
    }

    public Carrera addIngresoEstudiante(IngresoEstudiante ingresoEstudiante) {
        this.ingresoEstudiantes.add(ingresoEstudiante);
        ingresoEstudiante.setCarrera(this);
        return this;
    }

    public Carrera removeIngresoEstudiante(IngresoEstudiante ingresoEstudiante) {
        this.ingresoEstudiantes.remove(ingresoEstudiante);
        ingresoEstudiante.setCarrera(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Carrera)) {
            return false;
        }
        return id != null && id.equals(((Carrera) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Carrera{" +
            "id=" + getId() +
            ", nombreCarrera='" + getNombreCarrera() + "'" +
            ", modalidad='" + getModalidad() + "'" +
            ", facultad='" + getFacultad() + "'" +
            "}";
    }
}
