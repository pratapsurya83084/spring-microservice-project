package com.address.address;

import com.address.address.model.dto.AddressDto;
import com.address.address.model.dto.AddressRequest;
import com.address.address.model.entity.Address;
import com.address.address.service.Impl.AddressServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Controller
@RequestMapping("/api/address")
public class AddressController {


    private final AddressServiceImpl addressServiceimpl;

    public AddressController(AddressServiceImpl addressServiceimpl) {
        this.addressServiceimpl = addressServiceimpl;
    }

    @PostMapping("/save-address")
   public ResponseEntity<List<AddressDto>> savedAddress(@RequestBody AddressRequest addressDto){
      List<AddressDto>   response = addressServiceimpl.saveAddress(addressDto);
     return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/upadter-address")
     public ResponseEntity<List<AddressDto>> UpdateAdreess(@RequestParam Long empid , @RequestBody AddressRequest addressDto){
      List<AddressDto> response  = addressServiceimpl.updateAddress(empid, addressDto);
      return new  ResponseEntity<>(response,HttpStatus.OK);
    } 

    @GetMapping("/get-singleAddress")
    public ResponseEntity<List<AddressDto>> GetSingleAddress(@RequestParam Long id){
      List<AddressDto>  singleAddress = Collections.singletonList(addressServiceimpl.getSingleAddress(id));
     return new  ResponseEntity<>(singleAddress,HttpStatus.CREATED);
    }

    @GetMapping("/get-addressList")
    public ResponseEntity<List<AddressDto>> getAllAddressList(){
      List<AddressDto> listOfAdd =    addressServiceimpl.getAddress();
    return new  ResponseEntity<>(listOfAdd,HttpStatus.CREATED);
    }

    @DeleteMapping("deletAddressByIds")
    public void deleteAddressByIdWise(@RequestParam Long id){
      addressServiceimpl.deleteAddress(id);
    }
}







