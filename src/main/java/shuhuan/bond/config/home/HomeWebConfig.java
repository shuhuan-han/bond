package shuhuan.bond.config.home;
/**
 * 用来配置拦截器的配置类
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shuhuan.bond.constant.RuntimeConstant;
import shuhuan.bond.interceptor.admin.AuthorityInterceptor;
import shuhuan.bond.interceptor.admin.LoginInterceptor;
import shuhuan.bond.interceptor.home.HomeGlobalInterceptor;
@Configuration
public class HomeWebConfig implements WebMvcConfigurer {
	
//	@Autowired
//	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private HomeGlobalInterceptor homeGlobalInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(homeGlobalInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.homeGlobalExcludePathPatterns);
	}

}
