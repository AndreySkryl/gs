package database.todoList.dao.impl;

import database.todoList.dao.UserAndListOfTasksDAO;
import database.todoList.mappers.UserAndListOfTasksRowMapper;
import database.todoList.model.UserAndListOfTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserAndListOfTasksImpl implements UserAndListOfTasksDAO {
    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(UserAndListOfTasks userAndListOfTasks) {
        String sql = "INSERT INTO USER__LIST_OF_TASKS (USER_GUID, LIST_OF_TASKS_GUID) VALUES (?, ?)";
        jdbcTemplate.update(sql, userAndListOfTasks.getUserGuid(), userAndListOfTasks.getListOfTasksGuid());
    }

    @Override
    public void insertBatch(Collection<UserAndListOfTasks> listOfUserAndListOfTasks) {
        for (UserAndListOfTasks userAndListOfTasks : listOfUserAndListOfTasks)
			insert(userAndListOfTasks);
    }

    @Override
    public void insertBatchSQL(String sql) {
        jdbcTemplate.batchUpdate(sql);
    }

    @Override
    public Collection<UserAndListOfTasks> findUserAndListOfTasksByListOfTasksGuid(String listOfTasksGuid) {
        String sql = "SELECT * FROM USER__LIST_OF_TASKS WHERE LIST_OF_TASKS_GUID = ?";
		return jdbcTemplate.query(sql, new UserAndListOfTasksRowMapper(), listOfTasksGuid);
    }

    @Override
    public Collection<UserAndListOfTasks> findAll() {
        String sql = "SELECT * FROM USER__LIST_OF_TASKS";
		return jdbcTemplate.query(sql, new UserAndListOfTasksRowMapper());
    }

    @Override
    public int findTotalUserAndListOfTasks() {
        String sql = "SELECT COUNT(*) FROM USER__LIST_OF_TASKS";
        Number number = jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
        return (number != null ? number.intValue() : 0);
    }
}