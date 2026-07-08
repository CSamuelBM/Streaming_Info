package com.netflix.service;

import com.netflix.dto.ProductionDTO.ProductionRegisterDTO;
import com.netflix.dto.ProductionDTO.ProductionResponseDTO;
import com.netflix.dto.ProductionWorkerDTO.ProductionWorkerRegisterDTO;
import com.netflix.entity.ProductionEntity;
import com.netflix.repository.ProductionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class ProductionService {
    private final ProductionRepository productionRepository;
    private final ProductionWorkerService productionWorkerService;

    public void insertDataProduction(ProductionRegisterDTO productionRegisterDTO){
        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.saveProduction(productionRegisterDTO);
        productionRepository.save(productionEntity);
    }

    @Transactional
    public ProductionResponseDTO getDataProduction(long productionId) {
        ProductionEntity production = productionCast(productionId);
        return new ProductionResponseDTO(
                production.getProductionId(),
                production.getAllWorkers(),
                production.getAllHectares(),
                production.getTotalCast(),
                ProductionResponseDTO.productionResponseDTO(production).productionWorker()
        );
    }

    private ProductionEntity productionCast(long productionId){
        java.util.concurrent.ThreadLocalRandom random = java.util.concurrent.ThreadLocalRandom.current();
        ProductionEntity production = productionRepository.findById(productionId).orElseThrow();

        long totalWorkers = production.getAllWorkers();
        double sumWeight = 0.0, totalCast = 0.0, factor, factorCast;

        List<Double> listWeight = new ArrayList<>();
        List<Double> listCast = new ArrayList<>();

        for(int i = 0; i < totalWorkers; i++) {
            double rndm = random.nextDouble(0.03, 0.06);
            listWeight.add(rndm);
            sumWeight += rndm;
        }

        factor = totalWorkers / sumWeight;
        for(int i = 0; i < totalWorkers; i++) {
            factorCast = listWeight.get(i) * factor;
            listCast.add(factorCast);
            totalCast += factorCast;
            ProductionWorkerRegisterDTO productionWorkerRegisterDTO = new ProductionWorkerRegisterDTO(factorCast, production);
            productionWorkerService.insertDataProductionWorker(productionWorkerRegisterDTO);
        }

        production.setTotalCast(totalCast);
        return production;
    }
}
