package com.netflix.entity;

import com.netflix.dto.ProductionWorkerDTO.ProductionWorkerRegisterDTO;
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

    @ManyToOne @JoinColumn(name="production_id")
    private ProductionEntity productionEntity;

    public void saveProductionWorker(ProductionWorkerRegisterDTO productionWorkerRegisterDTO){
        this.setProduction(productionWorkerRegisterDTO.production());
        this.setProductionEntity(productionWorkerRegisterDTO.productionEntity());
    }
}
