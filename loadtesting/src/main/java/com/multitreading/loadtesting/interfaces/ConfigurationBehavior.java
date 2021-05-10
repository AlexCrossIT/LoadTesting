package com.multitreading.loadtesting.interfaces;

import java.util.Map;

public interface ConfigurationBehavior {

	String getPackageToScan();
	
	Map<Class, Class> getInterfaceToImplementations();
	
}
