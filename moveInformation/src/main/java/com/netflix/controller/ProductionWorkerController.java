package com.netflix.controller;

import com.netflix.dto.ProductionWorkerDTO.ProductionWorkerResponseDTO;
import com.netflix.service.ProductionWorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/productionWorker")
@RequiredArgsConstructor @CrossOrigin(origins = "*")
public class ProductionWorkerController {
    private final ProductionWorkerService productionWorkerService;

    @GetMapping("{productionWorkerId}")
    public ProductionWorkerResponseDTO getDataProductionWorker(@PathVariable long productionWorkerId) {
        return productionWorkerService.getDataProductionWorker(productionWorkerId);
    }
}
