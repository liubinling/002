/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.estyle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pxz
 */
public class CfEStyleBean implements java.io.Serializable {

//    private final List<CfEStyleBean> childrenFolders = new ArrayList<CfEStyleBean>();
    private final List<CfEStyleBean> childrenBeans = new ArrayList<CfEStyleBean>();
    
    private String folderName = "";

    public CfEStyleBean() {
    }

//    
//    /**
//     * @return the childrenFolders
//     */
//    public List<CfEStyleBean> getChildrenFolders() {
//        return childrenFolders;
//    }

    /**
     * @return the childrenBeans
     */
    public List<CfEStyleBean> getChildrenBeans() {
        return childrenBeans;
    }

    /**
     * @return the folderName
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * @param folderName the folderName to set
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public String toString() {
        return  folderName ;
    }
    
    
}
