package com.SpringSecurity.SpringSecurity.repository;

import com.SpringSecurity.SpringSecurity.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{

}
