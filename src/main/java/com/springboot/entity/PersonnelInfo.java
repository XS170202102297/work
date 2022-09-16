package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author jb
 * @since 2020-12-09
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("personnelinfo")
public class PersonnelInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
//    @TableField("personnelID")
    @TableId(value = "personnelID", type = IdType.AUTO)
    private Integer personnelID;

    /**
     * 员工姓名
     */
    @TableField("personnelName")
    @Size(min = 2,max = 8,message = "姓名的长度在2~8个字符之间")
    private String personnelName;

    /**
     * 性别
     */
    @TableField("personnelSex")
    private String personnelSex;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 身份证号
     */
    @TableField("ID")
    private Integer id;

    /**
     * 婚姻状况
     */
    private String wedlock;

    /**
     * 民族
     */
    private String race;

    /**
     * 籍贯
     */
    @TableField("nativePlace")
    private String nativePlace;

    /**
     * 政治面貌
     */
    private String politic;

    /**
     * 电子邮箱
     */
    @TableField("E_mail")
    private String eMail;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 部门
     */
    private String department;

    /**
     * 职位
     */
    private String job;

    /**
     * 职称
     */
    private String duty;

    /**
     * 聘用形式
     */
    @TableField("engageForm")
    private String engageForm;

    /**
     * 最高学历
     */
    @TableField("topDegree")
    private String topDegree;

    /**
     * 所属专业
     */
    private String specialty;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 入职日期
     */
    @TableField("beginWorkDate")
    private LocalDate beginWorkDate;

    /**
     * 在职状态
     */
    @TableField("workState")
    private String workState;

    /**
     * 工号
     */
//    @TableId(value = "workID", type = IdType.AUTO)
    @TableField("workID")
    private Integer workID;

    /**
     * 合同期限
     */
    @TableField("contractTerm")
    private Integer contractTerm;

    /**
     * 转正日期
     */
    @TableField("beFormDate")
    private LocalDate beFormDate;

    /**
     * 离职日期
     */
    @TableField("notWorkState")
    private LocalDate notWorkState;

    /**
     * 合同起始日期
     */
    @TableField("beginContract")
    private LocalDate beginContract;

    /**
     * 合同终止日期
     */
    @TableField("endContract")
    private LocalDate endContract;

    /**
     * 工龄
     */
    @TableField("workAge")
    private Integer workAge;

    /**
     * 工资套账
     */
    @TableField("bookName")
    private String bookName;

    @TableField("basicSalary")
    private BigDecimal basicSalary;

    @TableField("travelAllowance")
    private BigDecimal travelAllowance;

    @TableField("lunchAllowance")
    private BigDecimal lunchAllowance;

    @TableField("bonus")
    private BigDecimal bonus;


}
