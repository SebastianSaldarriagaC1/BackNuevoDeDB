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
 * A Estudiante.
 */
@Entity
@Table(name = "estudiante")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private Instant fechaNacimiento;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "documento")
    private String documento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "numero_contacto")
    private String numeroContacto;

    @OneToMany(mappedBy = "estudiante")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "estudiante", "carrera", "sede", "pensum", "documentoIngresoEstudiantes" }, allowSetters = true)
    private Set<IngresoEstudiante> ingresoEstudiantes = new HashSet<>();

    @OneToMany(mappedBy = "estudiante")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "carrera", "estudiante", "documentoReingresoEstudiantes" }, allowSetters = true)
    private Set<SolicitudReingreso> solicitudReingresos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Estudiante id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Estudiante nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public Estudiante apellido(String apellido) {
        this.setApellido(apellido);
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Instant getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public Estudiante fechaNacimiento(Instant fechaNacimiento) {
        this.setFechaNacimiento(fechaNacimiento);
        return this;
    }

    public void setFechaNacimiento(Instant fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return this.correo;
    }

    public Estudiante correo(String correo) {
        this.setCorreo(correo);
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Estudiante direccion(String direccion) {
        this.setDireccion(direccion);
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return this.estado;
    }

    public Estudiante estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDocumento() {
        return this.documento;
    }

    public Estudiante documento(String documento) {
        this.setDocumento(documento);
        return this;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getGenero() {
        return this.genero;
    }

    public Estudiante genero(String genero) {
        this.setGenero(genero);
        return this;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNumeroContacto() {
        return this.numeroContacto;
    }

    public Estudiante numeroContacto(String numeroContacto) {
        this.setNumeroContacto(numeroContacto);
        return this;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public Set<IngresoEstudiante> getIngresoEstudiantes() {
        return this.ingresoEstudiantes;
    }

    public void setIngresoEstudiantes(Set<IngresoEstudiante> ingresoEstudiantes) {
        if (this.ingresoEstudiantes != null) {
            this.ingresoEstudiantes.forEach(i -> i.setEstudiante(null));
        }
        if (ingresoEstudiantes != null) {
            ingresoEstudiantes.forEach(i -> i.setEstudiante(this));
        }
        this.ingresoEstudiantes = ingresoEstudiantes;
    }

    public Estudiante ingresoEstudiantes(Set<IngresoEstudiante> ingresoEstudiantes) {
        this.setIngresoEstudiantes(ingresoEstudiantes);
        return this;
    }

    public Estudiante addIngresoEstudiante(IngresoEstudiante ingresoEstudiante) {
        this.ingresoEstudiantes.add(ingresoEstudiante);
        ingresoEstudiante.setEstudiante(this);
        return this;
    }

    public Estudiante removeIngresoEstudiante(IngresoEstudiante ingresoEstudiante) {
        this.ingresoEstudiantes.remove(ingresoEstudiante);
        ingresoEstudiante.setEstudiante(null);
        return this;
    }

    public Set<SolicitudReingreso> getSolicitudReingresos() {
        return this.solicitudReingresos;
    }

    public void setSolicitudReingresos(Set<SolicitudReingreso> solicitudReingresos) {
        if (this.solicitudReingresos != null) {
            this.solicitudReingresos.forEach(i -> i.setEstudiante(null));
        }
        if (solicitudReingresos != null) {
            solicitudReingresos.forEach(i -> i.setEstudiante(this));
        }
        this.solicitudReingresos = solicitudReingresos;
    }

    public Estudiante solicitudReingresos(Set<SolicitudReingreso> solicitudReingresos) {
        this.setSolicitudReingresos(solicitudReingresos);
        return this;
    }

    public Estudiante addSolicitudReingreso(SolicitudReingreso solicitudReingreso) {
        this.solicitudReingresos.add(solicitudReingreso);
        solicitudReingreso.setEstudiante(this);
        return this;
    }

    public Estudiante removeSolicitudReingreso(SolicitudReingreso solicitudReingreso) {
        this.solicitudReingresos.remove(solicitudReingreso);
        solicitudReingreso.setEstudiante(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estudiante)) {
            return false;
        }
        return id != null && id.equals(((Estudiante) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Estudiante{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", estado='" + getEstado() + "'" +
            ", documento='" + getDocumento() + "'" +
            ", genero='" + getGenero() + "'" +
            ", numeroContacto='" + getNumeroContacto() + "'" +
            "}";
    }
}
