package com.springboot.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author jb
 * @since 2021-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("salarybook")
public class SalaryBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bookID", type = IdType.AUTO)
    private Integer bookID;

    @NotBlank(message = "账套名称不能为空")
    @TableField("bookName")
    private String bookName;

    @NotNull(message = "基础工资不能为空")
    @TableField("basicSalary")
    @DecimalMin(value = "0",message = "基础工资不能为负数")
    private BigDecimal basicSalary;

    @NotNull(message = "交通补贴不能为空")
    @TableField("travelAllowance")
    @DecimalMin(value = "0",message = "交通补贴不能为负数")
    private BigDecimal travelAllowance;

    @NotNull(message = "午餐补贴不能为空")
    @TableField("lunchAllowance")
    @DecimalMin(value = "0",message = "午餐补贴不能为负数")
    private BigDecimal lunchAllowance;

    @NotNull(message = "奖金不能为空")
    @TableField("bonus")
    @DecimalMin(value = "0",message = "奖金不能为负数")
    private BigDecimal bonus;

    @NotNull(message = "启用日期不能为空")
    @Future(message = "开始日期错误 只能为将来的日期")
    @TableField("startTime")
    private LocalDate startTime;

    @TableField("status")
    private Integer status;


}
