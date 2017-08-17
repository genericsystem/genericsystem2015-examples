package org.genericsystem.extendedcarcolor.app;

import org.genericsystem.carcolor.app.UserGuide;
import org.genericsystem.common.Generic;
import org.genericsystem.common.Root;
import org.genericsystem.extendedcarcolor.app.CarColorApp3.CarColorScript2;
import org.genericsystem.extendedcarcolor.app.CarColorApp3.GroupDiv;
import org.genericsystem.extendedcarcolor.app.CarColorApp3.GroupDiv2;
import org.genericsystem.extendedcarcolor.app.CarColorApp3.UserGuideButtonDiv;
import org.genericsystem.extendedcarcolor.model.Airbag;
import org.genericsystem.extendedcarcolor.model.Bike;
import org.genericsystem.extendedcarcolor.model.Car;
import org.genericsystem.extendedcarcolor.model.Color;
import org.genericsystem.extendedcarcolor.model.Energy;
import org.genericsystem.extendedcarcolor.model.Mileage;
import org.genericsystem.extendedcarcolor.model.MileageUnit;
import org.genericsystem.extendedcarcolor.model.Power;
import org.genericsystem.extendedcarcolor.model.Price;
import org.genericsystem.extendedcarcolor.model.SideCar;
import org.genericsystem.extendedcarcolor.model.Transmission;
import org.genericsystem.extendedcarcolor.model.UsedCar;
import org.genericsystem.extendedcarcolor.model.Vehicle;
import org.genericsystem.extendedcarcolor.model.VehicleColor;
import org.genericsystem.extendedcarcolor.model.VehicleEnergy;
import org.genericsystem.extendedcarcolor.model.VehicleEngine;
import org.genericsystem.extendedcarcolor.model.VehicleVehicleEngine;
import org.genericsystem.reactor.annotations.Children;
import org.genericsystem.reactor.annotations.CustomAnnotations;
import org.genericsystem.reactor.annotations.DependsOnModel;
import org.genericsystem.reactor.annotations.DirectSelect;
import org.genericsystem.reactor.annotations.RunScript;
import org.genericsystem.reactor.annotations.SetText;
import org.genericsystem.reactor.annotations.Style;
import org.genericsystem.reactor.annotations.Style.FlexDirectionStyle;
import org.genericsystem.reactor.appserver.ApplicationServer;
import org.genericsystem.reactor.appserver.Script;
import org.genericsystem.reactor.gscomponents.AppHeader;
import org.genericsystem.reactor.gscomponents.AppHeader.AppTitleDiv;
import org.genericsystem.reactor.gscomponents.AppHeader.Logo;
import org.genericsystem.reactor.gscomponents.Composite;
import org.genericsystem.reactor.gscomponents.DivWithTitle.TitledInstancesTable;
import org.genericsystem.reactor.gscomponents.FlexDirection;
import org.genericsystem.reactor.gscomponents.FlexDiv;
import org.genericsystem.reactor.gscomponents.HtmlTag.HtmlButton;
import org.genericsystem.reactor.gscomponents.HtmlTag.HtmlH1;
import org.genericsystem.reactor.gscomponents.InstancesTable;
import org.genericsystem.reactor.gscomponents.Modal.ModalEditor;
import org.genericsystem.reactor.gscomponents.Monitor;
import org.genericsystem.reactor.gscomponents.Responsive;
import org.genericsystem.reactor.gscomponents.RootTagImpl;

@CustomAnnotations(CCInheritStyle.class)
@RunScript(CarColorScript2.class)
@DependsOnModel({ Car.class, Power.class, Color.class, Airbag.class, Bike.class, Energy.class, Mileage.class, MileageUnit.class, Price.class, SideCar.class, Transmission.class, UsedCar.class, VehicleColor.class, Vehicle.class, VehicleEngine.class,
		VehicleEnergy.class, VehicleVehicleEngine.class })
