package com.eazybytes.eazyschool.model;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import com.eazybytes.eazyschool.annotation.PasswordValidator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Password dont match"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email address dont match"
        )
})
public class Person extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
        @GenericGenerator(name = "native",strategy = "native")
        private int personId;

        @NotBlank(message = "name must not be blank")
        @Size(min = 3,message = "name must be at least 3 characters long")
        private String name;

        @NotBlank(message = "mobile number not be blank")
        @Pattern(regexp = "(^$|[0-9]{11})",message = "mobile number must be 11 digit")
        private String mobileNumber;

        @NotBlank(message = "email must not be blank")
        @Email(message = "please provide a valid email address")
        private String email;

        @NotBlank(message = "email must not be blank")
        @Email(message = "please provide a valid confirm email address")
        @Transient
        private String confirmEmail;

        @NotBlank(message = "password must not be blank")
        @Size(min = 5,message = "password must be at least 5 characters long")
        @PasswordValidator
        private String pwd;

        @NotBlank(message = "password must not be blank")
        @Size(min = 5,message = "confirm password must be at least 5 characters long")
        @Transient
        private String confirmPwd;
}
