package com.address.address.service;

import com.address.address.model.dto.AddressDto;
import com.address.address.model.dto.AddressRequest;

import java.util.List;

public interface AddressService {

   List<AddressDto> saveAddress(AddressRequest addressRequest);

    List<AddressDto> updateAddress(Long id,AddressRequest addressRequest);

    List<AddressDto> getSingleAddress(Long empId);

    List<AddressDto> getAddress();

    void deleteAddress(Long id);


}



