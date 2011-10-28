/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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