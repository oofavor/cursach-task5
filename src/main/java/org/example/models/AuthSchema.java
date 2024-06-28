package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthSchema {
    private String email;
    private String password;
}
