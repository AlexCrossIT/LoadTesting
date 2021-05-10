package com.multitreading.loadtesting.interfaces;

import com.multitreading.loadtesting.controllers.Controller;
import com.multitreading.loadtesting.enums.ControllerTypes;

@FunctionalInterface
public interface ControllersFactoryBehavior {

	Controller createController(ControllerTypes controllerType);
	
}
