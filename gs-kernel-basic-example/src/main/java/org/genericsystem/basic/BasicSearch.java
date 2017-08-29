package org.genericsystem.basic;

import org.genericsystem.common.Generic;
import org.genericsystem.kernel.Engine;

public class BasicSearch extends ExampleClass {

	public BasicSearch(Engine engine) {
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
		// Do some search
		BasicSearch basicSearch = new BasicSearch(engine);
		basicSearch.init();
	}

	@Override
	protected void init() {
		searchType();
		searchAttribute();
		searchRelationAndLink();
		searchInstances();
		searchHolder();
	}

	public void searchType() {
		// Find the type Vehicle
		Generic vehicle = engine.getInstance("Vehicle");

		// Find the type Color
		Generic color = engine.getInstance("Color");

		// Add a new color
		Generic green = color.addInstance("green");

		// Persist changes
		engine.getCurrentCache().flush();
	}

	public void searchAttribute() {
		// Find the type Vehicle
		Generic vehicle = engine.getInstance("Vehicle");

		// Find the attribute Options
		Generic options = vehicle.getAttribute("Options");

		// Find the property Power
		Generic power = vehicle.getAttribute("Power");

		// Create a new vehicle with a power and a few options
		Generic awesomeCar = vehicle.addInstance("awesomeCar");
		awesomeCar.addHolder(power, 600);
		awesomeCar.addHolder(options, "bat mobile");
		awesomeCar.addHolder(options, "super turbo");

		// Persist changes
		engine.getCurrentCache().flush();
	}

	public void searchRelationAndLink() {
		// Find the types
		Generic vehicle = engine.getInstance("Vehicle");
		Generic color = engine.getInstance("Color");

		// Find the relation VehicleColor from type Vehicle
		Generic vehicleColor = vehicle.getRelation("VehicleColor");
		// Or from type Color
		Generic vehicleColorAlt = color.getRelation("VehicleColor");

		// Find the instances
		Generic myVehicle = vehicle.getInstance("myVehicle");
		Generic yellow = color.getInstance("yellow");

		// Find the link myVehicleRed from myVehicle
		Generic myVehicleRed = myVehicle.getLink(vehicleColor, "myVehicleRed");
		// Find the link myVehicleYellow from yellow
		Generic myVehicleYellow = yellow.getLink(vehicleColor, "myVehicleYellow");
	}

	public void searchInstances() {
		// Find the types
		Generic vehicle = engine.getInstance("Vehicle");
		Generic color = engine.getInstance("Color");

		// Find the instances
		Generic myVehicle = vehicle.getInstance("myVehicle");
		Generic yellow = color.getInstance("yellow");
	}

	public void searchHolder() {
		// Find the type Vehicle, the attribute Options and myVehicle
		Generic vehicle = engine.getInstance("Vehicle");
		Generic options = vehicle.getAttribute("Options");
		Generic myVehicle = vehicle.getInstance("myVehicle");

		// Find the holder GPS
		Generic gps = myVehicle.getHolder(options, "GPS");
	}

}
