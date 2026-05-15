package com.address.address.service.Impl;

import com.address.address.model.dto.AddressDto;
import com.address.address.model.dto.AddressRequest;
import com.address.address.model.dto.AddressRequestDto;
import com.address.address.model.entity.Address;
import com.address.address.repository.AddressRepository;
import com.address.address.service.AddressService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class AddressServiceImpl implements AddressService {

    Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;

    // Constructor Injection
    public AddressServiceImpl(AddressRepository addressRepository,ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    ModelMapper modelMapper;
    @Override
    public List<AddressDto> saveAddress(AddressRequest addressRequest) {

        List<Address>  listTosave  =  this.saveOrUpdateAddress(addressRequest);

        List<Address> savedAddress = addressRepository.saveAll(listTosave);

        return savedAddress.stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .toList();
    }

    @Override
    public List<AddressDto> updateAddress(Long id, AddressRequest addressRequest) {
      //find Address by id  if found then update else notfound Exception throw

        //if empId not found then craete a new Address else found EmpId then update Address

        List<Address> addressList =  addressRepository.findEmpAllById(id);

         if (addressList.isEmpty()){
             log.info("No Address found for Employee id",addressRequest.getEmpId());
             log.info("Creating new Address for empployee id",addressRequest.getEmpId());
             // call here saveAddress service
         }

         List<Address>  listToUpdate  =  this.saveOrUpdateAddress(addressRequest);

      List<Long>  upcomingNonNullIds =  listToUpdate.stream().map(Address::getId).filter(Objects::nonNull).toList();
      List<Long> existingIds = addressList.stream().map(Address::getId).toList();

      List<Long> iDsToDelete  =  existingIds.stream().filter( ids->upcomingNonNullIds.contains(id)).toList();

      if (!iDsToDelete.isEmpty()){
      addressRepository.deleteAllById(iDsToDelete);
      }
     List<Address>   updatedAddress =  addressRepository.saveAll(listToUpdate);

      return   updatedAddress.stream().map(address -> modelMapper.map(address,AddressDto.class)).toList();

    }

    private List<Address> saveOrUpdateAddress(AddressRequest addressRequest){
        List<Address> listTosave = new ArrayList<>();

        for (AddressRequestDto addressRequestDto : addressRequest.getAddressRequestDtoList()) {

            Address address = new Address();
            address.setId(addressRequestDto.getId()!=null ? addressRequestDto.getId() : null);
            address.setStreet(addressRequestDto.getStreet());
            address.setCity(addressRequestDto.getCity());
            address.setCountry(addressRequestDto.getCountry());
            address.setPincode(addressRequestDto.getPinCode());
            address.setAddressType(addressRequestDto.getAddressType());
            address.setEmpId(addressRequest.getEmpId());

            listTosave.add(address);
        }

        return listTosave;
    }

    @Override
    public AddressDto getSingleAddress(Long id) {
      Address  address =  addressRepository.findById(id).orElseThrow(()-> new RuntimeException("Address is not found with that ID'S"));
      AddressDto  address1 = modelMapper.map(address,AddressDto.class);
      return address1;
    }

    @Override
    public List<AddressDto> getAddress() {
       List<Address> list =  addressRepository.findAll();

       // entityAddresslist converts into AddressDTo's  because we cannot expose directly entity
       List<AddressDto> addresList = list.stream()
               .map(address->
                       modelMapper.map(address,AddressDto.class))
                     .toList();
           return addresList;
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.findById(id).orElseThrow(()-> new RuntimeException("Address Not Found for Deletion."));
        addressRepository.deleteById(id);

    }

}
