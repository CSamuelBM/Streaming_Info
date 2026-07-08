package com.netflix.repository;

import com.netflix.entity.ProductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionRepository extends JpaRepository<ProductionEntity, Long> { }
