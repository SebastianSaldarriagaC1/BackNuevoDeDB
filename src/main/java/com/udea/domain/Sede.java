package com.udea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Sede.
 */
@Entity
@Table(name = "sede")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_sede")
    private String nombreSede;

    @Column(name = "direccion_sede")
    private String direccionSede;

    @Column(name = "regional")
    private String regional;

    @OneToMany(mappedBy = "sede")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "estudiante", "carrera", "sede", "pensum", "documentoIngresoEstudiantes" }, allowSetters = true)
    private Set<IngresoEstudiante> ingresoEstudiantes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Sede id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSede() {
        return this.nombreSede;
    }

    public Sede nombreSede(String nombreSede) {
        this.setNombreSede(nombreSede);
        return this;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getDireccionSede() {
        return this.direccionSede;
    }

    public Sede direccionSede(String direccionSede) {
        this.setDireccionSede(direccionSede);
        return this;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }

    public String getRegional() {
        return this.regional;
    }

    public Sede regional(String regional) {
        this.setRegional(regional);
        return this;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public Set<IngresoEstudiante> getIngresoEstudiantes() {
        return this.ingresoEstudiantes;
    }

    public void setIngresoEstudiantes(Set<IngresoEstudiante> ingresoEstudiantes) {
        if (this.ingresoEstudiantes != null) {
            this.ingresoEstudiantes.forEach(i -> i.setSede(null));
        }
        if (ingresoEstudiantes != null) {
            ingresoEstudiantes.forEach(i -> i.setSede(this));
        }
        this.ingresoEstudiantes = ingresoEstudiantes;
    }

    public Sede ingresoEstudiantes(Set<IngresoEstudiante> ingresoEstudiantes) {
        this.setIngresoEstudiantes(ingresoEstudiantes);
        return this;
    }

    public Sede addIngresoEstudiante(IngresoEstudiante ingresoEstudiante) {
        this.ingresoEstudiantes.add(ingresoEstudiante);
        ingresoEstudiante.setSede(this);
        return this;
    }

    public Sede removeIngresoEstudiante(IngresoEstudiante ingresoEstudiante) {
        this.ingresoEstudiantes.remove(ingresoEstudiante);
        ingresoEstudiante.setSede(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sede)) {
            return false;
        }
        return id != null && id.equals(((Sede) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sede{" +
            "id=" + getId() +
            ", nombreSede='" + getNombreSede() + "'" +
            ", direccionSede='" + getDireccionSede() + "'" +
            ", regional='" + getRegional() + "'" +
            "}";
    }
}
