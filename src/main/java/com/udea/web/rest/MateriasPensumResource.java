package com.udea.web.rest;

import com.udea.domain.MateriasPensum;
import com.udea.repository.MateriasPensumRepository;
import com.udea.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.udea.domain.MateriasPensum}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MateriasPensumResource {

    private final Logger log = LoggerFactory.getLogger(MateriasPensumResource.class);

    private static final String ENTITY_NAME = "materiasPensum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MateriasPensumRepository materiasPensumRepository;

    public MateriasPensumResource(MateriasPensumRepository materiasPensumRepository) {
        this.materiasPensumRepository = materiasPensumRepository;
    }

    /**
     * {@code POST  /materias-pensums} : Create a new materiasPensum.
     *
     * @param materiasPensum the materiasPensum to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new materiasPensum, or with status {@code 400 (Bad Request)} if the materiasPensum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/materias-pensums")
    public ResponseEntity<MateriasPensum> createMateriasPensum(@RequestBody MateriasPensum materiasPensum) throws URISyntaxException {
        log.debug("REST request to save MateriasPensum : {}", materiasPensum);
        if (materiasPensum.getId() != null) {
            throw new BadRequestAlertException("A new materiasPensum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MateriasPensum result = materiasPensumRepository.save(materiasPensum);
        return ResponseEntity
            .created(new URI("/api/materias-pensums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /materias-pensums/:id} : Updates an existing materiasPensum.
     *
     * @param id the id of the materiasPensum to save.
     * @param materiasPensum the materiasPensum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated materiasPensum,
     * or with status {@code 400 (Bad Request)} if the materiasPensum is not valid,
     * or with status {@code 500 (Internal Server Error)} if the materiasPensum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/materias-pensums/{id}")
    public ResponseEntity<MateriasPensum> updateMateriasPensum(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MateriasPensum materiasPensum
    ) throws URISyntaxException {
        log.debug("REST request to update MateriasPensum : {}, {}", id, materiasPensum);
        if (materiasPensum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, materiasPensum.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!materiasPensumRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MateriasPensum result = materiasPensumRepository.save(materiasPensum);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, materiasPensum.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /materias-pensums/:id} : Partial updates given fields of an existing materiasPensum, field will ignore if it is null
     *
     * @param id the id of the materiasPensum to save.
     * @param materiasPensum the materiasPensum to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated materiasPensum,
     * or with status {@code 400 (Bad Request)} if the materiasPensum is not valid,
     * or with status {@code 404 (Not Found)} if the materiasPensum is not found,
     * or with status {@code 500 (Internal Server Error)} if the materiasPensum couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/materias-pensums/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MateriasPensum> partialUpdateMateriasPensum(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MateriasPensum materiasPensum
    ) throws URISyntaxException {
        log.debug("REST request to partial update MateriasPensum partially : {}, {}", id, materiasPensum);
        if (materiasPensum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, materiasPensum.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!materiasPensumRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MateriasPensum> result = materiasPensumRepository
            .findById(materiasPensum.getId())
            .map(existingMateriasPensum -> {
                return existingMateriasPensum;
            })
            .map(materiasPensumRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, materiasPensum.getId().toString())
        );
    }

    /**
     * {@code GET  /materias-pensums} : get all the materiasPensums.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of materiasPensums in body.
     */
    @GetMapping("/materias-pensums")
    public List<MateriasPensum> getAllMateriasPensums() {
        log.debug("REST request to get all MateriasPensums");
        return materiasPensumRepository.findAll();
    }

    /**
     * {@code GET  /materias-pensums/:id} : get the "id" materiasPensum.
     *
     * @param id the id of the materiasPensum to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the materiasPensum, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/materias-pensums/{id}")
    public ResponseEntity<MateriasPensum> getMateriasPensum(@PathVariable Long id) {
        log.debug("REST request to get MateriasPensum : {}", id);
        Optional<MateriasPensum> materiasPensum = materiasPensumRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(materiasPensum);
    }

    /**
     * {@code DELETE  /materias-pensums/:id} : delete the "id" materiasPensum.
     *
     * @param id the id of the materiasPensum to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/materias-pensums/{id}")
    public ResponseEntity<Void> deleteMateriasPensum(@PathVariable Long id) {
        log.debug("REST request to delete MateriasPensum : {}", id);
        materiasPensumRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
