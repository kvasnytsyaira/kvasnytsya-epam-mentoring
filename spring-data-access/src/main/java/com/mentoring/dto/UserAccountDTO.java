package com.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {
    long id;

    @NotNull(message = "User id is mandatory")
    long userId;

    long wallet;
}
