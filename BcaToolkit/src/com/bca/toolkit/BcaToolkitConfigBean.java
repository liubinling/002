/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit;

import java.awt.Font;
//import org.dom4j.Element;

/**
 * <p>Title: BusinessChangingAdapter</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 北京华夏翰科科技有限公司</p>
 * @author 潘晓忠
 * @version 3.0.000-debug
 */
public class BcaToolkitConfigBean implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;

    /**
     * @return the inst
     */
    public static BcaToolkitConfigBean getInst() {
        return inst;
    }
    boolean zipRmiObjects = false;
    String codeFactory_orgnization = "北京华夏翰科科技有限公司 http://www.hxhk.com.cn";  // 北京华夏翰科科技有限公司 http://www.bcasoft.com";
    String codeFactory_encoding = "UTF-8";
    String codeFactory_author = "工具软件部";
    String defaultFactory = "";  // 默认工厂名称
    // 
    private static final BcaToolkitConfigBean inst = new BcaToolkitConfigBean();

    private BcaToolkitConfigBean() {
    }
//    /**
//     * <p>Title: BusinessChangingAdapter</p>
//     *
//     * <p>Description: </p>
//     *
//     * <p>Copyright: Copyright (c) 2004</p>
//     *
//     * <p>Company: 北京华夏翰科科技有限公司</p>
//     * @author 潘晓忠
//     * @version 3.2.000-debug
//     */
//    public class DockingLayoutData {
//
//        public String layoutName;
//        public boolean eastVisible;
//        public boolean southVisible;
//        public boolean westVisible;
//        public boolean centerVisible;
//        public float southRate;
//        public float eastRate;
//        public float westRate;
//
//        DockingLayoutData() {
//        }
//
//        /**
//         * overview
//         *
//         * @return String
//         */
//        public String overview() {
//            StringBuilder sb = new StringBuilder();
//
//            sb.append("layoutName : ").append(layoutName);
//            sb.append("\neastVisible : ").append(eastVisible);
//            sb.append("\nsouthVisible : ").append(southVisible);
//            sb.append("\nwestVisible : ").append(westVisible);
//            sb.append("\ncenterVisible : ").append(centerVisible);
//            sb.append("\nsouthRate : ").append(southRate);
//            sb.append("\neastRate : ").append(eastRate);
//            sb.append("\nwestRate : ").append(westRate);
//            return sb.toString();
//        }
//    }
//    static final public String BStudio_root = "BStudio";  // 从 FlowBuilder 改变而来
//    static final public String fbXml_pa_activityIconPath = "activityIconPath";
    static final public String fbXml_pa_recentEditFlow = "recentEditFlow";
//    static final public String fbXml_pa_propertyNameTextMode = "propertyNameTextMode";
//    static final public String fbXml_pa_gridEnable = "gridEnable";
//    public static final PubConfig pubcfg = PubConfig.getConfig();
//    public static final String activityIconPath = pubcfg.getResourcesRootDir() + "/images/activity/";
//    public static final String subjectIconPath = pubcfg.getResourcesRootDir() + "/images/subject/";
//    public static final String utilsIconPath = pubcfg.getResourcesRootDir() + "/images/utils/";
    static private BcaToolkitConfigBean cfg = null;
    public String defaultEditingFlowName;     // 0 indicates new flow
    public int defaultEditingFlowVer;
    public boolean propertyNameTextMode;
    public boolean gridEnable;
    public boolean autoOpenLineCustDialog;
    public boolean persistTopComponentEnable;
    //public boolean makeBeforeSave;
    int detaSaveSerial_4_AutoClearHistory = 1;
    public String externalEditor;
    public String externalCompareTool;
    public int sampleFileLinesToLoad = 100;
    public Font defFont;
    
    public boolean logEnvOnSrcCreate = false;   // 根据模板创建代码前，是否打印环境信息。

    public int getDetaSaveSerial_4_AutoClearHistory() {
        return detaSaveSerial_4_AutoClearHistory;
    }

    /**
     * @return the zipRmiObjects
     */
    public boolean isZipRmiObjects() {
        return zipRmiObjects;
    }

    /**
     * @return the codeFactory_orgnization
     */
    public String getCodeFactory_orgnization() {
        return codeFactory_orgnization;
    }

    /**
     * @return the codeFactory_encoding
     */
    public String getCodeFactory_encoding() {
        return codeFactory_encoding;
    }

    /**
     * @return the codeFactory_author
     */
    public String getCodeFactory_author() {
        return codeFactory_author;
    }
