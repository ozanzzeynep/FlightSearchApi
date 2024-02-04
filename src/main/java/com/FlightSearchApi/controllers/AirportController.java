package com.FlightSearchApi.controllers;

import com.FlightSearchApi.dto.requests.CreateAirportRequest;
import com.FlightSearchApi.dto.requests.UpdateAirportRequest;
import com.FlightSearchApi.entities.Airport;
import com.FlightSearchApi.exception.results.Result;
import com.FlightSearchApi.services.abstracts.AirportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AirportController {

    private final AirportService airportService;

    @GetMapping("getAll")
    public List<Airport> getAll(){
        return airportService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result add(@RequestBody  @Valid CreateAirportRequest createAirportRequests){
        return airportService.add(createAirportRequests);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateAirportRequest updateAirportRequests){
       return airportService.update(updateAirportRequests);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable  Integer id){
        return airportService.delete(id);
    }
}
