/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
 package de.itemis.eclipse.saveactions.internal;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

/**
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 * 
 * @author willebrandt
 */
public final class ToggleSaveActionsHandler extends AbstractHandler implements IElementUpdater {
	public ToggleSaveActionsHandler() {
	}

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		SaveActions.swap();
		// no need to refresh the ui.. will be triggered by preferences listener
		return null;
	}

	/**
	 * Update command element with toggle state
	 */
	public void updateElement(final UIElement element, @SuppressWarnings("rawtypes") final Map parameters) {
		element.setChecked(SaveActions.isSaveActionsEnabled());
	}

}
