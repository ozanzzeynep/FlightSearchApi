package com.FlightSearchApi.controllers;

import com.FlightSearchApi.dto.requests.CreateFlightRequest;
import com.FlightSearchApi.dto.requests.UpdateFlightRequest;
import com.FlightSearchApi.dto.responses.GetAllFlightResponse;
import com.FlightSearchApi.exception.results.Result;
import com.FlightSearchApi.services.abstracts.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/getAll")
    public List<GetAllFlightResponse> getAll(){
        return flightService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result add(@RequestBody @Valid CreateFlightRequest createFlightRequests){
         return flightService.add(createFlightRequests);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateFlightRequest updateFlightRequests){
        return flightService.update(updateFlightRequests);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return flightService.delete(id);
    }

    @GetMapping("/search")
    public List<?> searchRoundTrip(@RequestParam String originAirport
            ,@RequestParam String destinatinAirport
            ,@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate departureDate
            , @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate returnDate){
        return flightService.searchRoadTrip(originAirport,destinatinAirport,departureDate,returnDate);
    }
}
