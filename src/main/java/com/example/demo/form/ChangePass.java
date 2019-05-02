package com.example.demo.form;

import com.example.demo.annotations.EqualFields;
import com.example.demo.annotations.EqualPasswordFields;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Linh
 * @project demo
 */
@Data
@NoArgsConstructor
@EqualPasswordFields(baseField = "id", matchField = "oldPassword")
@EqualFields(baseField = "newPassword", matchField = "confirmNewPassword")
public class ChangePass {
    private Long id;
    @NotEmpty(message = "{demo.user.oldpass.empty.message}")
    private String oldPassword;
    @Size(min = 6, max = 13, message = "{demo.user.newpass.empty.message}")
    private String newPassword;
    private String confirmNewPassword;
}
