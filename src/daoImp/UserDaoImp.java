package daoImp;

import java.sql.Date;
import java.sql.SQLException;

import dao.BasicDao;
import dao.UserDao;
import entities.User;

public class UserDaoImp extends BasicDao<User> implements UserDao {

	@Override
	public int login(String user_name, String password) {
		String sql="select user_id from USER where user_name=? and password=?";
		Object user_id=getForValue(sql,user_name,password);
		return user_id==null?0:Integer.valueOf(user_id.toString());
	}

	@Override
	public void register(String user_name, String password, int gender, int age, String tel, String mail) throws Exception {
		String sql="insert into USER (user_name,password,gender,age,tel,mail,rank_id) values(?,?,?,?,?,?,?)";
		update(sql,user_name,password,gender,age,tel,mail,1);
	}

	@Override
	public void learned(int user_name, int pageId) throws Exception {
		String sql="insert into USER_PROGRESS (user_id,page_id) values(?,?)";
		update(sql,user_name,pageId);
	}

}
