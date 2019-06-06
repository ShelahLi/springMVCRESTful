package com.lucky.controller;

import com.lucky.dao.CourseDAO;
import com.lucky.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator.
 */
@Controller
public class CourseController {
    @Autowired
    private CourseDAO courseDAO;

    /**
     * 添加课程
     */
    //@PostMapping只接受POST请求
    @PostMapping(value = "/add")
    public String add(Course course){
        courseDAO.add(course);
        return "redirect:/getAll";
    }

    /**
     * 查询全部课程
     * @return
     */
    //@GetMapping只接受Get请求
    @GetMapping(value = "/getAll")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courseDAO.getAll());
        return modelAndView;
    }

    /**
     * 通过id查询课程
     */
    @GetMapping(value = "/getById/{id}")
    public ModelAndView getById(@PathVariable(value = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("course",courseDAO.getById(id));
        return modelAndView;
    }

    /**修改课程
     *
     */
    //@PutMapping只接受PUT请求
    //需要在前端页面将POST请求转为PUT请求
    //<!--把POST请求变为PUT请求-->
    //                <input type="hidden" name="_method" value="PUT"/>
    @PutMapping(value = "/update")
    public String update(Course course){
        courseDAO.update(course);
        return "redirect:/getAll";
    }

    /**
     * 删除课程
     */
    //@DeleteMapping只接受Delete请求
    //<form action="/delete/${course.id}" method="post">
    //                                <button class="btn btn-danger btn-sm delete_btn" type="submit">
    //                                    <input type="hidden" name="_method" value="DELETE"/>
    //                                    <span class="glyphicon glyphicon-trash">删除</span>
    //                                </button>
    //                            </form>
    @DeleteMapping(value = "/delete/{id}")
    //通过@PathVariable将请求中{id}赋值给delete的形参id
    public String delete(@PathVariable(value = "id")  int id){
        courseDAO.deleteById(id);
        return "redirect:/getAll";
    }

}
