package shuhuan.bond.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






/**
 * 物品分类管理dao层——数据层
 */
import shuhuan.bond.entity.common.GoodsCategory;
@Repository
public interface GoodsCategoryDao extends JpaRepository<GoodsCategory, Long> {
	
	/**
	 * 获取所有一级分类
	 * @return
	 */
	List<GoodsCategory> findByParentIsNull();
	
	/**
	 * 根据id获取
	 * @return
	 */
	//因为findById已经存在了，所以在这写个sql语句
	@Query("select gc from GoodsCategory gc where id = :id")
	GoodsCategory find(@Param("id")Long id);
}
