package com.Hique2605.Course.repositories;

import com.Hique2605.Course.entities.Admin;
import com.Hique2605.Course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Long> {
	
}

