/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.gx;

import java.util.HashMap;

/**
 *
 * @author admin
 */
public class PxzHashMap<K, V> extends HashMap<K, V> {

    static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    String logLeading = "";

    @Override
    public V put(K key, V value) {
      //  log.debug(logLeading + ".mapsize=%d. put:<%s><%s> at:%s", size(), key, value, Toolfunc.getCallStack());
        return super.put(key, value);
    }

    /**
     * @param logLeading the logLeading to set
     */
    public void setLogLeading(String logLeading) {
        this.logLeading = logLeading;
    }
}
