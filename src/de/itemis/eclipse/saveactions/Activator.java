package de.itemis.eclipse.saveactions;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin implements IStartup {

	public static final String COMMAND_ID = "de.itemis.eclipse.saveactions.commands.toggleSaveActions"; //$NON-NLS-1$

	public static final String PLUGIN_ID = "de.itemis.eclipse.saveactions"; //$NON-NLS-1$

	private static Activator plugin;

	public Activator() {
		System.out.println("SaveActions.Activator()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(final String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public void earlyStartup() {
		System.out.println("SaveActions.Activator.earlyStartup()");
		SaveActions.hookUp();
		// SaveActions.persistCurrentState();
		// SaveActions.refreshUi();
	}
}
