package com.entity;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user",catalog="qunxiang")
public class User  implements java.io.Serializable{
     private Integer userId;
     private String username;
     private String password;
     private Integer userAge;
     private Integer userIsTop;
     private String userGender;
     private String userPhone;
     private String userMobile;
     private String userEmail;
     private String userQq;
     private String userSchool;
     private String userEdu;
     private String userJob;
     private String userAddress;
     private String userConecter;
     private String userCompanyName;
     private String userCompanyDes;
     private String userFavorites;
//     private Timestamp userRegisterTime;
     //注意：后台的数据类型不管是timestamp还是datetime都没有关系
     private Date userRegisterTime;
     private Integer userFocusCount;
     private Integer userStatus;
     private Integer userTypeId;
     private String userDetailDes;
     private String userSelfDes;
     private String userImg1;
     private String userImg2;
     
    // Constructors

    /** default constructor */
    public User() {
    }
    
    public User(String username, String userAddress, Date userRegisterTime) {
		super();
		this.username = username;
		this.userAddress = userAddress;
		this.userRegisterTime = userRegisterTime;
	}

	/** full constructor */
    public User(String username, String password, String userPhone, String userMobile, String userEmail, String userQq, String userAddress, String userConecter, String userCompanyName, String userCompanyDes, Integer userFocusCount, Integer userStatus, Integer userTypeId, String userDetailDes, String userSelfDes, String userImg1, String userImg2) {
        this.username = username;
        this.password = password;
        this.userPhone = userPhone;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
        this.userQq = userQq;
        this.userAddress = userAddress;
        this.userConecter = userConecter;
        this.userCompanyName = userCompanyName;
        this.userCompanyDes = userCompanyDes;
        this.userFocusCount = userFocusCount;
        this.userStatus = userStatus;
        this.userTypeId = userTypeId;
        this.userDetailDes = userDetailDes;
        this.userSelfDes = userSelfDes;
        this.userImg1 = userImg1;
        this.userImg2 = userImg2;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="user_id", unique=true, nullable=false)
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Column(name="username")

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="password")

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="user_phone")

    public String getUserPhone() {
        return this.userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    @Column(name="user_mobile")

    public String getUserMobile() {
        return this.userMobile;
    }
    
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    
    @Column(name="user_email")

    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    @Column(name="user_qq")

    public String getUserQq() {
        return this.userQq;
    }
    
    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }
    
    @Column(name="user_address")

    public String getUserAddress() {
        return this.userAddress;
    }
    
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    
    @Column(name="user_conecter")

    public String getUserConecter() {
        return this.userConecter;
    }
    
    public void setUserConecter(String userConecter) {
        this.userConecter = userConecter;
    }
    
    @Column(name="user_company_name")

    public String getUserCompanyName() {
        return this.userCompanyName;
    }
    
    public void setUserCompanyName(String userCompanyName) {
        this.userCompanyName = userCompanyName;
    }
    
    @Column(name="user_company_des", length=65535)

    public String getUserCompanyDes() {
        return this.userCompanyDes;
    }
    
    public void setUserCompanyDes(String userCompanyDes) {
        this.userCompanyDes = userCompanyDes;
    }
    
    @Column(name="user_focus_count")

    public Integer getUserFocusCount() {
        return this.userFocusCount;
    }
    
    public void setUserFocusCount(Integer userFocusCount) {
        this.userFocusCount = userFocusCount;
    }
    
    @Column(name="user_status")

    public Integer getUserStatus() {
        return this.userStatus;
    }
    
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
    
    @Column(name="user_type_id")

    public Integer getUserTypeId() {
        return this.userTypeId;
    }
    
    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }
    
    @Column(name="user_detail_des", length=65535)

    public String getUserDetailDes() {
        return this.userDetailDes;
    }
    
    public void setUserDetailDes(String userDetailDes) {
        this.userDetailDes = userDetailDes;
    }
    
    @Column(name="user_self_des", length=65535)

    public String getUserSelfDes() {
        return this.userSelfDes;
    }
    
    public void setUserSelfDes(String userSelfDes) {
        this.userSelfDes = userSelfDes;
    }
    
    @Column(name="user_img1")

    public String getUserImg1() {
        return this.userImg1;
    }
    
    public void setUserImg1(String userImg1) {
        this.userImg1 = userImg1;
    }
    
    @Column(name="user_img2")

    public String getUserImg2() {
        return this.userImg2;
    }
    
    public void setUserImg2(String userImg2) {
        this.userImg2 = userImg2;
    }

    @Column(name="user_favorites", length=65535)
	public String getUserFavorites() {
		return userFavorites;
	}

	public void setUserFavorites(String userFavorites) {
		this.userFavorites = userFavorites;
	}

	/*@Column(name="user_register_time", length=19)
	public Timestamp getUserRegisterTime() {
		return userRegisterTime;
	}


	public void setUserRegisterTime(Timestamp userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}*/
	
	@Column(name="user_register_time", length=19)
	public Date getUserRegisterTime() {
		return userRegisterTime;
	}


	public void setUserRegisterTime(Date userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}


	@Column(name="user_age")
	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	@Column(name="user_gender")
	public String getUserGender() {
		return userGender;
	}


	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	@Column(name="user_is_top")
	public Integer getUserIsTop() {
		return userIsTop;
	}

	public void setUserIsTop(Integer userIsTop) {
		this.userIsTop = userIsTop;
	}

	@Column(name="user_school")
	public String getUserSchool() {
		return userSchool;
	}


	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}

	@Column(name="user_edu")
	public String getUserEdu() {
		return userEdu;
	}


	public void setUserEdu(String userEdu) {
		this.userEdu = userEdu;
	}

	@Column(name="user_job")
	public String getUserJob() {
		return userJob;
	}


	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
}