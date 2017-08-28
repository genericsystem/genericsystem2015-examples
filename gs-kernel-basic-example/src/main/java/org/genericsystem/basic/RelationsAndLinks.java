package org.genericsystem.basic;

import org.genericsystem.api.core.exceptions.ReferentialIntegrityConstraintViolationException;
import org.genericsystem.api.core.exceptions.RollbackException;
import org.genericsystem.common.Generic;
import org.genericsystem.kernel.Engine;

public class RelationsAndLinks extends ExampleClass {

	public RelationsAndLinks(Engine engine) {
		super(engine);
	}

	public static void main(String[] args) {
		Engine engine = new Engine();
		// Create the type, sub-types and instances
		TypeAndSubTypes typeAndSubTypes = new TypeAndSubTypes(engine);
		typeAndSubTypes.init();
		// Create the holders and properties
		HoldersAndProperties holdersAndProperties = new HoldersAndProperties(engine);
		holdersAndProperties.init();
		// Create the new types and relations
		RelationsAndLinks relationsAndLinks = new RelationsAndLinks(engine);
		relationsAndLinks.init();
	}

	@Override
	protected void init() {
		binaryRelation();
		ternaryRelation();
		unaryRelation();
	}

	private void binaryRelation() {
		// Get the type Vehicle and its instance myVehicle from the Engine
		Generic vehicle = engine.getInstance("Vehicle");
		Generic myVehicle = vehicle.getInstance("myVehicle");

		// Create a new type (Color) and a few instances (colors)
		Generic color = engine.addInstance("Color");
		Generic red = color.addInstance("red");
		Generic yellow = color.addInstance("yellow");

		// Create a relation between Vehicle and Color
		Generic vehicleColor = vehicle.addRelation("VehicleColor", color);
		// Or use an alternate syntax
		Generic vehicleColorAlt = engine.addInstance("vehicleColorAlt", vehicle, color);

		// Create a link between myVehicle and red
		Generic myVehicleRed = myVehicle.addLink(vehicleColor, "myVehicleRed", red);
		// Or, we can use an alternate syntax
		Generic myVehicleYellow = vehicleColor.addInstance("myVehicleYellow", myVehicle, yellow);

		// Persist the changes
		engine.getCurrentCache().flush();

		// Remove myVehicle (master)
		myVehicle.remove();
		// System.out.println(myVehicle.isAlive());
		// System.out.println(myVehicleRed.isAlive());
		// System.out.println(red.isAlive());

		// Remove red (slave)
		try {
			red.remove();
		} catch (RollbackException e) {
			assert e.getCause() instanceof ReferentialIntegrityConstraintViolationException;
		}

		// Cancel the changes
		engine.getCurrentCache().clear();
	}

	private void ternaryRelation() {
		// Get the elements created previously from the Engine
		Generic vehicle = engine.getInstance("Vehicle");
		Generic color = engine.getInstance("Color");
		Generic myVehicle = vehicle.getInstance("myVehicle");
		Generic yellow = color.getInstance("yellow");
		Generic red = color.getInstance("red");

		// Create a third type (Time) and an instance (now)
		Generic time = engine.addInstance("Time");
		Generic now = time.addInstance("now");

		// Create a ternary relation between Vehicle, Color and Time
		Generic vehicleColorTime = engine.addInstance("VehColTime", vehicle, color, time);

		// Create the links between the three instances
		Generic myVehicleYellowNow = myVehicle.addLink(vehicleColorTime, "myVehYelNow", yellow, now);
		// Or, we can use an alternate syntax
		Generic myVehicleRedNow = vehicleColorTime.addInstance("myVehRedNow", myVehicle, red, now);

		// Persist the changes
		engine.getCurrentCache().flush();
	}

	private void unaryRelation() {
		// Get the elements created previously from the Engine
		Generic vehicle = engine.getInstance("Vehicle");

		// Create an unary relation largenThan
		Generic largerThan = vehicle.addRelation("largerThan", vehicle);

		// Create two new vehicles
		Generic myCar = vehicle.addInstance("myCar");
		Generic myBike = vehicle.addInstance("myBike");

		// myCar is larger than myBike
		Generic carLargerThanBike = myCar.addLink(largerThan, "carLargenThanBike", myBike);

		// myBike is larger than myCar
		Generic bikeLargerThanCar = myBike.addLink(largerThan, "bikerLargenThanCar", myCar);

		// Persist changes
		engine.getCurrentCache().flush();
	}

}
