package com.multitreading.loadtesting.interfaces;

import com.multitreading.loadtesting.enums.ServiceTypes;
import com.multitreading.loadtesting.services.Service;

@FunctionalInterface
public interface ServiciesFactoryBehavior {

	Service createService(ServiceTypes serviceType);
	
}
