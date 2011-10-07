package de.itemis.eclipse.saveactions;

import org.eclipse.core.commands.Command;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jface.commands.PersistentState;
import org.eclipse.jface.menus.IMenuStateIds;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.osgi.service.prefs.Preferences;

public final class SaveActions {

	private static final String SAVE_ACTIONS_ENABLED = "editor_save_participant_org.eclipse.jdt.ui.postsavelistener.cleanup";//$NON-NLS-1$
	private static final String INSTANCE_ORG_ECLIPSE_JDT_UI = "/instance/org.eclipse.jdt.ui";//$NON-NLS-1$
	public static final String PREF_SAVE_ACTIONS_ENABLED = "saveActionsEnabled"; //$NON-NLS-1$

	private SaveActions() {
	}

	public static boolean swap() {
		final Preferences jdtUiNode = getJdtUiNode();
		final boolean status = jdtUiNode.getBoolean(SAVE_ACTIONS_ENABLED, false);
		final boolean newStatus = !status;
		jdtUiNode.putBoolean(SAVE_ACTIONS_ENABLED, newStatus);
		return newStatus;
	}

	public static boolean isSaveActionsEnabled() {
		return getJdtUiNode().getBoolean(SAVE_ACTIONS_ENABLED, false);
	}

	static Preferences getJdtUiNode() {
		return Platform.getPreferencesService().getRootNode().node(INSTANCE_ORG_ECLIPSE_JDT_UI);
	}

	public static void persistCurrentState() {
		final boolean enabled = isSaveActionsEnabled();
		final ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(
				ICommandService.class);
		final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		final Command command = commandService.getCommand(Activator.COMMAND_ID);
		final PersistentState state = (PersistentState) command.getState(IMenuStateIds.STYLE);
		state.load(preferenceStore, PREF_SAVE_ACTIONS_ENABLED);
		System.out.println("loaded:" + state.getValue());
		state.setValue(Boolean.valueOf(enabled));
		System.out.println("set:" + state.getValue());
		state.setShouldPersist(true);
		state.save(preferenceStore, PREF_SAVE_ACTIONS_ENABLED);
	}

	public static void refreshUi() {
		((ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class)).refreshElements(
				Activator.COMMAND_ID, null);
	}

	public static void hookUp() {
		final IPropertyChangeListener l = new IPropertyChangeListener() {

			public void propertyChange(final PropertyChangeEvent event) {
				System.out.println(event.getProperty() + " changed to " + event.getNewValue());
				if (SAVE_ACTIONS_ENABLED.equals(event.getProperty())) {
					refreshUi();
				}

			}
		};

		JavaPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(l);

	}
}
