/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diet.utils;

import java.util.Hashtable;

/**
 *
 * @author gj
 */
public class HashtableWithDefaultvalue {

	public Hashtable ht = new Hashtable();
	Object defaultValue;

	public HashtableWithDefaultvalue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Object getObject(Object key) {
		Object retValue = ht.get(key);
		if (retValue == null) {
			ht.put(key, defaultValue);
			return defaultValue;
		}
		return retValue;
	}

	public void putObject(Object key, Object value) {
		ht.put(key, value);
	}

}
