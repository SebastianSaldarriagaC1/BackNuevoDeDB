package com.udea.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.udea.IntegrationTest;
import com.udea.domain.MateriasPensum;
import com.udea.repository.MateriasPensumRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MateriasPensumResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MateriasPensumResourceIT {

    private static final String ENTITY_API_URL = "/api/materias-pensums";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MateriasPensumRepository materiasPensumRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMateriasPensumMockMvc;

    private MateriasPensum materiasPensum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MateriasPensum createEntity(EntityManager em) {
        MateriasPensum materiasPensum = new MateriasPensum();
        return materiasPensum;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MateriasPensum createUpdatedEntity(EntityManager em) {
        MateriasPensum materiasPensum = new MateriasPensum();
        return materiasPensum;
    }

    @BeforeEach
    public void initTest() {
        materiasPensum = createEntity(em);
    }

    @Test
    @Transactional
    void createMateriasPensum() throws Exception {
        int databaseSizeBeforeCreate = materiasPensumRepository.findAll().size();
        // Create the MateriasPensum
        restMateriasPensumMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(materiasPensum))
            )
            .andExpect(status().isCreated());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeCreate + 1);
        MateriasPensum testMateriasPensum = materiasPensumList.get(materiasPensumList.size() - 1);
    }

    @Test
    @Transactional
    void createMateriasPensumWithExistingId() throws Exception {
        // Create the MateriasPensum with an existing ID
        materiasPensum.setId(1L);

        int databaseSizeBeforeCreate = materiasPensumRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMateriasPensumMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(materiasPensum))
            )
            .andExpect(status().isBadRequest());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMateriasPensums() throws Exception {
        // Initialize the database
        materiasPensumRepository.saveAndFlush(materiasPensum);

        // Get all the materiasPensumList
        restMateriasPensumMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(materiasPensum.getId().intValue())));
    }

    @Test
    @Transactional
    void getMateriasPensum() throws Exception {
        // Initialize the database
        materiasPensumRepository.saveAndFlush(materiasPensum);

        // Get the materiasPensum
        restMateriasPensumMockMvc
            .perform(get(ENTITY_API_URL_ID, materiasPensum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(materiasPensum.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingMateriasPensum() throws Exception {
        // Get the materiasPensum
        restMateriasPensumMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMateriasPensum() throws Exception {
        // Initialize the database
        materiasPensumRepository.saveAndFlush(materiasPensum);

        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();

        // Update the materiasPensum
        MateriasPensum updatedMateriasPensum = materiasPensumRepository.findById(materiasPensum.getId()).get();
        // Disconnect from session so that the updates on updatedMateriasPensum are not directly saved in db
        em.detach(updatedMateriasPensum);

        restMateriasPensumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMateriasPensum.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMateriasPensum))
            )
            .andExpect(status().isOk());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
        MateriasPensum testMateriasPensum = materiasPensumList.get(materiasPensumList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingMateriasPensum() throws Exception {
        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();
        materiasPensum.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMateriasPensumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, materiasPensum.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(materiasPensum))
            )
            .andExpect(status().isBadRequest());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMateriasPensum() throws Exception {
        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();
        materiasPensum.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMateriasPensumMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(materiasPensum))
            )
            .andExpect(status().isBadRequest());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMateriasPensum() throws Exception {
        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();
        materiasPensum.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMateriasPensumMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(materiasPensum)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMateriasPensumWithPatch() throws Exception {
        // Initialize the database
        materiasPensumRepository.saveAndFlush(materiasPensum);

        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();

        // Update the materiasPensum using partial update
        MateriasPensum partialUpdatedMateriasPensum = new MateriasPensum();
        partialUpdatedMateriasPensum.setId(materiasPensum.getId());

        restMateriasPensumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMateriasPensum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMateriasPensum))
            )
            .andExpect(status().isOk());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
        MateriasPensum testMateriasPensum = materiasPensumList.get(materiasPensumList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateMateriasPensumWithPatch() throws Exception {
        // Initialize the database
        materiasPensumRepository.saveAndFlush(materiasPensum);

        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();

        // Update the materiasPensum using partial update
        MateriasPensum partialUpdatedMateriasPensum = new MateriasPensum();
        partialUpdatedMateriasPensum.setId(materiasPensum.getId());

        restMateriasPensumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMateriasPensum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMateriasPensum))
            )
            .andExpect(status().isOk());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
        MateriasPensum testMateriasPensum = materiasPensumList.get(materiasPensumList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingMateriasPensum() throws Exception {
        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();
        materiasPensum.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMateriasPensumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, materiasPensum.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(materiasPensum))
            )
            .andExpect(status().isBadRequest());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMateriasPensum() throws Exception {
        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();
        materiasPensum.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMateriasPensumMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(materiasPensum))
            )
            .andExpect(status().isBadRequest());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMateriasPensum() throws Exception {
        int databaseSizeBeforeUpdate = materiasPensumRepository.findAll().size();
        materiasPensum.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMateriasPensumMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(materiasPensum))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MateriasPensum in the database
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMateriasPensum() throws Exception {
        // Initialize the database
        materiasPensumRepository.saveAndFlush(materiasPensum);

        int databaseSizeBeforeDelete = materiasPensumRepository.findAll().size();

        // Delete the materiasPensum
        restMateriasPensumMockMvc
            .perform(delete(ENTITY_API_URL_ID, materiasPensum.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MateriasPensum> materiasPensumList = materiasPensumRepository.findAll();
        assertThat(materiasPensumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
