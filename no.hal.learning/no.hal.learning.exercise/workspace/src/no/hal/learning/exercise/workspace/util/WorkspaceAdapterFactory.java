/**
 */
package no.hal.learning.exercise.workspace.util;

import no.hal.learning.exercise.AbstractStringEditEvent;
import no.hal.learning.exercise.Answer;
import no.hal.learning.exercise.Proposal;
import no.hal.learning.exercise.StringEditAnswer;
import no.hal.learning.exercise.StringEditTaskProposal;
import no.hal.learning.exercise.TaskAnswer;
import no.hal.learning.exercise.TaskEvent;
import no.hal.learning.exercise.TaskProposal;

import no.hal.learning.exercise.workspace.*;

import no.hal.learning.fv.EFeatureObject;
import no.hal.learning.fv.FeatureValued;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see no.hal.learning.exercise.workspace.WorkspacePackage
 * @generated
 */
public class WorkspaceAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WorkspacePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkspaceAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = WorkspacePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceSwitch<Adapter> modelSwitch =
		new WorkspaceSwitch<Adapter>() {
			@Override
			public Adapter caseSourceFileEditAnswer(SourceFileEditAnswer object) {
				return createSourceFileEditAnswerAdapter();
			}
			@Override
			public Adapter caseSourceFileEditProposal(SourceFileEditProposal object) {
				return createSourceFileEditProposalAdapter();
			}
			@Override
			public Adapter caseSourceFileEditEvent(SourceFileEditEvent object) {
				return createSourceFileEditEventAdapter();
			}
			@Override
			public Adapter caseLaunchAnswer(LaunchAnswer object) {
				return createLaunchAnswerAdapter();
			}
			@Override
			public <T extends LaunchAnswer> Adapter caseLaunchProposal(LaunchProposal<T> object) {
				return createLaunchProposalAdapter();
			}
			@Override
			public Adapter caseLaunchEvent(LaunchEvent object) {
				return createLaunchEventAdapter();
			}
			@Override
			public Adapter caseAnswer(Answer object) {
				return createAnswerAdapter();
			}
			@Override
			public Adapter caseTaskAnswer(TaskAnswer object) {
				return createTaskAnswerAdapter();
			}
			@Override
			public Adapter caseStringEditAnswer(StringEditAnswer object) {
				return createStringEditAnswerAdapter();
			}
			@Override
			public <A extends Answer> Adapter caseProposal(Proposal<A> object) {
				return createProposalAdapter();
			}
			@Override
			public <T extends TaskAnswer> Adapter caseTaskProposal(TaskProposal<T> object) {
				return createTaskProposalAdapter();
			}
			@Override
			public <A extends StringEditAnswer> Adapter caseStringEditTaskProposal(StringEditTaskProposal<A> object) {
				return createStringEditTaskProposalAdapter();
			}
			@Override
			public Adapter caseFeatureValued(FeatureValued object) {
				return createFeatureValuedAdapter();
			}
			@Override
			public Adapter caseEFeatureObject(EFeatureObject object) {
				return createEFeatureObjectAdapter();
			}
			@Override
			public Adapter caseTaskEvent(TaskEvent object) {
				return createTaskEventAdapter();
			}
			@Override
			public Adapter caseAbstractStringEditEvent(AbstractStringEditEvent object) {
				return createAbstractStringEditEventAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.workspace.SourceFileEditAnswer <em>Source File Edit Answer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.workspace.SourceFileEditAnswer
	 * @generated
	 */
	public Adapter createSourceFileEditAnswerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.workspace.SourceFileEditProposal <em>Source File Edit Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.workspace.SourceFileEditProposal
	 * @generated
	 */
	public Adapter createSourceFileEditProposalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.workspace.SourceFileEditEvent <em>Source File Edit Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.workspace.SourceFileEditEvent
	 * @generated
	 */
	public Adapter createSourceFileEditEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.workspace.LaunchAnswer <em>Launch Answer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.workspace.LaunchAnswer
	 * @generated
	 */
	public Adapter createLaunchAnswerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.workspace.LaunchProposal <em>Launch Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.workspace.LaunchProposal
	 * @generated
	 */
	public Adapter createLaunchProposalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.workspace.LaunchEvent <em>Launch Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.workspace.LaunchEvent
	 * @generated
	 */
	public Adapter createLaunchEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.Answer <em>Answer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.Answer
	 * @generated
	 */
	public Adapter createAnswerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.TaskAnswer <em>Task Answer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.TaskAnswer
	 * @generated
	 */
	public Adapter createTaskAnswerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.StringEditAnswer <em>String Edit Answer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.StringEditAnswer
	 * @generated
	 */
	public Adapter createStringEditAnswerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.Proposal <em>Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.Proposal
	 * @generated
	 */
	public Adapter createProposalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.TaskProposal <em>Task Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.TaskProposal
	 * @generated
	 */
	public Adapter createTaskProposalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.StringEditTaskProposal <em>String Edit Task Proposal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.StringEditTaskProposal
	 * @generated
	 */
	public Adapter createStringEditTaskProposalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.fv.FeatureValued <em>Feature Valued</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.fv.FeatureValued
	 * @generated
	 */
	public Adapter createFeatureValuedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.fv.EFeatureObject <em>EFeature Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.fv.EFeatureObject
	 * @generated
	 */
	public Adapter createEFeatureObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.TaskEvent <em>Task Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.TaskEvent
	 * @generated
	 */
	public Adapter createTaskEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link no.hal.learning.exercise.AbstractStringEditEvent <em>Abstract String Edit Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see no.hal.learning.exercise.AbstractStringEditEvent
	 * @generated
	 */
	public Adapter createAbstractStringEditEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //WorkspaceAdapterFactory
