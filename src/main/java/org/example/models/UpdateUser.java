package org.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateUser extends CreateUser {
    private String name;
    private String job;
    private String updatedAt;
}
