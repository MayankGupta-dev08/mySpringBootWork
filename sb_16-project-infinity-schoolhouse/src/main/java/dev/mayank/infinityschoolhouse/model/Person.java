package dev.mayank.infinityschoolhouse.model;

import dev.mayank.infinityschoolhouse.annotations.FieldValueMatch;
import dev.mayank.infinityschoolhouse.annotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "person")
@FieldValueMatch.List({
        @FieldValueMatch(
                firstField = "pwd",
                secondField = "confirmPwd",
                message = "Passwords do not match!"
        ),
        @FieldValueMatch(
                firstField = "email",
                secondField = "confirmEmail",
                message = "Email addresses do not match!"
        )
})
public class Person extends BaseEntity {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name = "name")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Column(name = "mobile_number")
    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Column(name = "email")
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Transient
    @NotBlank(message = "Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address")
    private String confirmEmail;

    @PasswordValidator
    @Column(name = "pwd")
    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String pwd;

    @Transient
    @NotBlank(message = "Confirm Password must not be blank")
    @Size(min = 5, message = "Confirm Password must be at least 5 characters long")
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;
}