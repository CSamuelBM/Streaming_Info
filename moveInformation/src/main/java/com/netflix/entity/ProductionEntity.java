package com.netflix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity @Table(name="Production")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="production_id")
    private long productionId;
    @Column(name="date")
    private LocalDate date;
    @Column(name="all_workers")
    private long allWorkers;
    @Column(name="all_hectares")
    private long allHectares;
    @OneToMany(mappedBy="productionWorker")
    private List<ProductionWorkerEntity> productionWorker;
}
