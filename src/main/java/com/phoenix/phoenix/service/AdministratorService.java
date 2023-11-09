package com.phoenix.phoenix.service;

import com.phoenix.phoenix.dto.administrator.AdministratorRowDTO;
import com.phoenix.phoenix.dto.administrator.UpsertAdministratorDTO;
import com.phoenix.phoenix.entity.Administrator;
import com.phoenix.phoenix.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public Page<AdministratorRowDTO> getRow(Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("username"));
        var dto = administratorRepository.getRow(pageable);
        return dto;
    }

    public void save(UpsertAdministratorDTO dto){
        var entity = new Administrator();
        entity.setUsername(dto.getUsername());
        if(dto.getPassword() == null){
            entity.setPassword("123");
        }
        else{
            entity.setPassword(dto.getPassword());
        }
        entity.setJobTitle(dto.getJobTitle());
        administratorRepository.save(entity);
    }
}
