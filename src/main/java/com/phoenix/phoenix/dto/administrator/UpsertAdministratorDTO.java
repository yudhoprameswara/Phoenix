package com.phoenix.phoenix.dto.administrator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertAdministratorDTO {
    private String username;
    private String password;
    private String jobTitle;
}
