/*
* generated by Xtext
*/
package no.hal.jex.jextest;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class JexTestStandaloneSetup extends JexTestStandaloneSetupGenerated{

	public static void doSetup() {
		new JexTestStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

