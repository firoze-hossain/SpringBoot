package com.roze.repository;

import com.roze.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(value = "select ea.id,ea.lane_1,ea.lane_2,ea.postal_code,ea.state from address ea join employee e on ea.employee_id = e.id where ea.employee_id=:employeeId;", nativeQuery = true)
    Address findAddressByEmployeeId(@Param("employeeId") int employeeId);

    Address findByEmployeeId(int employeeId);
}
