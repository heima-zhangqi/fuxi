package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.BrandExample;
import com.itheima.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @classname: BrandServiceImpl
 * @author: pcpc
 * @createtime: 2019/12/30 18:01:00
 */
@Service
public class BrandServiceImpl implements BrandService {
    /**
     * @param page      当前页
     * @param size      一页的记录数
     * @param searchMap 分页条件
     * @return 列表数据
     */
    @Autowired
    BrandMapper brandMapper;

    @Override
    public PageResult<Brand> findPage(Integer page, Integer size, Map<String, String> searchMap) {
        //1.参数校验
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 5;
        }
        if (searchMap == null) {
            searchMap = new HashMap<>();
        }

        //2.业务逻辑
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        String name = searchMap.get("name");
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        //根据ID倒序查询
        example.setOrderByClause("id desc");
        //去重
        example.setDistinct(true);

        PageHelper.startPage(page, size);
        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>();


        //3.封装查询结果
        PageResult<Brand> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getList());


        return pageResult;
    }

    /**
     * 新增
     *
     * @param brand 品牌对象
     * @return
     */
    @Override
    public Result add(Brand brand) {

        //1.参数校验
        if (StringUtils.isEmpty(brand.getName())) {
            throw new RuntimeException("参数异常！");
        }
        if (StringUtils.isEmpty(brand.getId())) {
            throw new RuntimeException("参数异常！");
        }

        //小写转大写
        if (!StringUtils.isEmpty(brand.getLetter())) {
            brand.setLetter(brand.getLetter().toUpperCase());
        }

        //2.业务逻辑
        int i = brandMapper.insertSelective(brand);
        System.out.println("所影响的行数为：" + i);
        System.out.println(brand);
        //3.封装查询结果
        Result result = new Result(brand);
        return result;
    }

    /**
     * 根据ID查询品牌对象
     *
     * @param id
     * @return
     */
    @Override
    public Result findById(Integer id) {
        // 业务逻辑
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (brand == null) {
            return new Result(false, "无品牌数据", 1);
        }
        // 封装返回结果
        return new Result(brand);
    }
}
