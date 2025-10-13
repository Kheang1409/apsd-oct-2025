package edu.miu.cs.cs489.lesson7.citylibraryapp.controller;

import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.address.AddressResponse2;
import edu.miu.cs.cs489.lesson7.citylibraryapp.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/citylibrary/api/v1/address/list")
    public ResponseEntity<List<AddressResponse2>> listAddress() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    // ADS-compatible addresses endpoint (sorted by city) - reuses existing AddressResponse2 shape
    @GetMapping(value = "/adsweb/api/v1/addresses")
    public ResponseEntity<List<AddressResponse2>> listAddressesForAds() {
        // The existing service returns AddressResponse2 which may include publisher; for ADS we will reuse it
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

}
