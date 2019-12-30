package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.entity.PageResult;
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
            criteria.andNameLike("%name%");
        }
        PageHelper.startPage(page, size);
        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>();

        //3.封装查询结果
        PageResult<Brand> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getList());


        return pageResult;
    }
}
