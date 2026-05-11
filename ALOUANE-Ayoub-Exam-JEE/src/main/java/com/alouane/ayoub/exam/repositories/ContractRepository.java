package com.alouane.ayoub.exam.repositories;

import com.alouane.ayoub.exam.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
