package com.phoenix.phoenix.repository;

import com.phoenix.phoenix.dto.administrator.AdministratorRowDTO;
import com.phoenix.phoenix.entity.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,String> {

    @Query("""
            SELECT new com.phoenix.phoenix.dto.administrator.AdministratorRowDTO(
                adm.username , adm.jobTitle
            )
            FROM Administrator as adm
            """)
    Page<AdministratorRowDTO> getRow(Pageable pageable);
}
