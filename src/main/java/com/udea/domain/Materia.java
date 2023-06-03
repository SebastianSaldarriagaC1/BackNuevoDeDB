package com.udea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Materia.
 */
@Entity
@Table(name = "materia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_materia")
    private String nombreMateria;

    @Column(name = "creditos")
    private Long creditos;

    @OneToMany(mappedBy = "materia")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "materia", "pensum" }, allowSetters = true)
    private Set<MateriasPensum> materiasPensums = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Materia id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMateria() {
        return this.nombreMateria;
    }

    public Materia nombreMateria(String nombreMateria) {
        this.setNombreMateria(nombreMateria);
        return this;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Long getCreditos() {
        return this.creditos;
    }

    public Materia creditos(Long creditos) {
        this.setCreditos(creditos);
        return this;
    }

    public void setCreditos(Long creditos) {
        this.creditos = creditos;
    }

    public Set<MateriasPensum> getMateriasPensums() {
        return this.materiasPensums;
    }

    public void setMateriasPensums(Set<MateriasPensum> materiasPensums) {
        if (this.materiasPensums != null) {
            this.materiasPensums.forEach(i -> i.setMateria(null));
        }
        if (materiasPensums != null) {
            materiasPensums.forEach(i -> i.setMateria(this));
        }
        this.materiasPensums = materiasPensums;
    }

    public Materia materiasPensums(Set<MateriasPensum> materiasPensums) {
        this.setMateriasPensums(materiasPensums);
        return this;
    }

    public Materia addMateriasPensum(MateriasPensum materiasPensum) {
        this.materiasPensums.add(materiasPensum);
        materiasPensum.setMateria(this);
        return this;
    }

    public Materia removeMateriasPensum(MateriasPensum materiasPensum) {
        this.materiasPensums.remove(materiasPensum);
        materiasPensum.setMateria(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Materia)) {
            return false;
        }
        return id != null && id.equals(((Materia) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Materia{" +
            "id=" + getId() +
            ", nombreMateria='" + getNombreMateria() + "'" +
            ", creditos=" + getCreditos() +
            "}";
    }
}
