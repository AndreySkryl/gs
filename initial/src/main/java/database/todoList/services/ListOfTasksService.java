package database.todoList.services;

import database.todoList.exceptions.UserIsNotOwnerOfListOfTasksException;
import database.todoList.model.ListOfTasks;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ListOfTasksService {
	void insertListOfTasks(ListOfTasks listOfTasks);
	void insertListsOfTasks(Collection<ListOfTasks> listOfTasksCollection);

	ListOfTasks findListOfTasksByGuid(String guidOfListOfTasks);
	Collection<ListOfTasks> findAllListOfTasksOfUser(String guidOfUser);

	void updateListOfTasks(String guidOfUser, ListOfTasks listOfTasks) throws UserIsNotOwnerOfListOfTasksException;

	void deleteListOfTasks(String guidOfListOfTasks, String guidOfUser) throws UserIsNotOwnerOfListOfTasksException;
}
