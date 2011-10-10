package de.itemis.eclipse.saveactions;

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
