package org.genericsystem.basic;

import org.genericsystem.common.Generic;
import org.genericsystem.kernel.Engine;

public class TypeAndSubTypes extends ExampleClass {

	public TypeAndSubTypes(Engine engine) {
		super(engine);
	}

	public static void main(String[] args) {
		// Create an Engine dynamically (i.e., only in memory)
		Engine engine = new Engine();
		TypeAndSubTypes typeAndSubTypes = new TypeAndSubTypes(engine);
		typeAndSubTypes.init();
	}

	@Override
	public void init() {
		typeCreation(engine);
		inheritance(engine);
	}

	private void typeCreation(Engine engine) {
		// Create a new type
		Generic vehicle = engine.addInstance("Vehicle");

		// Add a new vehicle (instance of Vehicle)
		Generic myVehicle = vehicle.addInstance("myVehicle");
		Generic yourVehicle = vehicle.addInstance("yourVehicle");

		// Persist the changes
		engine.getCurrentCache().flush();
	}

	private void inheritance(Engine engine) {
		// Get the type Vehicle from the Engine
		Generic vehicle = engine.getInstance("Vehicle");

		// Now, we want to create two kinds of vehicles: car and motorcycle
		Generic motorcycle = engine.addInstance(vehicle, "Motorcycle");
		Generic car = engine.addInstance(vehicle, "Car");

		// Add new cars
		Generic myCar = car.addInstance("myAudi");
		Generic yourCar = car.addInstance("yourRenault");

		// Add new motorcycles
		Generic myMoto = motorcycle.addInstance("myYamaha");
		Generic yourMoto = motorcycle.addInstance("yourBMW");

		// Persist the changes
		engine.getCurrentCache().flush();
	}
}
