package com.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.common.lang.Result;
import com.springboot.entity.SalaryBook;
import com.springboot.service.SalaryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jb
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/salary")
public class SalaryBookController {
    @Autowired
    SalaryBookService salaryBookService;

    //添加账套信息（增）
    @PostMapping("/add")
    public Result addSalaryBook(@Validated @RequestBody SalaryBook salaryBook) {
        SalaryBook salaryBook1 = salaryBookService.getOne(new QueryWrapper<SalaryBook>().eq("bookName", salaryBook.getBookName()));
        if (salaryBook1 == null) {

            SalaryBook salaryBook2= new SalaryBook();

            salaryBook2.setBasicSalary(salaryBook.getBasicSalary());
            salaryBook2.setTravelAllowance(salaryBook.getTravelAllowance());
            salaryBook2.setLunchAllowance(salaryBook.getLunchAllowance());
            salaryBook2.setBonus(salaryBook.getBonus());
            salaryBook2.setStartTime(salaryBook.getStartTime());
            salaryBook2.setBookName(salaryBook.getBookName());
            salaryBook2.setStatus(salaryBook.getStatus());

            salaryBookService.save(salaryBook2);
            return Result.success(salaryBook2);
        } else {
            return Result.fail(salaryBook.getBookName()+"该账套已存在");
        }
    }

    //删除账套信息（删）
    @PostMapping("/delete")
    public Result deleteSalaryBook(@RequestBody SalaryBook salaryBook){
        boolean rows = salaryBookService.remove(new QueryWrapper<SalaryBook>().eq("bookID", salaryBook.getBookID()));

        if(rows){
            return Result.success("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }

    //修改账套信息（改）
    // 1.根据id查找账套
    @GetMapping("/searchBookById/{bookID}")
    public Result findBookById(@PathVariable(name = "bookID") String bookID){

        return Result.success(salaryBookService.getById(bookID));
    }

    // 2.根据id修改账套信息
    @PostMapping("/update")
    public Result updateBookInfo(@Validated @RequestBody SalaryBook salaryBook){
        SalaryBook salaryBook2 = salaryBookService.getOne(new QueryWrapper<SalaryBook>().eq("bookID", salaryBook.getBookID()));
        if (salaryBook2 != null) {
            salaryBook2.setBookID(salaryBook.getBookID());
            salaryBook2.setBookName(salaryBook.getBookName());
            salaryBook2.setBasicSalary(salaryBook.getBasicSalary());
            salaryBook2.setTravelAllowance(salaryBook.getTravelAllowance());
            salaryBook2.setLunchAllowance(salaryBook.getLunchAllowance());
            salaryBook2.setBonus(salaryBook.getBonus());
            salaryBook2.setStartTime(salaryBook.getStartTime());

            salaryBookService.saveOrUpdate(salaryBook2);

            return Result.success(salaryBook2);
        } else {
            return Result.fail("修改失败，请重新输入信息");
        }
    }

    //修改账套状态（改）
    @PostMapping("/status")
    public Result changeBookStatus(@RequestBody SalaryBook salaryBook){
        SalaryBook salaryBook1 = salaryBookService.getOne(new QueryWrapper<SalaryBook>().eq("bookID", salaryBook.getBookID()));

        if(salaryBook1.getStatus()==1){
            salaryBook1.setStatus(0);
        }else{
            salaryBook1.setStatus(1);
        };

        salaryBookService.saveOrUpdate(salaryBook1);
        return Result.success(salaryBook1);
    }

    //查询所有账套信息
    @GetMapping("/all")
    public Result searchSalaryBook(SalaryBook salaryBook){

        return Result.success(salaryBookService.list(null));
    }






}
