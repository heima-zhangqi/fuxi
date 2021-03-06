package com.itheima.controller;

import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @classname: BrandController
 * @author: pcpc
 * @createtime: 2019/12/30 21:06:20
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @PostMapping("/findPage")
    public PageResult<Brand> findPage(Integer page, Integer size, @RequestBody Map<String, String> searchMap) {
        return brandService.findPage(page, size, searchMap);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Brand brand) {
        return brandService.add(brand);
    }

    @GetMapping("/findById")
    public Result findById(Integer id) {
        return brandService.findById(id);
    }

    @PutMapping("update")
    public Result edit(Integer id, @RequestBody Brand brand) {
        return brandService.update(id, brand);
    }

    @DeleteMapping("delete")
    public Result delete(Integer id) {
        return brandService.delete(id);
    }
}
