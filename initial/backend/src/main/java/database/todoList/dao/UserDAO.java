package database.todoList.dao;

import database.todoList.model.User;

import java.util.Collection;

public interface UserDAO {
    void insert(User user);
    void insertBatch(Collection<User> users);
    void insertBatchSQL(String sql);

    User findUserByGuid(String guid);
    Collection<User> findUsersByGuid(Collection<String> guides);
    Collection<User> findAll();
    Collection<User> f1(String guidOfUserSender, Collection<String> guidesOfColleagues);

    void update(User user);

    void delete(String guid);
	void delete(Collection<String> guides);
}