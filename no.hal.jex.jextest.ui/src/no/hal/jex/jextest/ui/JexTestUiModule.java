/*
 * generated by Xtext
 */
package no.hal.jex.jextest.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.xbase.validation.UniqueClassNameValidator;

/**
 * Use this class to register components to be used within the IDE.
 */
public class JexTestUiModule extends no.hal.jex.jextest.ui.AbstractJexTestUiModule {
	
	public JexTestUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	// contributed by org.eclipse.xtext.generator.generator.GeneratorFragment
	public Class<? extends org.eclipse.xtext.builder.IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
		return no.hal.jex.jextest.ui.builder.JexTestBuilderParticipant.class;
	}
	

	public Class<? extends UniqueClassNameValidator> bindUniqueClassNameValidator() {
		return no.hal.jex.jextest.ui.validation.ProjectAwareUniqueClassNameExceptTestClassValidator.class;
	}
}
