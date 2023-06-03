package com.udea.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.udea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MateriasPensumTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MateriasPensum.class);
        MateriasPensum materiasPensum1 = new MateriasPensum();
        materiasPensum1.setId(1L);
        MateriasPensum materiasPensum2 = new MateriasPensum();
        materiasPensum2.setId(materiasPensum1.getId());
        assertThat(materiasPensum1).isEqualTo(materiasPensum2);
        materiasPensum2.setId(2L);
        assertThat(materiasPensum1).isNotEqualTo(materiasPensum2);
        materiasPensum1.setId(null);
        assertThat(materiasPensum1).isNotEqualTo(materiasPensum2);
    }
}
