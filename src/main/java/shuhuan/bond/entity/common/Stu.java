package shuhuan.bond.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import shuhuan.bond.annotion.ValidateEntity;

/**
 * 用户实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="hsh_Stu")
@EntityListeners(AuditingEntityListener.class)
public class Stu extends BaseEntity{
	//默认的状态为可用
	public static final int STU_STATUS_ENABLE=1;
	//状态不可用
	public static final int STU_STATUS_UNABLE=0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=6,maxLength=18,errorRequiredMsg="账号不能为空!",errorMinLengthMsg="账号长度需大于6!",errorMaxLengthMsg="账号长度不能大于18!")
	@Column(name="sn",nullable=false,length=18)
	private String sn;//用户账号
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=6,maxLength=18,errorRequiredMsg="密码不能为空!",errorMinLengthMsg="密码长度需大于6!",errorMaxLengthMsg="密码长度不能大于18!")
	@Column(name="password",nullable=false,length=18,unique=true)
	private String password;//登录密码
	
	@ValidateEntity(required=false)
	@Column(name="head_pic",length=128)//因为图片路径可能会有点长
	private String headPic;//头像
	
	@ValidateEntity(required=false)
	@Column(name="nickname",length=32)
	private String nickname;//昵称
	

	@ValidateEntity(required=false)
	@Column(name="mobile",length=18)
	private String mobile;//手机号码
	
	@ValidateEntity(required=false)
	@Column(name="status",length=1)
	private int status = STU_STATUS_ENABLE;//	账号状态

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Stu [sn=" + sn + ", password=" + password + ", headPic="
				+ headPic + ", nickname=" + nickname + ", mobile=" + mobile
				+ ", status=" + status + "]";
	}

}
