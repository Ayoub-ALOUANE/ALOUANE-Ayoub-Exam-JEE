package com.alouane.ayoub.exam.repositories;

import com.alouane.ayoub.exam.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
