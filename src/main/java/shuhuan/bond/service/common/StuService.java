package shuhuan.bond.service.common;
/**
 * 用户信息操作service
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shuhuan.bond.dao.common.StuDao;
import shuhuan.bond.entity.common.Stu;

@Service
public class StuService {
	@Autowired
	private StuDao stuDao;
	/**
	 * 根据账号查询
	 * @param sn
	 * @return
	 */
	public Stu findBySn(String sn){
		return stuDao.findBySn(sn);
	}
	//验证通过之后
	/**
	 * 账号修改/编辑
	 * 当传入id时，为编辑；若id为空则为添加
	 * @param stu
	 * @return
	 */
	public Stu save(Stu stu){
		return stuDao.save(stu);
	}
}
