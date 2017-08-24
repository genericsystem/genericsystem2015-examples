package org.genericsystem.basic;

import org.genericsystem.common.Generic;
import org.genericsystem.kernel.Engine;

public class HoldersAndProperties extends ExampleClass {

	public HoldersAndProperties(Engine engine) {
		super(engine);
	}

	public static void main(String[] args) {
		// Create an Engine dynamically (i.e., only in memory)
		Engine engine = new Engine();
		// Create the type, sub-types and instances
		TypeAndSubTypes typeAndSubTypes = new TypeAndSubTypes(engine);
		typeAndSubTypes.init();
		// Create the holders and properties
		HoldersAndProperties holdersAndProperties = new HoldersAndProperties(engine);
		holdersAndProperties.init();
	}

	@Override
	protected void init() {
		holders(engine);
		properties(engine);
	}

	public void holders(Engine engine) {
		// Get the type Vehicle and its instance myVehicle from the Engine
		Generic vehicle = engine.getInstance("Vehicle");
		Generic myVehicle = vehicle.getInstance("myVehicle");

		// Add an attribute on our vehicle
		Generic options = vehicle.addAttribute("Options");

		// Now that our vehicle has options, let's customize it
		Generic abs = myVehicle.addHolder(options, "ABS");
		Generic musicPlayer = myVehicle.addHolder(options, "MusicPlayer");
		Generic gps = myVehicle.addHolder(options, "GPS");

		// Persist the changes
		engine.getCurrentCache().flush();
	}

	public void properties(Engine engine) {
		// Get the type Vehicle and its instances myVehicle and yourVehicle from the Engine
		Generic vehicle = engine.getInstance("Vehicle");
		Generic myVehicle = vehicle.getInstance("myVehicle");
		Generic yourVehicle = vehicle.getInstance("yourVehicle");

		// We add the property Power to our type Vehicle
		Generic power = vehicle.addAttribute("Power");
		power.enablePropertyConstraint();

		// Now we can add the power of our vehicles
		myVehicle.addHolder(power, 114);
		yourVehicle.addHolder(power, 116);

		// Persist the changes
		engine.getCurrentCache().flush();
	}

}
