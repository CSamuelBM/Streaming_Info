package com.netflix.dto.ProductionWorkerDTO;

import com.netflix.entity.ProductionEntity;

public record ProductionWorkerRegisterDTO (
        double production,
        ProductionEntity productionEntity
) { }
