/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.message;

/**
 *
 * @author Greg
 */
public class MessageDisplayChangeWebpage extends Message {

	String id;
	String url;

	String prepend = "";
	String append = "";

	long lengthOfTime = -999999; // Anything negative is treated as display
									// forever

	public MessageDisplayChangeWebpage(String id, String url) {
		super("server", "server");
		this.id = id;
		this.url = url;
	}

	public MessageDisplayChangeWebpage(String id, String url, String prepend, String append) {
		super("server", "server");
		this.id = id;
		this.url = url;
		this.prepend = prepend;
		this.append = append;
	}

	public MessageDisplayChangeWebpage(String id, String url, String prepend, String append, long lengthOfTime) {
		super("server", "server");
		this.id = id;
		this.url = url;
		this.prepend = prepend;
		this.append = append;
		this.lengthOfTime = lengthOfTime;
	}

	public long getLengthOfTime() {
		return this.lengthOfTime;
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getAppend() {
		return this.append;
	}

	public String getPrepend() {
		return this.prepend;
	}
}
