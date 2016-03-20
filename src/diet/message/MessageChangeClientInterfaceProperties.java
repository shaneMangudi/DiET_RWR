package diet.message;

import java.io.Serializable;

public class MessageChangeClientInterfaceProperties extends Message implements Serializable {

	private int windowNumber;

	private int interfaceproperties;
	private Object value = null;
	private Object value2 = null;

	private String uniqueID;

	public MessageChangeClientInterfaceProperties(String uniqueIDGeneratedByServer, int interfaceproperties) {
		super("server", "server");
		this.interfaceproperties = interfaceproperties;
		this.uniqueID = uniqueIDGeneratedByServer;
	}

	public MessageChangeClientInterfaceProperties(String uniqueIDGeneratedByServer, int interfaceproperties,
			Object value) {
		super("server", "server");
		this.interfaceproperties = interfaceproperties;
		this.value = value;
		this.uniqueID = uniqueIDGeneratedByServer;
	}

	public MessageChangeClientInterfaceProperties(String uniqueIDGeneratedByServer, int interfaceproperties,
			Object value, Object value2) {
		super("server", "server");
		this.interfaceproperties = interfaceproperties;
		this.value = value;
		this.value2 = value2;
		this.uniqueID = uniqueIDGeneratedByServer;
	}

	public int getWindowNumber() {
		return windowNumber;
	}

	public int getInterfaceproperties() {
		return interfaceproperties;
	}

	// public Color getNewBackgroundColour()

	public Object getValue() {
		return value;
	}

	public Object getValue2() {
		return value2;
	}

	public String getUniqueIDGeneratedByServer() {
		return this.uniqueID;
	}

}
