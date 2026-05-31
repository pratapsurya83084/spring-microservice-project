package com.address.address.repository;

import com.address.address.model.dto.AddressDto;
import com.address.address.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findEmpAllById(Long empId );
    List<Address> findByEmpId(Long empId);
}
