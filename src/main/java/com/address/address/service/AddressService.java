package com.address.address.service;

import com.address.address.model.dto.AddressDto;
import com.address.address.model.dto.AddressRequest;

import java.util.List;

public interface AddressService {

   List<AddressDto> saveAddress(AddressRequest addressRequest);

    List<AddressDto> updateAddress(Long id,AddressRequest addressRequest);

    AddressDto getSingleAddress(Long id);

    List<AddressDto> getAddress();

    void deleteAddress(Long id);


}



