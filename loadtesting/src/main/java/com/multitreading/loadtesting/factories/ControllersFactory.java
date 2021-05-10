package com.multitreading.loadtesting.factories;

import com.multitreading.loadtesting.controllers.Controller;
import com.multitreading.loadtesting.controllers.MenuFactoryLoadTestingController;
import com.multitreading.loadtesting.enums.ControllerTypes;
import com.multitreading.loadtesting.interfaces.ControllersFactoryBehavior;
import com.multitreading.loadtesting.services.MenuFactoryLoadTestingService;

public class ControllersFactory implements ControllersFactoryBehavior{
	
	@Override
	public Controller createController(ControllerTypes controllerType) {
		
		Controller controller = null;
		
		switch(controllerType) {
		
			case MENU_FACTORY_LOAD_TESTING_CONTROLLER:
				
				controller = new MenuFactoryLoadTestingController(menuFactoryLoadTestingService)
				break;
				
		}
		
		return controller;
		
	}

	
	
}
