package org.genericsystem.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.genericsystem.api.core.Snapshot;
import org.genericsystem.common.Generic;
import org.genericsystem.kernel.Engine;

public class CompareSnapshotCollection extends ExampleClass {

	public CompareSnapshotCollection(Engine engine) {
		super(engine);
	}

	public static void main(String[] args) {
		Engine engine = new Engine();
		CompareSnapshotCollection compareSnapshotCollection = new CompareSnapshotCollection(engine);
		compareSnapshotCollection.init();
	}

	@Override
	protected void init() {
		// Create a type Vehicle
		Generic vehicle = engine.addInstance("Vehicle");
		Generic myFirstVehicle = vehicle.addInstance("myFirstVehicle");
		engine.getCurrentCache().flush();

		collectionEx();
		snapshotEx();
	}

	private void snapshotEx() {
		Generic vehicle = engine.getInstance("Vehicle");
		Generic myFirstVehicle = vehicle.getInstance("myFirstVehicle");

		// (2)
		Snapshot<Generic> instances = vehicle.getInstances();

		// (3)
		assert instances.size() == 1;
		assert instances.contains(myFirstVehicle);

		// (4)
		Generic mySecondVehicle = vehicle.addInstance("mySecondVehicle");

		// (5)

		// (6)
		assert instances.size() == 2;
		assert instances.containsAll(Arrays.asList(myFirstVehicle, mySecondVehicle));

		engine.getCurrentCache().clear();
	}

	private void collectionEx() {
		Generic vehicle = engine.getInstance("Vehicle");
		Generic myFirstVehicle = vehicle.getInstance("myFirstVehicle");

		// (2)
		List<Generic> instances = new ArrayList<>();
		instances.add(myFirstVehicle);

		// (3)
		assert instances.size() == 1;
		assert instances.contains(myFirstVehicle);

		// (4)
		Generic mySecondVehicle = vehicle.addInstance("mySecondVehicle");

		// (5)
		instances.add(mySecondVehicle);

		// (6)
		assert instances.size() == 2;
		assert instances.containsAll(Arrays.asList(myFirstVehicle, mySecondVehicle));

		engine.getCurrentCache().clear();
	}
}
