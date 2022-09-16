package com.springboot.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class SalaryDto implements Serializable {

    private Integer workID;

    private String personnelName;

    private String department;

    private String job;

    private String bookName;

    @TableField("basicSalary")
    @NotNull(message = "基础工资不能为空")
    @DecimalMin(value = "0",message = "基础工资不能为负数")
    private BigDecimal basicSalary;

    @TableField("travelAllowance")
    @NotNull(message = "交通补贴不能为空")
    @DecimalMin(value = "0",message = "交通补贴不能为负数")
    private BigDecimal travelAllowance;

    @TableField("lunchAllowance")
    @NotNull(message = "午餐补贴不能为空")
    @DecimalMin(value = "0",message = "午餐补贴不能为负数")
    private BigDecimal lunchAllowance;

    @TableField("bonus")
    @NotNull(message = "奖金不能为空")
    @DecimalMin(value = "0",message = "奖金不能为负数")
    private BigDecimal bonus;

    @TableField("status")
    private Integer status;

    @TableId(value = "personnelID", type = IdType.AUTO)
    private Integer personnelID;
}
