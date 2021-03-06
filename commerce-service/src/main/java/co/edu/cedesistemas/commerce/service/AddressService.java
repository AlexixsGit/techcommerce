package co.edu.cedesistemas.commerce.service;

import co.edu.cedesistemas.commerce.model.Address;
import co.edu.cedesistemas.commerce.repository.AddressRepository;
import co.edu.cedesistemas.common.SpringProfile;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Profile("!" + SpringProfile.SANDBOX)
@Service
@AllArgsConstructor
public class AddressService implements IAddressService {

    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public Optional<Address> findById(String id) {
        return this.addressRepository.findById(id);
    }
}
