package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author jb
 * @since 2021-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("personnelrewpub")
public class PersonnelRewPub implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "工号不能为空")
    @TableField("workID")
    private Integer workID;

    @NotBlank(message = "姓名不能为空")
    @TableField("personnelName")
    @Size(min = 2,max = 8,message = "姓名的长度在2~8个字符之间")
    private String personnelName;

    @Past(message="日期错误 只能为过去的时间")
    @NotNull(message = "日期不能为空")
    private LocalDate date;

    @NotBlank(message = "奖惩信息不能为空")
    @TableField("rewPub")
    private String rewPub;

    @NotBlank(message = "部门不能为空")
    private String department;

    private String remarks;


}
