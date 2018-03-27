/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.opt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author pxz
 */
public class CodeFactoryOptions implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    public List<String> recentUsedProjNames = new ArrayList<String>();
    public List<String> recentUsedPackages = new ArrayList<String>();

    public List<String> recentUsedPackageRoot = new ArrayList<String>();
    public List<String> recentUsedPackageHome = new ArrayList<String>();
     //????¡ã¨¹??????¡Á?¡ã¨¹???¡§?¨®?¨ª??????
    public List<String> recentUsedChildFolder = new ArrayList<String>();
      //????¡ã¨¹????????¡ã¨¹???¡§?¨®?¨ª??????
    public List<String> recentUsedFatherFolder = new ArrayList<String>();
    //???¡§???¡§?¨®?¨ª??????
    public List<String> recentUsedCreator = new ArrayList<String>();
    public List<String> recentUsedPackageTail = new ArrayList<String>();
    public List<String> recentUsedDaoPrefix = new ArrayList<String>();

    public List<String> recentUsedJspPaths = new ArrayList<String>();
    public Properties properties = new Properties();   // 
    public Map<String, List<String>> recentUsedList = new ConcurrentHashMap<String, List<String>>();
    //¡À???????SQL??????
    public ArrayList<HashMap<String, String>> recentInputSQLs = new ArrayList<HashMap<String, String>>();

    public CodeFactoryOptions() {
    }

    public void checkFields() {
        if (properties == null) {
            properties = new Properties();
        }
        if (recentUsedProjNames == null) {
            recentUsedProjNames = new ArrayList<String>();
        }
        if (recentUsedList == null) {
            recentUsedList = new ConcurrentHashMap<String, List<String>>();
        }
        if (recentUsedPackages == null) {
            recentUsedPackages = new ArrayList<String>();
        }
        if (recentUsedPackageRoot == null) {
            recentUsedPackageRoot = new ArrayList<String>();
        }
        if (recentUsedPackageHome == null) {
            recentUsedPackageHome = new ArrayList<String>();
        }
        if (recentUsedPackageTail == null) {
            recentUsedPackageTail = new ArrayList<String>();
        }
        //??¡ã¨¹????????¡ã¨¹???¡§?¨®?¨ª??????
        if (recentUsedFatherFolder == null) {
            recentUsedFatherFolder = new ArrayList<String>();
        }
        //??¡ã¨¹??????¡Á?¡ã¨¹???¡§?¨®?¨ª??????
        if (recentUsedChildFolder == null) {
            recentUsedChildFolder = new ArrayList<String>();
        }
        //???¡§???¡§?¨®?¨ª??????
        if(recentUsedCreator == null){
            recentUsedCreator = new ArrayList<String>();
        }
        if (recentUsedJspPaths == null) {
            recentUsedJspPaths = new ArrayList<String>();
        }
        //????SQL?¡§?¨ª???¡À????2017/05/12 10??44  ???¨¬????
        if(recentInputSQLs == null){
            recentInputSQLs = new ArrayList<HashMap<String, String>>();
        }
    }

    private synchronized boolean regForList(String s, List<String> dataList) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        if (dataList.size() > 0 && s.equals(dataList.get(0))) {
            return false;
        }
        dataList.remove(s);
        dataList.add(0, s);
        if (dataList.size() > 20) {
            for (int i = dataList.size() - 1; i >= 20; i--) {
                dataList.remove(i);
            }
        }
        return true;
    }

    public boolean regPackage(String s) {
        return regForList(s, recentUsedPackages);
    }

    public boolean regJspPath(String s) {
        return regForList(s, recentUsedJspPaths);
    }

    public boolean regPkgRoot(String s) {
        return regForList(s, recentUsedPackageRoot);
    }

    public boolean regPkgHome(String s) {
        return regForList(s, recentUsedPackageHome);
    }
    
    public boolean regChildFolder(String s) {
        return regForList(s, recentUsedChildFolder);
    }
    
    public boolean regFatherFolder(String s) {
        return regForList(s, recentUsedFatherFolder);
    }
    public boolean regCreator(String s) {
        return regForList(s, recentUsedCreator);
    }

    public boolean regPkgTail(String s) {
        return regForList(s, recentUsedPackageTail);
    }

    public boolean regDaoPrefix(String s) {
        if (recentUsedDaoPrefix == null) {
            recentUsedDaoPrefix = new ArrayList<String>();
        }
        return regForList(s, recentUsedDaoPrefix);
    }
   //????SQL?¡§?¨ª???¡À????2017/05/12 10??44  ???¨¬????
    //¡À???????SQL
    public void regInputSQLs(String name, String inputSQLTxt){
        if(recentInputSQLs == null)
            recentInputSQLs = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> inputSQL = new HashMap<String, String>();
        inputSQL.put(name, inputSQLTxt);
        recentInputSQLs.add(inputSQL);
        //??????????????????20?????¨°????????????¡¤??????????¨°????¡Á???????????????????¡À¨º¡Á?????????
        int size = recentInputSQLs.size();
        if(size > 20){
            for(int i = 0; i < size - 20; i++){
                recentInputSQLs.remove(i);
            }
        }
    }
    //????????SQL
    public ArrayList<HashMap<String, String>> getInputSQLs(){
        return recentInputSQLs;
    }

    public String getProperty(String key) {
        String s = properties.getProperty(key);
        return s == null ? "" : s;
    }

    public void setProperty(String key, String val) {
        properties.setProperty(key, val);
    }

    public boolean regprojName(String s) {
        return regForList(s, this.recentUsedProjNames);
    }

    public synchronized List<String> getMemList(String grp) {
        List<String> list = recentUsedList.get(grp);
        if (list == null) {
            list = new ArrayList<String>();
            recentUsedList.put(grp, list);
        }
        return list;
    }

    public void regMemListName(String s, String grp) {
        List<String> dataList = this.getMemList(grp);
        dataList.remove(s);
        dataList.add(0, s);
        if (dataList.size() > 20) {
            for (int i = dataList.size() - 1; i >= 20; i--) {
                dataList.remove(i);
            }
        }
    }
}
