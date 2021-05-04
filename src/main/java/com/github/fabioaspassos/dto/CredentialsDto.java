package com.github.fabioaspassos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialsDto {
    private String login;
    private String password;
}
