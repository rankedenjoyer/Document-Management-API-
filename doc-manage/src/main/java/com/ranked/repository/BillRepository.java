package com.ranked.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ranked.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, String> {
}
