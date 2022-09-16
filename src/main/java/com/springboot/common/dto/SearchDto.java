package com.springboot.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


import javax.annotation.Nullable;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
public class SearchDto implements Serializable {

    @NotNull(message = "工号不能为空")
    @TableField("workID")
    private Integer workID;

    @TableId(value = "personnelID", type = IdType.AUTO)
    private Integer personnelID;

    @NotBlank(message = "姓名不能为空")
    @Size(min = 2,max = 8,message = "姓名的长度在2~8个字符之间")
    private String personnelName;

    private String personnelSex;

    @NotBlank(message = "部门不能为空")
    private String department;

    private String job;

    private LocalDate beginWorkDate;

    @NotBlank(message = "工作状态不能为空")
    private String workState;

    @Past(message="出生日期错误")
    private LocalDate birthday;

    @TableField("ID")
    private Integer id;

    @Email(message = "邮箱格式不正确")
    @TableField("E_mail")
    private String eMail;

    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String phone;

    private String address;

    @TableField("bookName")
    @NotBlank(message = "工资套账不能为空")
    private String bookName;

}
