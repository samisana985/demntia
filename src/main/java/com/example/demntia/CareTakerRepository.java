package com.example.demntia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareTakerRepository extends JpaRepository<CareTakerEntity, Long> {
}
