package com.firstapp.demo.repositories;

import com.firstapp.demo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository  extends JpaRepository<Session, Long> {
}
//with this extension you now can find, save, delete and many more