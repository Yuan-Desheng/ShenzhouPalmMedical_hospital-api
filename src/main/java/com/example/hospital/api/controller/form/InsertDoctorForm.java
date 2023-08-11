package com.example.hospital.api.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class InsertDoctorForm {

    @NotBlank(message = "nname不能为空")
    private String name;

    @NotBlank(message = "pid不能为空")
    private String pid;

    @NotBlank(message = "sex不能为空")
    private String sex;

    @NotBlank(message = "birthday不能为空")
    private String birthday;

    @NotBlank(message = "school不能为空")
    private String school;

    @NotBlank(message = "degree不能为空")
    private String degree;

    @NotBlank(message = "tel不能为空")
    private String tel;

    @NotBlank(message = "address不能为空")
    private String address;

    @NotBlank(message = "email不能为空")
    private String email;

    @NotBlank(message = "job不能为空")
    private String job;

    @NotBlank(message = "remark不能为空")
    private String remark;

    @NotBlank(message = "description不能为空")
    private String description;

    @NotBlank(message = "hiredate不能为空")
    private String hiredate;

    private String[] tag;

    @NotNull(message = "recommended不能为空")
    private Boolean recommended;

    @NotNull(message = "status不能为空")
    private Byte status;

    @NotNull(message = "subId不能为空")
    private Integer subId;

}
