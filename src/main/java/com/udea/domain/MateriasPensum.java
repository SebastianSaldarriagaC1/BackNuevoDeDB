package com.udea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MateriasPensum.
 */
@Entity
@Table(name = "materias_pensum")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MateriasPensum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = { "materiasPensums" }, allowSetters = true)
    private Materia materia;

    @ManyToOne
    @JsonIgnoreProperties(value = { "materiasPensums", "ingresoEstudiantes" }, allowSetters = true)
    private Pensum pensum;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MateriasPensum id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Materia getMateria() {
        return this.materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public MateriasPensum materia(Materia materia) {
        this.setMateria(materia);
        return this;
    }

    public Pensum getPensum() {
        return this.pensum;
    }

    public void setPensum(Pensum pensum) {
        this.pensum = pensum;
    }

    public MateriasPensum pensum(Pensum pensum) {
        this.setPensum(pensum);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MateriasPensum)) {
            return false;
        }
        return id != null && id.equals(((MateriasPensum) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MateriasPensum{" +
            "id=" + getId() +
            "}";
    }
}
