package com.co.udea.mintic.proyecto.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPermisos extends JpaRepository<EntityPermisos,Long> {
}
