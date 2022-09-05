package com.pattanacode.controllers;

import com.pattanacode.models.TrackingPrice;
import com.pattanacode.repository.TrackingPriceRepository;
import com.pattanacode.utils.DateTimeUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tracking/v1")
public class TrackingPriceController {
    @Autowired
    TrackingPriceRepository trackingPriceRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(method= RequestMethod.GET, value="/trackingprices")
    public List<TrackingPrice> findAll() {
        return trackingPriceRepository.findAll();
    }//


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(method=RequestMethod.POST, value="/trackingprice")
    public String save(@RequestBody TrackingPrice trackingPrice) {

        trackingPrice.setCreatedDate(DateTimeUtils.getSystemDate());
        trackingPrice.setUpdatedDate(DateTimeUtils.getSystemDate());
        trackingPriceRepository.save(trackingPrice);

        return "Created Success="+trackingPrice.getId();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(method=RequestMethod.GET, value="/trackingprice/{id}")
    public Optional<TrackingPrice> show(@PathVariable String id) {
        return trackingPriceRepository.findById(id);
    }


}