//    public LinkedHashMap<String, DockingLayoutData> getDockingLayoutList() {
//        return dockingLayoutList;
//    }
//    public Element getMapItemByXPath(String xpath, String elementName, String keyAttrName, boolean createOnAbsent, String key) {
//        assert xpath != null;
//        Element elemOnXPath = null;
//        if (xpath.length() == 0 || xpath.equals("root")) {
//            elemOnXPath = dom.getDocument().getRootElement();
//        } else {
//            elemOnXPath = getElementByXPath(xpath);
//        }
//
//        if (elemOnXPath == null && createOnAbsent) {
//            log.warn("xpath " + xpath + " not found. create it now.");
//            dom.getDocument().createXPath(xpath);
//            elemOnXPath = getElementByXPath(xpath);
//        }
//        if (elemOnXPath == null) {
//            return null;
//        }
//        for (Object o : elemOnXPath.elements()) {
//            Element e = (Element) o;
//            if (e.getName().equals(elementName) && e.attributeValue(keyAttrName).equals(key)) {
//                return e;
//            }
//        }
//        if (createOnAbsent) {
//            Element e = elemOnXPath.addElement(elementName);
//            e.addAttribute(keyAttrName, key);
//            return e;
//        }
//        return null;
//    }
//    @Override
//    public void loadBasicSettings(Element basicSettingNode, LinkedHashMap<String, StringEntry> basicSettingsMap) {
//        this.basicRoot = basicSettingNode;
//        if (basicRoot.elements().size() == 0) {
//            List<StringEntry> m = new ArrayList<StringEntry>();
//            m.add(new StringEntry("isInDebug", "no"));
//            m.add(new StringEntry("bweHost", "127.0.0.1"));
//            m.add(new StringEntry(fbXml_pa_activityIconPath, pubcfg.getResourcesRootDir() + "/images/activity/"));
//            m.add(new StringEntry("subjectIconPath", pubcfg.getResourcesRootDir() + "/images/subject/"));
//            m.add(new StringEntry(fbXml_pa_recentEditFlow, "TestFlow"));
//            m.add(new StringEntry("recentEditFlowVersion", "0.0.1"));
//            m.add(new StringEntry(fbXml_pa_propertyNameTextMode, "yes"));
//            m.add(new StringEntry(fbXml_pa_gridEnable, "yes"));
//            m.add(new StringEntry("persistTopComponentEnable", "yes"));
//            m.add(new StringEntry("zipRmiObjects", "no"));
//
//
//            dom.putEntryListToElement(basicRoot, m, "prop", "key", "val");
//            this.updateOrderFlag = true;
//        }
//
//        // Map<String, String> getMapFromElements(Element parent, String elementNamePattern, String keyAttrName, String valAttrName) {
////    basicSettingsMap = dom.getMapFromElements(basicRoot, "prop", "key", "val");
//        assert basicSettingsMap != null;
//
//        debugConfig.isInDebug = Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "isInDebug", "no", "prop", "key", "val"));
//
//        zipRmiObjects = Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "zipRmiObjects", "no", "prop", "key", "val"));
//
////    activityIconPath = getMapVal_CreateOnNull(basicSettingsMap, basicRoot, fbXml_pa_activityIconPath,
////                                              "./classes/resources/images/activity/", "prop", "key", "val");
////    subjectIconPath = getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "subjectIconPath", "./classes/resources/images/subject/", "prop",
////                                             "key", "val");
//
//        defaultEditingFlowName = getMapVal_CreateOnNull(basicSettingsMap, basicRoot, fbXml_pa_recentEditFlow, "TestFlow", "prop", "key", "val");
//        defaultEditingFlowVer = Numtool.parseInt(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "recentEditFlowVersion", "0.0.1", "prop", "key", "val"), 0);
//        propertyNameTextMode = Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, fbXml_pa_propertyNameTextMode, "yes", "prop", "key", "val"));
//        gridEnable = Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, fbXml_pa_gridEnable, "yes", "prop", "key", "val"));
//        persistTopComponentEnable = Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "persistTopComponentEnable", "yes", "prop", "key", "val"));
//
//        autoOpenLineCustDialog = Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "autoOpenLineCustDialog", "yes",
//                "prop", "key", "val"));
//        detaSaveSerial_4_AutoClearHistory = Numtool.parseInt(getMapVal_CreateOnNull(basicSettingsMap, basicRoot,
//                "DetaSaveSerial_4_AutoClearHistory", "5", "prop", "key", "val"), 5);
//
//        externalEditor = getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "externalEditor", "%SystemRoot%\\system32\\notepad.exe", "prop", "key", "val");
//        if (externalEditor.contains(" ") && !externalEditor.startsWith("\"")) {
//            externalEditor = "\"" + externalEditor + "\"";
//        }
//
//        // externalCompareTool
//        externalCompareTool = getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "externalCompareTool", "BC2.exe", "prop", "key", "val");
//        if (externalCompareTool.contains(" ") && !externalCompareTool.startsWith("\"")) {
//            externalCompareTool = "\"" + externalCompareTool + "\"";
//        }
//
//        sampleFileLinesToLoad = Numtool.parseInt(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "sampleFileLinesToLoad", "100", "prop", "key", "val"), 100);
//        // Numtool.parseBooleanEx(getAttributeValueWithDef(root, "isInDebug", "false"));
//    }
//    @Override
//    public boolean loadConfig() {
//        if (!checkConfigFileExist(true)) {
//            writeInitialConfig();
//        }
//        // pubcfg.loadConfig();
//
//        if (dom.openDocument(configFileName) == false) {
//            log.warn("failed to load the config(" + configFileName + "), create as default. please check the file.");
//            writeInitialConfig();
//        }
//        if (dom.openDocument(configFileName) == false) {
//            log.warn("failed to create the config file (" + configFileName + "), bwf system hated.");
//            return false;
//        }
//        Element root = checkRootExist(true);
//        if (root == null) {
//            return false;
//        }
//        if (root.getName().equals(BStudio_root) == false) {
//            log.fatal("root name (" + root.getName() + ") not matched " + BStudio_root + ". config failed.");
//            return false;
//        }
//
//        loadBaseConfig(root);
//
//        // to load the defualt font setting.
//        Element defFontElem = this.getElement_CreateOnNull(root, "DefaultFont", "");
//        String defFontName = this.getAttributeValueWithDef(defFontElem, "name", "宋体");
//        int defFontStyle = Numtool.parseInt(this.getAttributeValueWithDef(defFontElem, "style", "0"), 0);
//        int defFontSize = Numtool.parseInt(this.getAttributeValueWithDef(defFontElem, "size", "12"), 12);
//        defFont = new Font(defFontName, defFontStyle, defFontSize);
//        if (defFont == null) {
//            defFont = new Font("宋体", 0, 12);
//        }
//
//        // debugConfig.isInDebug = Numtool.parseBooleanEx(getAttributeValueWithDef(root, "isInDebug", "false"));
//        // if (debugConfig.isInDebug) {
//        debugConfig.loadDebugConfig(root);
//        //}
//
//        dataWizConfig.loadConfig(root);
//
//        writeFileOnNecessary();
//        return true;
//    }
//    public void updateBasicSetting(String key, String val) {
//        StringEntry entry = new StringEntry(key, val);
//        LinkedHashMap<String, StringEntry> mtemp = new LinkedHashMap<String, StringEntry>();
//        mtemp.put(key, entry);
//        dom.putMapToElement(basicRoot, mtemp, "prop", "key", "val");
//        this.updateOrderFlag = true;
//    }
//    @Override
//    public void writeInitialConfig() {
//        dom.createNewDocument();
//        Element root = dom.getDocument().addElement(BStudio_root);
//        writeToFile();
//    }

    /**
     * @return the defaultFactory
     */
    public String getDefaultFactory() {
        return defaultFactory;
    }

    
}
