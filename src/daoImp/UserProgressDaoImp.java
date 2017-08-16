package daoImp;

import java.util.List;

import dao.BasicDao;
import dao.UserProgressDao;
import entities.UserProgress;

public class UserProgressDaoImp  extends BasicDao<UserProgress> implements UserProgressDao {

	@Override
	public List<UserProgress> getAll(int user_id) {
		String sql="select * from  USER_PROGRESS where user_id=?";
		return getForList(sql, user_id);
	}

}
