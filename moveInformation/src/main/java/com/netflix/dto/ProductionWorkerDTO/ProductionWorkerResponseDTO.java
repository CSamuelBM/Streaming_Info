package com.netflix.dto.ProductionWorkerDTO;

public record ProductionWorkerResponseDTO(
        long productionWorkerId,
        double production,
        long productionId,
        long allHectares
) { }
