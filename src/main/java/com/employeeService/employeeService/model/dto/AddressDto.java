package com.employeeService.employeeService.model.dto;

//import com.address.address.enums.AddressType;
import com.employeeService.employeeService.model.enums.AddressType;
import jakarta.persistence.*;

public class AddressDto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Long empId;
    private String street;
    private Long pincode;
    private String city;
    private String country;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", empId=" + empId +
                ", street='" + street + '\'' +
                ", pincode=" + pincode +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", addressType=" + addressType +
                '}';
    }

    public AddressDto(Long id) {
        this.id = id;
    }




    // Default Constructor
    public AddressDto() {
    }

    public AddressDto(Long id, AddressType addressType, String country, Long pincode, String street, Long empId, String city) {
        this.id = id;
        this.addressType = addressType;
        this.country = country;
        this.pincode = pincode;
        this.street = street;
        this.empId = empId;
        this.city = city;
    }


}
