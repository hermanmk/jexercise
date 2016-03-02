/*
 * generated by Xtext
 */
package no.hal.emfs.xtext;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class XemfsRuntimeModule extends no.hal.emfs.xtext.AbstractXemfsRuntimeModule {

	@Override
	public Class<? extends org.eclipse.xtext.conversion.IValueConverterService> bindIValueConverterService() {
		return no.hal.emfs.xtext.XemfsValueConverters.class;
	}

	public Class<? extends org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
		return no.hal.emfs.xtext.scoping.RootOnlyResourceDescriptionStrategy.class;
	}
	
	@Override
	public Class<? extends org.eclipse.xtext.resource.IFragmentProvider> bindIFragmentProvider() {
		return no.hal.emfs.xtext.scoping.EmfsFragmentProvider.class;
	}
}
