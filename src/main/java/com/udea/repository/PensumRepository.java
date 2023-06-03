package com.udea.repository;

import com.udea.domain.Pensum;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Pensum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PensumRepository extends JpaRepository<Pensum, Long> {}
