package shuhuan.bond.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import shuhuan.bond.bean.PageBean;
import shuhuan.bond.dao.common.GoodsCategoryDao;
import shuhuan.bond.entity.common.GoodsCategory;
@Service
public class GoodsCategoryService {
	
	@Autowired
	private GoodsCategoryDao goodsCategoryDao;
	/**
	 * 物品分类添加/编辑；当id不为空时，编辑
	 * @param goodsCategory
	 * @return
	 */
	public GoodsCategory save(GoodsCategory goodsCategory){
		return goodsCategoryDao.save(goodsCategory);
	}
	/**
	 * 获取所有的一级分类
	 * @return
	 */
	public List<GoodsCategory>findTopCategorys(){
		return goodsCategoryDao.findByParentIsNull();
	}
	/**
	 * 搜索分类列表
	 * @param pageBean
	 * @param goodsCategory
	 * @return
	 */
	public PageBean<GoodsCategory> findlist(PageBean<GoodsCategory> pageBean,GoodsCategory goodsCategory){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		//不加的话，也会把int直接识别成0，数据库会有问题
		exampleMatcher=exampleMatcher.withIgnorePaths("sort");
		Example<GoodsCategory> example=Example.of(goodsCategory, exampleMatcher);
		Sort sort = Sort.by(Direction.ASC, "sort");
		PageRequest pageable=PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<GoodsCategory> findAll = goodsCategoryDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public GoodsCategory findById(Long id){
		return goodsCategoryDao.find(id);
	}
	/**
	 * 物品分类删除
	 * @param id
	 */
	public void delete(Long id){
		goodsCategoryDao.deleteById(id);
	}
	
	/**
	 * 在前端获取所有物品分类
	 * @return
	 */
	public List<GoodsCategory>findAll(){
		return goodsCategoryDao.findAll();
	}
}
