package com.bandiera.getinline.repository;

import com.bandiera.getinline.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
