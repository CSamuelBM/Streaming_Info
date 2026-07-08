package com.netflix.service;

import com.netflix.dto.ProductionWorkerDTO.ProductionWorkerRegisterDTO;
import com.netflix.dto.ProductionWorkerDTO.ProductionWorkerResponseDTO;
import com.netflix.entity.ProductionWorkerEntity;
import com.netflix.repository.ProductionWorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ProductionWorkerService {
    private final ProductionWorkerRepository productionWorkerRepository;

    public void insertDataProductionWorker(ProductionWorkerRegisterDTO productionWorkerRegisterDTO){
        ProductionWorkerEntity productionWorkerEntity = new ProductionWorkerEntity();
        productionWorkerEntity.saveProductionWorker(productionWorkerRegisterDTO);
        productionWorkerRepository.save(productionWorkerEntity);
    }

    public ProductionWorkerResponseDTO getDataProductionWorker(long productionWorkerId) {
        ProductionWorkerEntity productionWorkerEntity = productionWorkerRepository.findById(productionWorkerId).orElseThrow();
        return new ProductionWorkerResponseDTO(
                productionWorkerEntity.getProductionWorkerId(),
                productionWorkerEntity.getProduction(),
                productionWorkerEntity.getProductionEntity().getProductionId(),
                productionWorkerEntity.getProductionEntity().getAllHectares()
        );
    }
}
