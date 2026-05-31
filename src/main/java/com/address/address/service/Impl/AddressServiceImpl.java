package com.address.address.service.Impl;

import com.address.address.client.EmployeeClient;
import com.address.address.exception.GlobelExceptionHandler;
import com.address.address.exception.ResourceNotFoundException;
import com.address.address.model.dto.AddressDto;
import com.address.address.model.dto.AddressRequest;
import com.address.address.model.dto.AddressRequestDto;
import com.address.address.model.dto.EmployeeDto;
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

     //bean creation process
    private final AddressRepository addressRepository;
     private final EmployeeClient employeeClient;
//     private final GlobelExceptionHandler globelExceptionHandler;
    // Constructor Injection
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper, EmployeeClient employeeClient) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.employeeClient = employeeClient;

    }

    ModelMapper modelMapper;
    @Override
    public List<AddressDto> saveAddress(AddressRequest addressRequest) {

        try {
            employeeClient.GetSingleEmp(addressRequest.getEmpId());
        } catch (Exception e) {
            throw new ResourceNotFoundException(
                    "Employee not found with ID: " + addressRequest.getEmpId()
            );
        }

        List<Address> addresses = saveOrUpdateAddress(addressRequest);

        return addressRepository.saveAll(addresses)
                .stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .toList();
    }

    @Override
    public List<AddressDto> updateAddress(Long id, AddressRequest addressRequest) {
        EmployeeDto employee   = employeeClient.GetSingleEmp(addressRequest.getEmpId());
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

    //get SingleAddressByEmpId

    public List<AddressDto> findEmpAllById(Long emp_id){

        List<Address> addressByEmpId =
                addressRepository.findEmpAllById(emp_id);

        if (addressByEmpId.isEmpty()){
            throw new ResourceNotFoundException(
                    "Employee Address Not Found"
            );
        }

        return addressByEmpId.stream()
                .map(address ->
                        modelMapper.map(address, AddressDto.class))
                .toList();
    }


    //this is not just for idWiseSingleAddress return ,
    // it is for => getSingleAddresLiST with address for microservice communication this api function
//    @Override
//    public AddressDto getSingleAddress(Long id) {
//        log.info("EmpId isReceived : "+id);
//      Address  address =  addressRepository.findById(id)
//              .orElseThrow(()-> new ResourceNotFoundException("Address is not found with that ID'S:"+id));
//      AddressDto  address1 = modelMapper.map(address,AddressDto.class);
//      return address1;
//    }
    @Override
    public List<AddressDto> getSingleAddress(Long id) {

        List<Address> addresses =
                addressRepository.findByEmpId(id);

        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Address not found with employee id : " + id);
        }

        return addresses.stream()
                .map(address ->
                        modelMapper.map(address, AddressDto.class))
                .toList();
    }

    //get All addressList
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
