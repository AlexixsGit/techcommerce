package co.edu.cedesistemas.commerce.controller;

import co.edu.cedesistemas.commerce.model.Address;
import co.edu.cedesistemas.commerce.service.AddressService;
import co.edu.cedesistemas.common.DefaultResponseBuilder;
import co.edu.cedesistemas.common.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AddressesController {

    private AddressService addressService;

    @PostMapping(value = "/addresses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status<?>> createAddress(@RequestBody Address address) {
        try {
            Address created = this.addressService.createAddress(address);
            return DefaultResponseBuilder.defaultResponse(created, HttpStatus.CREATED);
        } catch (Exception ex) {
            return DefaultResponseBuilder.errorResponse(ex.getMessage(), ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Status<?>> getAddressById(@PathVariable String id) {
        try {
            Optional<Address> address = this.addressService.findById(id);
            if (address.isPresent()) {
                return DefaultResponseBuilder.defaultResponse(address.get(), HttpStatus.OK);
            }
            return DefaultResponseBuilder.errorResponse("Address not found", null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return DefaultResponseBuilder.errorResponse(ex.getMessage(), ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
