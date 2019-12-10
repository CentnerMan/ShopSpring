package ru.vlsv.ShopSpring.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 17.11.2019
 * @link https://github.com/Centnerman
 */

@Data
@NoArgsConstructor
public class SystemUser {
    private String username;
    private String password;
    private String matchingPassword;
    private String firstName;
    private String lastName;
    private String email;
}
