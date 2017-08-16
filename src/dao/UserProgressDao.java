package dao;

import java.util.List;

import entities.UserProgress;

public interface UserProgressDao {
	List<UserProgress> getAll(int user_id);

}
