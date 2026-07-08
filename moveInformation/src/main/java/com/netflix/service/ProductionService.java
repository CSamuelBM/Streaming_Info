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
import java.util.Random;

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
        Random random = new Random();
        ProductionEntity production = productionRepository.findById(productionId).orElseThrow();

        long totalWorkers = production.getAllWorkers();
        double sumWeight = 0.0, totalCast = 0.0, factor, cast;
        double rndm = random.nextDouble(0.03, 0.06);

        List<Double> listWeight = new ArrayList<>();

        for(int i = 0; i < totalWorkers; i++) {
            listWeight.add(rndm);
            sumWeight += listWeight.get(i);
        }

        factor = totalWorkers / sumWeight;
        for(int i = 0; i < totalWorkers; i++) {
            cast = listWeight.get(i) * factor;
            totalCast += cast;
            ProductionWorkerRegisterDTO productionWorkerRegisterDTO = new ProductionWorkerRegisterDTO(cast, production);
            productionWorkerService.insertDataProductionWorker(productionWorkerRegisterDTO);
        }

        production.setTotalCast(totalCast);
        return production;
    }
}
