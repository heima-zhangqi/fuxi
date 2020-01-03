package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.pojo.Brand;

import java.util.Map;

/**
 * @classname: BrandService
 * @author: pcpc
 * @createtime: 2019/12/30 17:49:58
 */
public interface BrandService {

    /**
     * 根据条件查询品牌列表带分页
     *
     * @param page      当前页
     * @param size      一页的记录数
     * @param searchMap 分页条件
     * @return 列表数据
     */
    public PageResult<Brand> findPage(Integer page, Integer size, Map<String, String> searchMap);

    /**
     * 新增
     *
     * @param brand 品牌对象
     * @return
     */
    public Result add(Brand brand);

    /**
     * 根据ID查询品牌对象
     * @param id
     * @return
     */
    public Result findById(Integer id);

    /**
     * 根据ID更新产品
     * @param id id
     * @param brand 品牌
     * @return
     */
    public Result update(Integer id,Brand brand);

    /**
     * 根据ID删除商品
     * @param id
     * @return
     */
    public Result delete(Integer id);
}
