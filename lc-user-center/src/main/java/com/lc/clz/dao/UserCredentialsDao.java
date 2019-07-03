package com.lc.clz.dao;

import com.lc.clz.entities.user.AppUser;
import com.lc.clz.entities.user.UserCredential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserCredentialsDao {

	@Insert("insert into user_credentials(username, type, userId) values(#{username}, #{type}, #{userId})")
	int save(UserCredential userCredential);

	@Select("select * from user_credentials t where t.username = #{username}")
	UserCredential findByUsername(String username);

	@Select("select u.* from app_user u inner join user_credentials c on c.userId = u.id where c.username = #{username}")
	AppUser findUserByUsername(String username);
}
