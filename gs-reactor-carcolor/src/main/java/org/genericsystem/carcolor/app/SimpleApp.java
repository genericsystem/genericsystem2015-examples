package org.genericsystem.carcolor.app;

import org.genericsystem.api.core.annotations.Components;
import org.genericsystem.api.core.annotations.SystemGeneric;
import org.genericsystem.api.core.annotations.constraints.PropertyConstraint;
import org.genericsystem.carcolor.app.SimpleApp.Car;
import org.genericsystem.carcolor.app.SimpleApp.CarColor;
import org.genericsystem.carcolor.app.SimpleApp.CarColorScript;
import org.genericsystem.carcolor.app.SimpleApp.Color;
import org.genericsystem.carcolor.app.SimpleApp.Power;
import org.genericsystem.common.Generic;
import org.genericsystem.common.Root;
import org.genericsystem.reactor.annotations.Children;
import org.genericsystem.reactor.annotations.DependsOnModel;
import org.genericsystem.reactor.annotations.DirectSelect;
import org.genericsystem.reactor.annotations.RunScript;
import org.genericsystem.reactor.annotations.Style;
import org.genericsystem.reactor.annotations.Style.FlexDirectionStyle;
import org.genericsystem.reactor.appserver.ApplicationServer;
import org.genericsystem.reactor.appserver.Script;
import org.genericsystem.reactor.gscomponents.FlexDirection;
import org.genericsystem.reactor.gscomponents.InstancesTable;
import org.genericsystem.reactor.gscomponents.RootTagImpl;

@DependsOnModel({ Car.class, Power.class, Color.class, CarColor.class })
@RunScript(CarColorScript.class)
@Children({ InstancesTable.class, InstancesTable.class })
@FlexDirectionStyle(FlexDirection.ROW)
@Style(path = InstancesTable.class, pos = 0, name = "flex", value = "1")
@Style(path = InstancesTable.class, pos = 1, name = "flex", value = "3")
@DirectSelect(path = InstancesTable.class, value = { Color.class, Car.class })
public class SimpleApp extends RootTagImpl {
	public static void main(String[] mainArgs) {
		ApplicationServer.startSimpleGenericApp(mainArgs, SimpleApp.class, "/example");
	}

	public static class CarColorScript implements Script {

		@Override
		public void run(Root engine) {
			Generic car = engine.find(Car.class);
			Generic power = engine.find(Power.class);
			Generic carColor = engine.find(CarColor.class);
			Generic color = engine.find(Color.class);
			Generic red = color.setInstance("Red");
			Generic black = color.setInstance("Black");
			Generic green = color.setInstance("Green");
			Generic audiS4 = car.setInstance("Audi S4");
			audiS4.setHolder(power, 333);
			audiS4.setLink(carColor, "Audi S4 Green", green);
			Generic bmwM3 = car.setInstance("BMW M3");
			bmwM3.setHolder(power, 450);
			bmwM3.setLink(carColor, "BMW M3 Red", red);
			Generic ferrariF40 = car.setInstance("Ferrari F40");
			ferrariF40.setHolder(power, 478);
			ferrariF40.setLink(carColor, "Ferrari F40 red", red);
			Generic miniCooper = car.setInstance("Mini Cooper");
			miniCooper.setHolder(power, 175);
			miniCooper.setLink(carColor, "Mini Cooper", black);
			engine.getCurrentCache().flush();
		}
	}

	@SystemGeneric
	public static class Car {
	}

	@SystemGeneric
	public static class Color {
	}

	@SystemGeneric
	@Components({ Car.class, Color.class })
	public static class CarColor {
	}

	@SystemGeneric
	@Components(Car.class)
	@PropertyConstraint
	public static class Power {
	}
}
