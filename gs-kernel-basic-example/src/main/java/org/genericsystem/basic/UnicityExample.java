package org.genericsystem.basic;

import org.genericsystem.api.core.exceptions.ExistsException;
import org.genericsystem.api.core.exceptions.RollbackException;
import org.genericsystem.common.Generic;
import org.genericsystem.kernel.Engine;

public class UnicityExample {
	public static void main(String[] args) {
		Engine engine = new Engine();

		// Add a new instance
		Generic myCar = engine.addInstance("Toyota Auris");

		// Let's try to add another instance of the same type, with the same name
		try {
			Generic yourCar = engine.addInstance("Toyota Auris");
		} catch (RollbackException e) {
			// An instance with the same name already exists!
			assert e.getCause() instanceof ExistsException;
		}

		// But if you use setInstance, you will get the previously created instance!
		Generic yourCar = engine.setInstance("Toyota Auris");
		assert myCar == yourCar;
	}
}
