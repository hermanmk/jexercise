package no.hal.learning.exercise.views.adapters;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import no.hal.emf.ui.parts.adapters.EObjectUIAdapter;
import no.hal.emf.ui.parts.adapters.EObjectUIAdapterImpl;
import no.hal.learning.exercise.ExercisePackage;
import no.hal.learning.exercise.TaskAnswer;
import no.hal.learning.exercise.TaskEvent;
import no.hal.learning.exercise.TaskProposal;

public abstract class TaskProposalUIAdapter<A extends TaskAnswer> extends EObjectUIAdapterImpl<TaskProposal<A>, Composite> implements ProposalUIAdapter<TaskProposal<A>, Composite> {

	public TaskProposalUIAdapter(TaskProposal<A> proposal) {
		super(proposal);
	}

	@Override
	public TaskProposal<A> getProposal() {
		return eObject;
	}

	protected EObjectUIAdapter<?, ?> createAttemptsUI(Composite parent) {
		EObjectUIAdapter<?, ?> attemptsUIAdapter = new TaskAttemptsUIAdapter(getProposal());
		attemptsUIAdapter.initView(parent);
		return attemptsUIAdapter;
	}

	@Override
	protected boolean isChangeNotification(Notification notification) {
		return super.isChangeNotification(notification) && notification.getFeature() == ExercisePackage.eINSTANCE.getTaskProposal_Attempts();
	}

	protected TaskEvent getLastAttempt() {
		EList<TaskEvent> attempts = getProposal().getAttempts();
		return (attempts.size() > 0 ? attempts.get(attempts.size() - 1) : null);
	}
	
	private EObjectUIAdapter<?, ?>[] subAdapters;
	
	protected abstract EObjectUIAdapter<?, ?>[] createSubAdapters();

	@Override
	public Composite initView(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new RowLayout());
		subAdapters = createSubAdapters();
		for (int i = 0; i < subAdapters.length; i++) {
			EObjectUIAdapter<?, ?> uiAdapter = subAdapters[i];
			uiAdapter.setTarget(getProposal());
			uiAdapter.initView(composite);
		}
		setView(composite);
		return composite;
	}

	@Override
	public void updateView() {
		for (int i = 0; i < subAdapters.length; i++) {
			subAdapters[i].updateView();
		}
	}

	@Override
	public void updateModel() {
	}
	
	//

	protected boolean isEmpty(String element) {
		return element == null || "".equals(element);
	}
}