package de.itemis.eclipse.saveactions;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorPart;

public class ProjectSaveActionsTester extends PropertyTester {

	public ProjectSaveActionsTester() {
		super();
	}

	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		final IProject project = ((IFile) ((IEditorPart) receiver).getEditorInput().getAdapter(IFile.class))
				.getProject();
		return SaveActions.checkCurrentProject(project);
	}

}