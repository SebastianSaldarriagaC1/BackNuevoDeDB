package com.udea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Pensum.
 */
@Entity
@Table(name = "pensum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Pensum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_pensum")
    private Long numeroPensum;

    @OneToMany(mappedBy = "pensum")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "materia", "pensum" }, allowSetters = true)
    private Set<MateriasPensum> materiasPensums = new HashSet<>();

    @OneToMany(mappedBy = "pensum")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "estudiante", "carrera", "sede", "pensum", "documentoIngresoEstudiantes" }, allowSetters = true)
    private Set<IngresoEstudiante> ingresoEstudiantes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Pensum id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroPensum() {
        return this.numeroPensum;
    }

    public Pensum numeroPensum(Long numeroPensum) {
        this.setNumeroPensum(numeroPensum);
        return this;
    }

    public void setNumeroPensum(Long numeroPensum) {
        this.numeroPensum = numeroPensum;
    }

    public Set<MateriasPensum> getMateriasPensums() {
        return this.materiasPensums;
    }

    public void setMateriasPensums(Set<MateriasPensum> materiasPensums) {
        if (this.materiasPensums != null) {
            this.materiasPensums.forEach(i -> i.setPensum(null));
        }
        if (materiasPensums != null) {
            materiasPensums.forEach(i -> i.setPensum(this));
        }
        this.materiasPensums = materiasPensums;
    }

    public Pensum materiasPensums(Set<MateriasPensum> materiasPensums) {
        this.setMateriasPensums(materiasPensums);
        return this;
    }

    public Pensum addMateriasPensum(MateriasPensum materiasPensum) {
        this.materiasPensums.add(materiasPensum);
        materiasPensum.setPensum(this);
        return this;
    }

    public Pensum removeMateriasPensum(MateriasPensum materiasPensum) {
        this.materiasPensums.remove(materiasPensum);
        materiasPensum.setPensum(null);
        return this;
    }

    public Set<IngresoEstudiante> getIngresoEstudiantes() {
        return this.ingresoEstudiantes;
    }

    public void setIngresoEstudiantes(Set<IngresoEstudiante> ingresoEstudiantes) {
        if (this.ingresoEstudiantes != null) {
            this.ingresoEstudiantes.forEach(i -> i.setPensum(null));
        }
        if (ingresoEstudiantes != null) {
            ingresoEstudiantes.forEach(i -> i.setPensum(this));
        }
        this.ingresoEstudiantes = ingresoEstudiantes;
    }

    public Pensum ingresoEstudiantes(Set<IngresoEstudiante> ingresoEstudiantes) {
        this.setIngresoEstudiantes(ingresoEstudiantes);
        return this;
    }

    public Pensum addIngresoEstudiante(IngresoEstudiante ingresoEstudiante) {
        this.ingresoEstudiantes.add(ingresoEstudiante);
        ingresoEstudiante.setPensum(this);
        return this;
    }

    public Pensum removeIngresoEstudiante(IngresoEstudiante ingresoEstudiante) {
        this.ingresoEstudiantes.remove(ingresoEstudiante);
        ingresoEstudiante.setPensum(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pensum)) {
            return false;
        }
        return id != null && id.equals(((Pensum) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pensum{" +
            "id=" + getId() +
            ", numeroPensum=" + getNumeroPensum() +
            "}";
    }
}
