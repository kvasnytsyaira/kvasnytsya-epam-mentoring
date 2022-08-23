package com.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    long id;

    @NotBlank(message = "Name is mandatory")
    String name;

    @Email
    String email;

}
