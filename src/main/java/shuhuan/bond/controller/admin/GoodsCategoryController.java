package shuhuan.bond.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shuhuan.bond.bean.CodeMsg;
import shuhuan.bond.bean.PageBean;
import shuhuan.bond.bean.Result;
import shuhuan.bond.entity.common.GoodsCategory;
import shuhuan.bond.service.common.GoodsCategoryService;
import shuhuan.bond.util.ValidateEntityUtil;

/**
 * 后台物品分类管理控制器
*/
@RequestMapping("/admin/goods_category")
@Controller
public class GoodsCategoryController {
	
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	/*
	 * 物品分类管理列表页面
	 */
	@RequestMapping(value="/list")
	public String list(GoodsCategory goodsCategory, PageBean<GoodsCategory> pageBean, Model model){
		model.addAttribute("title", "物品分类列表");
		model.addAttribute("name", goodsCategory.getName());
		pageBean.setPageSize(7);
		model.addAttribute("pageBean",goodsCategoryService.findlist(pageBean, goodsCategory) );
		
		return "admin/goods_category/list";
	}
	/**
	 * 物品分类添加页面
	 * @param goodsCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("title", "添加物品分类");
		model.addAttribute("goodsCategorys", goodsCategoryService.findTopCategorys());
		return "admin/goods_category/add";
	}
	
	/**
	 * 商品分类添加表单提交
	 * @param goodsCategory
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(GoodsCategory goodsCategory){
		
		if(goodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//用统一验证实体方法验证是否合法
				CodeMsg validate = ValidateEntityUtil.validate(goodsCategory);
				if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
					return Result.error(validate);
				}
		if (goodsCategory.getParent()!=null &&goodsCategory.getParent().getId() == null){
			goodsCategory.setParent(null);
		}
		//可以添加到数据库190110910708
		if(goodsCategoryService.save(goodsCategory)==null){
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_ADD_ERROR);
		}
		return Result.success(true);
		
	}
	/**
	 * 物品分类编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(@RequestParam(name="id",required=true)Long id, Model model){
		model.addAttribute("title", "编辑物品分类");
		model.addAttribute("goodsCategorys", goodsCategoryService.findTopCategorys());
		model.addAttribute("goodsCategory", goodsCategoryService.findById(id));
		return "admin/goods_category/edit";
	}
	
	/**
	 * 分类编辑表单提交
	 * @param goodsCategory
	 * @return
	 */
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(GoodsCategory goodsCategory){
		
		if(goodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(goodsCategory);
			if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
					return Result.error(validate);
				}
		if (goodsCategory.getParent()!=null &&goodsCategory.getParent().getId() == null){
			goodsCategory.setParent(null);
		}
		
		if(goodsCategory.getId()== null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		GoodsCategory existGoodsCategory = goodsCategoryService.findById(goodsCategory.getId());
		if(existGoodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		
		existGoodsCategory.setIcon(goodsCategory.getIcon());
		existGoodsCategory.setName(goodsCategory.getName());
		existGoodsCategory.setParent(goodsCategory.getParent());
		existGoodsCategory.setSort(goodsCategory.getSort());
		
		//可以添加到数据库190110910708
		if(goodsCategoryService.save(existGoodsCategory)==null){
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * 物品分类删除，搜索在service文件里
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		//因为设置了外键，所以要想删除父分类，前提的是把下面的子分类都删除
		try {
			goodsCategoryService.delete(id);		
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_DELETE_ERROR);
		}
		//默认删除成功
		return Result.success(true);
		
	}
}
