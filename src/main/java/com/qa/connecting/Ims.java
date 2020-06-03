package com.qa.connecting;

import org.apache.log4j.Logger;

import com.qa.connecting.dao.CustomerDao;
import com.qa.connecting.dao.RemoteDatabaseConnection;
import com.qa.connecting.model.Customer;
import com.qa.connecting.utils.Input;

public class Ims {

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	
	Input input = new Input();

	public void start() {
		//TRACE
		//DEBUG
		//INFO
		//WARN
		//ERROR
		//FATAL
		LOGGER.info("Database username: ");
		String user = input.getInput();
		System.out.println("Database password: ");
		String password = input.getInput();

		RemoteDatabaseConnection connection = new RemoteDatabaseConnection(user, password);

		// Below needs to be similar to Garage project
		// where different type of objects can be built and different inputs are
		// required
		// --------------------------

		System.out.println("What action would you like to do? Choose from the following:");
		for (Action action : Action.values()) {
			System.out.println(action.name());
		}
		System.out.println("---");

		Action selectedAction;
		while (true) {
			try {
				String actionInput = input.getInput();
				selectedAction = Action.valueOf(actionInput.toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("Not a valid selection. Please re-enter");
			}
		}

		System.out.println(selectedAction);

		switch (selectedAction) {
		case INSERT:
			System.out.println("customer name:");
			String name = input.getInput();

			System.out.println("customer email:");
			String email = input.getInput();

			System.out.println("customer address:");
			String address = input.getInput();

			Customer customer = new Customer(name, email, address);
			// ----------------------------

			// With the object send it to the Dao and have it do the rest
			CustomerDao customerDao = new CustomerDao(connection);
			//
			customerDao.insertCustomer(customer);

			break;
		}

		// DONT FORGET TO CLOSE OFF CONNECTIONS
		connection.closeConnection();
	}
}
