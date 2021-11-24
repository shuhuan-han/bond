package shuhuan.bond.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shuhuan.bond.bean.CodeMsg;
import shuhuan.bond.bean.Result;
import shuhuan.bond.entity.common.Stu;
import shuhuan.bond.service.common.GoodsCategoryService;
import shuhuan.bond.service.common.StuService;
import shuhuan.bond.util.ValidateEntityUtil;

//前台首页控制器
@Controller
@RequestMapping("/home/index")
public class indexController {
	
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private StuService stuService;
	/**
	 * 前端首页，实验一下hello world！
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model){
		return "home/index/index";
	}
	/**
	 * 用户登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model){
		return "home/index/login";
	}
	
	/**
	 * 检查账号是否存在
	 * @param sn
	 * @return
	 */
	@RequestMapping(value="/check_sn",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> checkSn(@RequestParam(name="sn",required=true)String sn){
		Stu stu = stuService.findBySn(sn);
		//默认是不存在的
		return Result.success(stu==null);
	}
	
	/**
	 * 用户注册表单提交
	 * @param stu
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> register(Stu stu){
		CodeMsg validate = ValidateEntityUtil.validate(stu);
		//如果发现不相等就报错
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//默认是不存在的_____——基本验证通过
		//需要再验证一遍，因为前端不太可靠，可以随便注释掉
		if(stuService.findBySn(stu.getSn())!=null){
			return Result.error(CodeMsg.HOME_STU_REGISTER_ERROR_SN_EXIST);
		}
		if(stuService.save(stu) == null){
			return Result.error(CodeMsg.HOME_STU_REGISTER_ERROR);
		}
		return Result.success(true);
	}
}