@Style(name = "background-color", value = "#00afeb")
@Style(path = { Responsive.class, TitledInstancesTable.class, InstancesTable.class, Composite.class }, name = "flex", value = "0 1 auto")
@Children({ ModalEditor.class, AppHeader.class, Responsive.class, Monitor.class })
@Children(path = Responsive.class, value = { TitledInstancesTable.class, GroupDiv.class, GroupDiv2.class, TitledInstancesTable.class })
@Children(path = AppHeader.class, value = { Logo.class, AppTitleDiv.class, UserGuideButtonDiv.class })
@SetText(path = { AppHeader.class, AppTitleDiv.class, HtmlH1.class }, value = "Reactor Live Demo")
@DirectSelect(path = { Responsive.class, TitledInstancesTable.class }, value = { Vehicle.class, VehicleEngine.class })
public class CarColorApp3 extends RootTagImpl {
	public static void main(String[] mainArgs) {
		ApplicationServer.startSimpleGenericApp(mainArgs, CarColorApp3.class, "cars2");
	}

	public CarColorApp3() {
		addPrefixBinding(context -> getAdminModeProperty(context).setValue(true));
	}

	@Children({ TitledInstancesTable.class, TitledInstancesTable.class })
	@DirectSelect(path = TitledInstancesTable.class, value = { Bike.class, Car.class })
	@Style(path = TitledInstancesTable.class, name = "flex", value = "0 1 auto")
	@Style(name = "flex", value = "1")
	@Style(path = { TitledInstancesTable.class, InstancesTable.class }, name = "flex", value = "0 1 auto")
	@Style(path = { TitledInstancesTable.class, InstancesTable.class, Composite.class }, name = "flex", value = "0 1 auto")
	public static class GroupDiv extends FlexDiv {

	}

	@FlexDirectionStyle(FlexDirection.ROW)
	@Children({ TitledInstancesTable.class, TitledInstancesTable.class })
	@DirectSelect(path = TitledInstancesTable.class, value = { Color.class, Energy.class })
	@Style(name = "flex", value = "1")
	@Style(path = { TitledInstancesTable.class, InstancesTable.class }, name = "flex", value = "0 1 auto")
	@Style(path = { TitledInstancesTable.class, InstancesTable.class, Composite.class }, name = "flex", value = "0 1 auto")
	public static class GroupDiv2 extends FlexDiv {

	}

	@Style(name = "justify-content", value = "center")
	@Style(name = "align-items", value = "center")
	@Style(name = "flex", value = "1")
	@Children({ UserGuide.class, GuideButton.class })
	public static class UserGuideButtonDiv extends FlexDiv {
	}

	@SetText("User Guide")
	@Style(name = "flex", value = "0 1 auto")
	@CCInheritStyle("background-color")
	public static class GuideButton extends HtmlButton {
		@Override
		public void init() {
			bindAction(model -> getParent().find(UserGuide.class).getDisplayProperty(model).setValue("flex"));
		}
	}

	public static class CarColorScript2 implements Script {

		@Override
		public void run(Root engine) {
			Generic car = engine.find(Car.class);
			Generic power = engine.find(Power.class);
			Generic vehicleColor = engine.find(VehicleColor.class);
			Generic color = engine.find(Color.class);
			Generic red = color.setInstance("Red");
			Generic black = color.setInstance("Black");
			Generic green = color.setInstance("Green");
			color.setInstance("Blue");
			color.setInstance("Orange");
			color.setInstance("White");
			color.setInstance("Yellow");
			Generic audiS4 = car.setInstance("Audi S4");
			audiS4.setHolder(power, 333);
			System.out.println(engine.find(Vehicle.class).isAncestorOf(audiS4));
			audiS4.setLink(vehicleColor, "Audi S4 Green", green);
			Generic bmwM3 = car.setInstance("BMW M3");
			bmwM3.setHolder(power, 450);
			bmwM3.setLink(vehicleColor, "BMW M3 Red", red);
			Generic ferrariF40 = car.setInstance("Ferrari F40");
			ferrariF40.setHolder(power, 478);
			ferrariF40.setLink(vehicleColor, "Ferrari F40 red", red);
			Generic miniCooper = car.setInstance("Mini Cooper");
			miniCooper.setHolder(power, 175);
			miniCooper.setLink(vehicleColor, "Mini Cooper", black);
			car.setInstance("Audi A4 3.0 TDI").setHolder(power, 233);
			car.setInstance("Peugeot 106 GTI").setHolder(power, 120);
			car.setInstance("Peugeot 206 S16").setHolder(power, 136);
			// power.enableRequiredConstraint(ApiStatics.BASE_POSITION);
			engine.getCurrentCache().flush();
		}
	}
}
