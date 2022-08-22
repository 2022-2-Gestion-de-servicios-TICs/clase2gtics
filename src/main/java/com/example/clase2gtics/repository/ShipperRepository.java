package com.example.clase2gtics.repository;

import com.example.clase2gtics.entity.Shippers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepository extends JpaRepository<Shippers,Integer> {
}
