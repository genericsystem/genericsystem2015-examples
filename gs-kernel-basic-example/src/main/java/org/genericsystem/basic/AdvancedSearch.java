package org.genericsystem.basic;

import java.util.Arrays;

import org.genericsystem.api.core.Snapshot;
import org.genericsystem.common.Generic;
import org.genericsystem.kernel.Engine;

public class AdvancedSearch extends ExampleClass {

	public AdvancedSearch(Engine engine) {
		super(engine);
	}

	public static void main(String[] args) {
		Engine engine = new Engine();
		AdvancedSearch advancedSearch = new AdvancedSearch(engine);
		advancedSearch.init();
	}

	@Override
	protected void init() {
		// Create new types
		Generic vehicle = engine.addInstance("Vehicle");
		Generic color = engine.addInstance("Color");
		Generic car = engine.addInstance(vehicle, "Car");
		Generic bike = engine.addInstance(vehicle, "Bike");

		// Add new attributes
		Generic options = vehicle.addAttribute("Options");
		Generic wheels = vehicle.addAttribute("Wheels");
		Generic power = vehicle.addAttribute("Power").enablePropertyConstraint();

		// Add new relations
		Generic vehicleColor = vehicle.addRelation("VehicleColor", color);
		Generic betterThan = vehicle.addRelation("betterThan", vehicle);

		// Add new instances
		Generic red = color.addInstance("red");
		Generic yellow = color.addInstance("yellow");
		Generic myFirstVehicle = vehicle.addInstance("myfirstVehicle");
		Generic mySecondVehicle = vehicle.addInstance("mySecondVehicle");
		Generic myThirdVehicle = vehicle.addInstance("myThirdVehicle");
		Generic myCar = car.addInstance("myCar");
		Generic yourCar = car.addInstance("yourCar");
		Generic myBike = bike.addInstance("myBike");
		Generic yourBike = bike.addInstance("yourBike");

		// Add new links
		myCar.addLink(vehicleColor, "myCarRed", red);
		yourCar.addLink(vehicleColor, "yourCarYellow", yellow);
		myBike.addLink(vehicleColor, "myBikeRed", red);
		yourBike.addLink(vehicleColor, "yourBikeRed", red);
		myCar.addLink(betterThan, "my car is better than yours", yourCar);
		myCar.addLink(betterThan, "my car is better than my bike", myBike);

		// Add new holders
		myFirstVehicle.addHolder(options, "music player");
		myFirstVehicle.addHolder(options, "ABS");
		mySecondVehicle.addHolder(options, "air conditioning");
		myThirdVehicle.addHolder(options, "air conditioning");
		myCar.addHolder(power, 136);
		yourCar.addHolder(power, 110);

		// Persist changes
		engine.getCurrentCache().flush();

		searchTypes();
		searchInstances();
		searchSubInstances();
		searchAttributes();
		searchRelations();
		searchHolders();
		searchLinks();
	}

	private void searchTypes() {
		// Get all the types from the Engine
		Snapshot<Generic> types = engine.getInstances();

		// types should at least contain Vehicle, Color
		Generic vehicle = engine.getInstance("Vehicle");
		Generic color = engine.getInstance("Color");
		Generic car = engine.getInstance(vehicle, "Car");
		Generic bike = engine.getInstance(vehicle, "Bike");
		assert types.size() >= 4;
		assert types.containsAll(Arrays.asList(vehicle, color, car, bike));
	}

	private void searchInstances() {
		// Get the type Vehicle from the Engine
		Generic vehicle = engine.getInstance("Vehicle");

		// Get all Vehicle instances
		Snapshot<Generic> vehicles = vehicle.getInstances();

		// We should have at least 3 instances of Vehicle
		Generic myFirstVehicle = vehicle.getInstance("myfirstVehicle");
		Generic mySecondVehicle = vehicle.getInstance("mySecondVehicle");
		Generic myThirdVehicle = vehicle.getInstance("myThirdVehicle");
		assert vehicles.size() >= 3;
		assert vehicles.containsAll(Arrays.asList(myFirstVehicle, mySecondVehicle, myThirdVehicle));
	}

	private void searchSubInstances() {
		// Get the type Vehicle from the Engine
		Generic vehicle = engine.getInstance("Vehicle");
		Generic car = engine.getInstance("Car");
		Generic bike = engine.getInstance("Bike");

		// Get all Vehicle sub-instances (i.e., instances of sub-types)
		Snapshot<Generic> subinstances = vehicle.getSubInstances();

		// We should have at least 4 sub-instances
		Generic myCar = car.getInstance("myCar");
		Generic yourCar = car.getInstance("yourCar");
		Generic myBike = bike.getInstance("myBike");
		Generic yourBike = bike.getInstance("yourBike");
		assert subinstances.size() >= 4;
		assert subinstances.containsAll(Arrays.asList(myCar, yourCar, myBike, yourBike));
	}

	private void searchAttributes() {
		// Get the type Vehicle from the Engine
		Generic vehicle = engine.getInstance("Vehicle");

		// Get all Vehicle attributes
		Snapshot<Generic> attributes = vehicle.getAttributes();

		// Vehicle contains at least Options, Wheels and Power attributes
		Generic options = vehicle.getAttribute("Options");
		Generic wheels = vehicle.getAttribute("Wheels");
		Generic power = vehicle.getAttribute("Power");
		assert attributes.size() >= 3;
		assert attributes.containsAll(Arrays.asList(options, wheels, power));
	}

	private void searchRelations() {
		// Get the type Vehicle from the Engine
		Generic vehicle = engine.getInstance("Vehicle");

		// Get all relations from Vehicle
		Snapshot<Generic> relations = vehicle.getRelations();

		// Vehicle has at least VehicleColor and betterThan relations
		Generic vehicleColor = vehicle.getRelation("VehicleColor");
		Generic betterThan = vehicle.getRelation("betterThan");
		assert relations.size() >= 2;
		assert relations.contains(Arrays.asList(vehicleColor, betterThan));
	}

	private void searchHolders() {
		// Get the required info from the Engine
		Generic vehicle = engine.getInstance("Vehicle");
		Generic myFirstVehicle = vehicle.getInstance("myfirstVehicle");
		Generic options = vehicle.getAttribute("Options");

		// Get the holders from myFisrstVehicle
		Snapshot<Generic> holders = myFirstVehicle.getHolders(options);

		// myFirstVehicle has at least the air conditioning and ABS
		Generic musicPlayer = myFirstVehicle.getHolder(options, "music player");
		Generic abs = myFirstVehicle.getHolder(options, "ABS");
		assert holders.size() >= 2;
		assert holders.containsAll(Arrays.asList(musicPlayer, abs));
	}

	private void searchLinks() {
		// Get the required info from the Engine
		Generic vehicle = engine.getInstance("Vehicle");
		Generic betterThan = vehicle.getRelation("betterThan");
		Generic myCar = engine.getInstance("Car").getInstance("myCar");

		// Get the links from the relation betterThan for myCar
		Snapshot<Generic> links = myCar.getLinks(betterThan);

		// The links should at least contain 2 links
		Generic myCarRocks = myCar.getLink(betterThan, "my car is better than yours");
		Generic iLoveMyCar = myCar.getLink(betterThan, "my car is better than my bike");
		assert links.size() >= 2;
		assert links.containsAll(Arrays.asList(myCarRocks, iLoveMyCar));
	}

}
