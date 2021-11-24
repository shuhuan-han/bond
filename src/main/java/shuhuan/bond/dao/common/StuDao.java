package shuhuan.bond.dao.common;
/**
 * 用户信息操作Dao类
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shuhuan.bond.entity.common.Stu;
@Repository
public interface StuDao extends JpaRepository<Stu, Long> {
	/**
	 * 根据账号查询
	 * @param sn
	 * @return
	 */
	Stu findBySn(String sn);
}
