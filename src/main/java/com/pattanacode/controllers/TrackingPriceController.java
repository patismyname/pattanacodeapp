package com.pattanacode.controllers;

import com.pattanacode.models.TrackingPrice;
import com.pattanacode.repository.TrackingPriceRepository;
import com.pattanacode.utils.DateTimeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class TrackingPriceController {
    @Autowired
    TrackingPriceRepository trackingPriceRepository;


    @RequestMapping(method= RequestMethod.GET, value="/trackingprices")
    public List<TrackingPrice> findAll() {
        return trackingPriceRepository.findAll();
    }//


    @RequestMapping(method=RequestMethod.POST, value="/trackingprice")
    public String save(@RequestBody TrackingPrice trackingPrice) {

        trackingPrice.setCreatedDate(DateTimeUtils.getSystemDate());
        trackingPrice.setUpdatedDate(DateTimeUtils.getSystemDate());
        trackingPriceRepository.save(trackingPrice);

        return "Created Success="+trackingPrice.getId();
    }

    @RequestMapping(method=RequestMethod.GET, value="/trackingprice/{id}")
    public Optional<TrackingPrice> show(@PathVariable String id) {
        return trackingPriceRepository.findById(id);
    }


}
