package shuhuan.bond.interceptor.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import shuhuan.bond.config.SiteConfig;
import shuhuan.bond.service.common.GoodsCategoryService;
import shuhuan.bond.util.StringUtil;

/**
 * 前台全局登录拦截器
 * @author Administrator
 *
 */
@Component
public class HomeGlobalInterceptor implements HandlerInterceptor{

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private SiteConfig siteConfig;
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		if(!StringUtil.isAjax(request)){
			//若不是ajax请求，则将菜单信息放入页面模板变量
			request.setAttribute("goodsCategorys", goodsCategoryService.findAll());
			request.setAttribute("siteName", siteConfig.getSiteName());
		}
		return true;
	}
}
