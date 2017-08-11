package daoImp;

import java.sql.Date;

import dao.BasicDao;
import dao.UserDao;
import entities.User;

public class UserDaoImp extends BasicDao<User> implements UserDao {

	@Override
	public boolean login(String user_name, String password) {
		String sql="select count(*) from USER where user_name=? and password=?";
		int count=Integer.valueOf(getForValue(sql,user_name,password).toString());
		if(count==1)
			return true;
		else
		    return false;
	}

	@Override
	public void register(String user_name, String password, int gender, int age, String tel, String mail) {
		String sql="insert into USER (user_name,password,gender,age,tel,mail,rank_id) values(?,?,?,?,?,?,?)";
		update(sql,user_name,password,gender,age,tel,mail,1);
	}

}
