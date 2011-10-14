package de.itemis.eclipse.saveactions.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorPart;

/**
 * 
 * @author willebrandt
 */
public class ProjectSaveActionsTester extends PropertyTester {

	public ProjectSaveActionsTester() {
		super();
	}

	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		final IFile iFile = (IFile) ((IEditorPart) receiver).getEditorInput().getAdapter(IFile.class);
		return SaveActions.trackProjectState(iFile.getProject());
	}

}