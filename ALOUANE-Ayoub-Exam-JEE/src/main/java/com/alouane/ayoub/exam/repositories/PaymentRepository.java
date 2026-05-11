package com.alouane.ayoub.exam.repositories;

import com.alouane.ayoub.exam.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
