package pl.betse.beontime.projectservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.betse.beontime.projectservice.mapper.RateMapper;
import pl.betse.beontime.projectservice.model.RateBody;
import pl.betse.beontime.projectservice.service.RateService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("projects/rates")
public class RateController {

    private final RateService rateService;
    private final RateMapper rateMapper;

    public RateController(RateService rateService, RateMapper rateMapper) {
        this.rateService = rateService;
        this.rateMapper = rateMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<RateBody>> getAllRates() {
        List<RateBody> rateBodyList = rateService.listOfRates()
                .stream()
                .map(rateMapper::fromBoToBody)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rateBodyList);
    }


}
