package com.springboot.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SeniorSearchDto implements Serializable {

    @NotNull(message = "工号不能为空")
    private Integer workID;

    @TableId(value = "personnelID", type = IdType.AUTO)
    private Integer personnelID;

    @NotBlank(message = "姓名不能为空")
    @Size(min = 2,max = 8,message = "姓名的长度在2~8个字符之间")
    private String personnelName;

    @Past(message="日期错误，只能为过去的日期")
    private LocalDate birthday;

    @NotNull(message = "身份证号不能为空")
    @TableField("ID")
    private Integer id;

    @Email(message = "邮箱格式不正确")
    @TableField("E_mail")
    private String eMail;

    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误 需9位数字")
    @NotNull(message = "手机号不能为空")
    private String phone;

    private String address;

}
