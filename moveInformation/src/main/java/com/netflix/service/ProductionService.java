package com.netflix.service;

import com.netflix.dto.ProductionDTO.ProductionRegisterDTO;
import com.netflix.dto.ProductionDTO.ProductionResponseDTO;
import com.netflix.dto.ProductionWorkerDTO.ProductionWorkerRegisterDTO;
import com.netflix.entity.ProductionEntity;
import com.netflix.repository.ProductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service @RequiredArgsConstructor
public class ProductionService {
    private final ProductionRepository productionRepository;
    private final ProductionWorkerService productionWorkerService;

    private List<Double> listWeight, listCast;
    private Random random = new Random();

    private double sumWeight, factor, totalCast;
    private double rndm = random.nextDouble(0.03, 0.06);

    public void insertDataProduction(ProductionRegisterDTO productionRegisterDTO){
        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.saveProduction(productionRegisterDTO);
        productionRepository.save(productionEntity);
    }

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
        ProductionEntity production = productionRepository.findById(productionId).orElseThrow();
        for(int i = 0; i < production.getAllWorkers(); i++) {
            listWeight.add(rndm);
            sumWeight += listWeight.get(i);
        }

        factor = production.getAllHectares() / sumWeight;
        for(int i = 0; i < production.getAllWorkers(); i++) {
            listCast.add(listWeight.get(i) * factor);
            totalCast += listCast.get(i);
            ProductionWorkerRegisterDTO productionWorkerRegisterDTO = new ProductionWorkerRegisterDTO(listCast.get(i), production);
            productionWorkerService.insertDataProductionWorker(productionWorkerRegisterDTO);
        }

        production.setTotalCast(totalCast);
        return production;
    }
}
