package com.horarios.SGH.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.horarios.SGH.Model.users;

public interface Iusers extends JpaRepository<users, Integer> {
}