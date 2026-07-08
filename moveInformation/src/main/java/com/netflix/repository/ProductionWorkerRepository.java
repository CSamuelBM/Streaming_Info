package com.netflix.repository;

import com.netflix.entity.ProductionWorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionWorkerRepository extends JpaRepository<ProductionWorkerEntity, Long> { }
