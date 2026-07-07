package com.netflix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name="production_worker")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductionWorkerEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="productionWorker_id")
    private long productionWorkerId;
    @Column(name="production")
    private double production;
    @Column(name="worker_number")
    private long workerNumber;
    @ManyToOne @JoinColumn(name="productionId")
    private ProductionEntity productionOrchard;
}
