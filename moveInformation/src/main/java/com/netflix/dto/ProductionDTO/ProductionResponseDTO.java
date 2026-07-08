package com.netflix.dto.ProductionDTO;

import com.netflix.dto.ProductionWorkerDTO.ProductionWorkerMapeoDTO;
import com.netflix.entity.ProductionEntity;

import java.util.List;

public record ProductionResponseDTO (
        long productionId,
        long allWorkers,
        double allHectares,
        double totalCast,
        List<ProductionWorkerMapeoDTO> productionWorker
) {
    public static ProductionResponseDTO productionResponseDTO(ProductionEntity productionEntity) {
        return new ProductionResponseDTO(
                productionEntity.getProductionId(),
                productionEntity.getAllWorkers(),
                productionEntity.getAllHectares(),
                productionEntity.getTotalCast(),
                productionEntity.getProductionWorker().stream().map(
                        productionWorkerEntity -> new ProductionWorkerMapeoDTO(
                                productionWorkerEntity.getProductionWorkerId(),
                                productionWorkerEntity.getProduction()
                        )
                ).toList()
        );
    }
}
