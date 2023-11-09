package com.phoenix.phoenix.repository;

import com.phoenix.phoenix.dto.inventory.InventoryRowDTO;
import com.phoenix.phoenix.dto.utility.DropdownDTO;
import com.phoenix.phoenix.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {

    @Query("""
            SELECT new com.phoenix.phoenix.dto.utility.DropdownDTO(
             inv.name,inv.name)
            FROM Inventory as inv
            """)
    public List<DropdownDTO> getInventoryDropdown();

    @Query("""
            SELECT new com.phoenix.phoenix.dto.inventory.InventoryRowDTO(
             inv.name, inv.stock, inv.description)
            FROM Inventory as inv
            """)
    public Page<InventoryRowDTO> getRows(Pageable pageable);

}
