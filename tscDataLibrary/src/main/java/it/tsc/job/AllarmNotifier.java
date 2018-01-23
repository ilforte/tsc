/**
 * 
 */
package it.tsc.job;

/**
 * @author "astraservice" Notify allarms on tsc
 */
public interface AllarmNotifier {
	/**
	 * fire allarms when message received
	 * 
	 * @param message
	 *            JSON message allarms
	 */
	public void onAllarmReceived(String message);

}
