/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit;

import com.bca.pub.PubConfig;
import com.bca.pub.cfg.BcaXmlConfiguration;
import com.bca.pub.tools.Filetool;
import com.bca.pub.tools.Regxtool;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.util.StringEntry;
import com.bca.pub.util.WfProperties;
import com.bca.templ.pub.I_StudioConfig;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
//import org.dom4j.Element;

/**
 * <p>Title: BusinessChangingAdapter</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 北京华夏翰科科技有限公司</p>
 * @author 潘晓忠
 * @version 3.0.000-debug
 */
public class BcaToolkitConfig extends BcaXmlConfiguration implements I_StudioConfig {

    private final BcaToolkitConfigBean cfgBean;

    private BcaToolkitConfig() throws ConfigurationException {
        super("BcaToolkit-config.xml");
//        super.loadConfig();
        cfgBean = BcaToolkitConfigBean.getInst();

    }

    @Override
    protected boolean loadConfigEx() {
        loadBasicSettings();

        // debugConfig.isInDebug = Numtool.parseBooleanEx(getAttributeValueWithDef(root, "isInDebug", "false"));
        // if (debugConfig.isInDebug) {
        debugConfig.loadDebugConfig();
        //}

        dataWizConfig.loadConfig();

        loadCodeFactorySettings();
//        writeFileOnNecessary();
        super.writeFile();
        return true;
    }

    @Override
    protected void prepareSave() {
    }

    private void loadBasicSettings() {
        debugConfig.isInDebug = super.getBoolean("BasicSettings.isInDebug", false);  // Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "isInDebug", "no", "prop", "key", "val"));

        cfgBean.zipRmiObjects = super.getBoolean("BasicSettings.zipRmiObjects", false);  //  Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "zipRmiObjects", "no", "prop", "key", "val"));

//    activityIconPath = getMapVal_CreateOnNull(basicSettingsMap, basicRoot, fbXml_pa_activityIconPath,
//                                              "./classes/resources/images/activity/", "prop", "key", "val");
//    subjectIconPath = getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "subjectIconPath", "./classes/resources/images/subject/", "prop",
//                                             "key", "val");

        cfgBean.defaultEditingFlowName = super.getString("BasicSettings.defaultEditingFlowName", "");  // getMapVal_CreateOnNull(basicSettingsMap, basicRoot, fbXml_pa_recentEditFlow, "TestFlow", "prop", "key", "val");
        cfgBean.defaultEditingFlowVer = super.getInt("BasicSettings.defaultEditingFlowVer", 0);  //Numtool.parseInt(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "recentEditFlowVersion", "0.0.1", "prop", "key", "val"), 0);
        cfgBean.propertyNameTextMode = super.getBoolean("BasicSettings.propertyNameTextMode", false);  // Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, fbXml_pa_propertyNameTextMode, "yes", "prop", "key", "val"));
        cfgBean.gridEnable = super.getBoolean("BasicSettings.gridEnable", false);  // Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, fbXml_pa_gridEnable, "yes", "prop", "key", "val"));
        cfgBean.persistTopComponentEnable = super.getBoolean("BasicSettings.persistTopComponentEnable", false);  // Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "persistTopComponentEnable", "yes", "prop", "key", "val"));

        cfgBean.autoOpenLineCustDialog = super.getBoolean("BasicSettings.autoOpenLineCustDialog", false);  // Numtool.parseBooleanEx(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "autoOpenLineCustDialog", "yes",                "prop", "key", "val"));
        cfgBean.detaSaveSerial_4_AutoClearHistory = super.getInt("BasicSettings.detaSaveSerial_4_AutoClearHistory", 0);  // Numtool.parseInt(getMapVal_CreateOnNull(basicSettingsMap, basicRoot,                "DetaSaveSerial_4_AutoClearHistory", "5", "prop", "key", "val"), 5);

        cfgBean.externalEditor = super.getString("BasicSettings.externalEditor", "");  //getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "externalEditor", "%SystemRoot%\\system32\\notepad.exe", "prop", "key", "val");
        if (cfgBean.externalEditor.contains(" ") && !cfgBean.externalEditor.startsWith("\"")) {
            cfgBean.externalEditor = "\"" + cfgBean.externalEditor + "\"";
        }

        // externalCompareTool
        cfgBean.externalCompareTool = super.getString("BasicSettings.externalCompareTool", "");  //getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "externalCompareTool", "BC2.exe", "prop", "key", "val");
        if (cfgBean.externalCompareTool.contains(" ") && !cfgBean.externalCompareTool.startsWith("\"")) {
            cfgBean.externalCompareTool = "\"" + cfgBean.externalCompareTool + "\"";
        }

        cfgBean.sampleFileLinesToLoad = super.getInt("BasicSettings.sampleFileLinesToLoad", 0);  // Numtool.parseInt(getMapVal_CreateOnNull(basicSettingsMap, basicRoot, "sampleFileLinesToLoad", "100", "prop", "key", "val"), 100);
        cfgBean.logEnvOnSrcCreate = super.getBoolean("BasicSettings.logEnvOnSrcCreate", false);  
//            // to load the defualt font setting.


//        Element defFontElem = getElement_CreateOnNull(root, "DefaultFont", "");
        String defFontName = "宋体"; // super.getString("DefaultFont.name", "宋体");     //  this.getAttributeValueWithDef(defFontElem, "name", "宋体");
        int defFontStyle = super.getInt("DefaultFont.style", 0);            //  Numtool.parseInt(this.getAttributeValueWithDef(defFontElem, "style", "0"), 0);
        int defFontSize = super.getInt("DefaultFont.size", 12);             //  Numtool.parseInt(this.getAttributeValueWithDef(defFontElem, "size", "12"), 12);
        cfgBean.defFont = new Font(defFontName, defFontStyle, defFontSize);
        if (cfgBean.defFont == null) {
            cfgBean.defFont = new Font("宋体", 0, 12);
        }
    }

    public void updateBasicSetting(String key, String val) {
        this.saveProperty("BasicSetting." + key, val);
    }

    /**
     * @return the cfgBean
     */
    public BcaToolkitConfigBean getCfgBean() {
        return cfgBean;
    }

    private void loadCodeFactorySettings() {
        cfgBean.codeFactory_author = super.getString("CodeFactory.author", "ZKY-grp");
        cfgBean.codeFactory_encoding = super.getString("CodeFactory.encoding", "UTF-8");
        cfgBean.codeFactory_orgnization = super.getString("CodeFactory.orgnization", "BeijingShengJie http://www.sjcrm.com");
        cfgBean.defaultFactory = super.getString("CodeFactory.defaultFactory", "");
    }

    @Override
    public String getExternalEditor() {
        if (cfgBean.externalEditor == null) {
            cfgBean.externalEditor = "";
        }
        if (!cfgBean.externalEditor.startsWith("\"")) {
            cfgBean.externalEditor = "\"" + cfgBean.externalEditor + "\"";
        }

        return cfgBean.externalEditor;
    }

    public class DataWizConfig {

//        Element edw;
        private int sampleDataRows;
        private String pojoOutputDir;
        private String defaultPackage;
        private boolean pkConstructorEnable;
        private boolean fullConstructorEnable;  // 刨掉 Serializable 的变量
        private List<StringEntry> typeConverterList = new ArrayList<StringEntry>();
        boolean caseSensitive;
        private boolean viewMetaOnClickTable;
        private boolean viewSampleDataOnClickTable;
        private Set<String> pojoPackages = new TreeSet<String>();

        DataWizConfig() {
        }

        public void addPojoPkg(String packageName) {
            if (packageName.isEmpty() || pojoPackages.contains(packageName)) {
                return;
            }
            pojoPackages.add(packageName);
            StringBuffer sb = new StringBuffer();
            for (String s : pojoPackages) {
                sb.append(s).append(';');
            }
            Stringtool.reduceLength(sb, 1);
//            writeAttributeValue(edw, "pojoPackages", sb.toString());
            BcaToolkitConfig.super.saveProperty("DataWizConfig.pojoPackages", sb.toString());
//            writeFileOnNecessary();
            BcaToolkitConfig.super.writeFile();
        }

        public String getConvertedJavaType(String fieldName, String sqltype) {      // 此处还需改良 应该对字段类型有所约束

            boolean match;
            for (StringEntry entry : typeConverterList) {
                if (caseSensitive) {
                    match = fieldName.matches(entry.getKey());
                } else {
                    match = Regxtool.matchRegularEx(fieldName.toLowerCase(), entry.getKey().toLowerCase(), true, false);
                }
                if (match) {
                    if (checkJavatypeSyntax(sqltype, entry.getVal())) {
                        return entry.getVal();
                    } else {
                        return null;
                    }
                }
            }
            return "";
        }

        private boolean checkJavatypeSyntax(String sqltype, String javatype) {
            if(javatype.equals("boolean")) {
                return sqltype.toLowerCase().startsWith("number") || sqltype.toLowerCase().startsWith("int");
            }
            return true;
        }

        public String getDefaultPackage() {
            return defaultPackage;
        }

        public String getPojoOutputDir() {
            return pojoOutputDir;
        }

        public int getSampleDataRows() {
            return sampleDataRows;
        }

        public boolean isFullConstructorEnable() {
            return fullConstructorEnable;
        }

        public boolean isPkConstructorEnable() {
            return pkConstructorEnable;
        }

        public boolean isViewMetaOnClickTable() {
            return viewMetaOnClickTable;
        }

        public boolean isViewSampleDataOnClickTable() {
            return viewSampleDataOnClickTable;
        }

        private void loadConfig() {
//            assert root != null;
//            edw = getElement_CreateOnNull(root, "DataWizConfig", "note: defaultPackage can write as pattern include flow package. such as #flowPackage#.orm ");
            sampleDataRows = BcaToolkitConfig.super.getInt("DataWizConfig.sampleDataRows", 100);  //  Numtool.parseInt(getAttributeValueWithDef(edw, "sampleDataRows", "100"), 100);
            pojoOutputDir = BcaToolkitConfig.super.getString("DataWizConfig.pojoOutputDir", "./pojos");  // getAttributeValueWithDef(edw, "pojoOutputDir", "./pojos");
            pkConstructorEnable = BcaToolkitConfig.super.getBoolean("DataWizConfig.pkConstructorEnable", false);  // Numtool.parseBooleanEx(getAttributeValueWithDef(edw, "pkConstructorEnable", "no"));
            fullConstructorEnable = BcaToolkitConfig.super.getBoolean("DataWizConfig.fullConstructorEnable", false);  // Numtool.parseBooleanEx(getAttributeValueWithDef(edw, "fullConstructorEnable", "no"));

            viewMetaOnClickTable = BcaToolkitConfig.super.getBoolean("DataWizConfig.viewMetaOnClickTable", false);  // Numtool.parseBooleanEx(getAttributeValueWithDef(edw, "viewMetaOnClickTable", "no"));
            viewSampleDataOnClickTable = BcaToolkitConfig.super.getBoolean("DataWizConfig.viewSampleDataOnClickTable", false);  // Numtool.parseBooleanEx(getAttributeValueWithDef(edw, "viewSampleDataOnClickTable", "no"));

            //
            //      edw.addComment("note: defaultPackage can write as pattern include flow package. such as #flowPackage#.orm ");
            //
            defaultPackage = BcaToolkitConfig.super.getString("DataWizConfig.defaultPackage", "#flowPackage#.orm"); // getAttributeValueWithDef(edw, "defaultPackage", "#flowPackage#.orm");
            String pojoPackages_s = BcaToolkitConfig.super.getString("DataWizConfig.pojoPackages", "");  // getAttributeValueWithDef(edw, "pojoPackages", "");
            for (String s : pojoPackages_s.split(";")) {
                if (!s.isEmpty()) {
                    pojoPackages.add(s);
                }
            }

            Filetool.isDirExist(PubConfig.Dirs.CLASS_EXPORT_DIR_FLOW, true);
            Filetool.isDirExist(PubConfig.Dirs.CLASS_EXPORT_DIR_FLOW_CLASSES, true);
            Filetool.isDirExist(PubConfig.Dirs.CLASS_EXPORT_DIR_DE_SRC, true);
            //
            Filetool.isDirExist(PubConfig.Dirs.CLASS_EXPORT_DIR_PO, true);
            Filetool.isDirExist(PubConfig.Dirs.CLASS_EXPORT_DIR_PO_CLASSES, true);
            Filetool.isDirExist(PubConfig.Dirs.CLASS_EXPORT_DIR_PO_SRC, true);

//            Element eTypeConv = getElement_CreateOnNull(edw, "TypeConverter", "Type converter for fields -> javaType. can use regular patterns.");
            loadTypeChangeMap();
            caseSensitive = BcaToolkitConfig.super.getBoolean("DataWizConfig.TypeConverter.caseSensitive", false);  // Numtool.parseBooleanEx(getAttributeValueWithDef(eTypeConv, "caseSensitive", "no"));
        }

        private void loadTypeChangeMap() {
            Configuration subcfg = subset("DataWizConfig.TypeConverter");
            List<String> groups = getChildrenList(subcfg, "*");  // new UniqueArrayList<String>();
            if (groups.isEmpty()) {
                BcaToolkitConfig.super.saveProperty("DataWizConfig.TypeConverter.converter_1.FieldNamePattern", "*Enable");
                BcaToolkitConfig.super.saveProperty("DataWizConfig.TypeConverter.converter_1.TargetJavaType", "boolean");
            }

            for (String grp : groups) {
                String pattern = subcfg.getString(grp + ".FieldNamePattern", "").toLowerCase();
                String varType = subcfg.getString(grp + ".TargetJavaType", "");
                // private List<StringEntry> typeConverterList = new ArrayList<StringEntry>();
                StringEntry se = new StringEntry(pattern, varType);
                typeConverterList.add(se);
            }
        }

        public void setDefaultPackage(String defaultPackage) {
            this.defaultPackage = defaultPackage;
            BcaToolkitConfig.super.saveProperty("DataWizConfig.defaultPackage", defaultPackage);
//            writeAttributeValue(edw, "defaultPackage", defaultPackage);
//
//            writeFileOnNecessary();
        }

        public void setPojoOutputDir(String pojoOutputDir) {
            this.pojoOutputDir = pojoOutputDir;
            BcaToolkitConfig.super.saveProperty("DataWizConfig.pojoOutputDir", pojoOutputDir);
//            edw.addAttribute("pojoOutputDir", pojoOutputDir);
//            updateOrderFlag = true;
        }

        public void setSampleDataRows(int sampleDataRows) {
            this.sampleDataRows = sampleDataRows;
            BcaToolkitConfig.super.saveProperty("DataWizConfig.sampleDataRows", sampleDataRows);
//            edw.addAttribute("sampleDataRows", Integer.toString(sampleDataRows));
//            updateOrderFlag = true;
        }

        public void setViewMetaOnClickTable(boolean viewMetaOnClickTable) {
            this.viewMetaOnClickTable = viewMetaOnClickTable;
            BcaToolkitConfig.super.saveProperty("DataWizConfig.viewMetaOnClickTable", viewMetaOnClickTable);
//            edw.addAttribute("viewMetaOnClickTable", viewMetaOnClickTable ? "yes" : "no");
//            updateOrderFlag = true;
        }

        public void setViewSampleDataOnClickTable(boolean viewSampleDataOnClickTable) {
            this.viewSampleDataOnClickTable = viewSampleDataOnClickTable;
            BcaToolkitConfig.super.saveProperty("DataWizConfig.viewSampleDataOnClickTable", viewSampleDataOnClickTable);
//            edw.addAttribute("viewSampleDataOnClickTable", viewSampleDataOnClickTable ? "yes" : "no");
//            updateOrderFlag = true;
        }

        /**
         * @return the pojoPackages
         */
        public Set<String> getPojoPackages() {
            return pojoPackages;
        }
    }

    /**
     * <p>Title: BusinessChangingAdapter</p>
     *
     * <p>Description: </p>
     *
     * <p>Copyright: Copyright (c) 2004</p>
     *
     * <p>Company: 北京华夏翰科科技有限公司</p>
     * @author 潘晓忠
     * @version 3.2.000-debug
     */
    public class DebugConfig {

        private boolean isInDebug;
//        public boolean showDataWizAtStartup;
//        public boolean skipFlowLoading;
        public boolean alwaysOpenArgInputDialog;
//        private Element eDebug;

        DebugConfig() {
        }

        public WfProperties loadArgInput(int flowId, WfProperties defaultArgValues) {
//            assert eDebug != null;
//      if (com.bca.pub.cfg.LogSwitch.debugLogEnable) log.debug("####### defaultArgValues: %s", defaultArgValues.asXML());

            WfProperties configInArgValues = defaultArgValues.clone();
            for (String key : defaultArgValues.keySet()) {
                Object v = BcaToolkitConfig.super.getProperty("DebugConfig.ArgsListOnDebug." + key);
                configInArgValues.setProp("DebugConfig.ArgsListOnDebug." + key, v);
            }
            return configInArgValues;

//            Element eArgsList = getElement_CreateOnNull(eDebug, "ArgsListOnDebug", "");
//            Element myArgsList = null;
//            for (Object o : eArgsList.elements()) {
//                Element e = (Element) o;
//                if (flowId == Numtool.parseInt(e.attributeValue("FlowID"), 0)) {
//                    myArgsList = e;
//                }
//            }
//            if (myArgsList == null) {
//                return configInArgValues;
//            }
//
//            String in = myArgsList.attributeValue("InstID");
//            if (in == null) {
//                in = "";
//            } else {
//                in = in.trim();
//            }
////            instID.append(myArgsList.attributeValue("InstID"));
//////      configInArgValues.copyFrom(defaultArgValues);
//            if (myArgsList == null) {
//                return configInArgValues;
//            }
//                        BcaToolkitConfig.super.saveProperty("DebugConfig.viewMetaOnClickTable", viewMetaOnClickTable);

//            LinkedHashMap<String, StringEntry> m = dom.getMapFromElements(myArgsList, "arg", "key", "val");
//            for (WfPropUnit p : configInArgValues.values()) {
//                if (WfNodeRt.basicPropNames.contains(p.getKey())) {
//                    continue;
//                }
//                if (m.get(p.getKey()) == null) {
//                    continue;
//                }
//                String val = m.get(p.getKey()).getVal();
//                if (p.getKey().equals("callStack")) {
////          if (com.bca.pub.cfg.LogSwitch.debugLogEnable) log.debug("##### %s", p.asXML());
//                }
//                if (val != null) {
//                    p.setVal(val);
//                }
//            }
//            return configInArgValues;
        }

        private void loadDebugConfig() {
//            assert root != null;
//            eDebug = getElement_CreateOnNull(root, "DebugConfig", "");
//            showDataWizAtStartup = Numtool.parseBooleanEx(getAttributeValueWithDef(eDebug, "showDataWizAtStartup", "no"));
//            skipFlowLoading = Numtool.parseBooleanEx(getAttributeValueWithDef(eDebug, "skipFlowLoading", "no"));
            alwaysOpenArgInputDialog = BcaToolkitConfig.super.getBoolean("DebugConfig.alwaysOpenArgInputDialog", false);  //  Numtool.parseBooleanEx(getAttributeValueWithDef(eDebug, "alwaysOpenArgInputDialog", "yes"));
        }

        public void saveFlowDebugSettings(int flowId, WfProperties inArgValues) {
            BcaToolkitConfig.super.setProperty("DebugConfig.alwaysOpenArgInputDialog", alwaysOpenArgInputDialog);
//            assert eDebug != null;
//            eDebug.addAttribute("alwaysOpenArgInputDialog", Boolean.toString(alwaysOpenArgInputDialog));
//
//            Element eArgsList = getElement_CreateOnNull(eDebug, "ArgsListOnDebug", "");
//            for (Object o : eArgsList.elements()) {
//                Element e = (Element) o;
//                if (flowId == Numtool.parseInt(e.attributeValue("FlowID"), 0)) {
//                    eArgsList.remove(e);
//                }
//            }
//            Element myArgsList = getElement_CreateOnNull(eArgsList, "ArgsList", "");
//            myArgsList.addAttribute("FlowID", Integer.toString(flowId));
////            myArgsList.addAttribute("InstID", Integer.toString(instID));
//
//            List<StringEntry> m = new ArrayList<StringEntry>();
//            for (WfPropUnit p : inArgValues.values()) {
//                if (!WfNodeRt.basicPropNames.contains(p.getKey())) {
//                    m.add(new StringEntry(p.getKey(), p.getValAsString()));
//                }
//            }
//            dom.putEntryListToElement(myArgsList, m, "arg", "key", "val");
//
//            updateOrderFlag = true;
        }
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
    static private BcaToolkitConfig cfg = null;

    public static BcaToolkitConfig getConfig() {
        if (cfg == null) {
            try {
                cfg = new BcaToolkitConfig();
                cfg.loadConfig();
            } catch (ConfigurationException ex) {
                log.error(com.bca.pub.tools.Toolfunc.getCallLocation(ex.getStackTrace()) + ":" + ex.getMessage(), ex);
                cfg.writeFile();
            }
        }
        return cfg;
    }
    private DebugConfig debugConfig = new DebugConfig();
    private DataWizConfig dataWizConfig = new DataWizConfig();
//    private LinkedHashMap<String, DockingLayoutData> dockingLayoutList = new LinkedHashMap<String, DockingLayoutData>();
//    private Element basicRoot;
//    private LinkedHashMap<String, StringEntry> basicSettingsMap; // 应该考虑使用 StringEntryMap

//    private BcaToolkitConfig() {
//        configFileName = SGlobal.getConfigFilePath("bstudio-config.xml");
//    }
    public DataWizConfig getDataWizConfig() {
        return dataWizConfig;
    }

    public DebugConfig getDebugConfig() {
        return debugConfig;
    }

//    public int getDetaSaveSerial_4_AutoClearHistory() {
//        return detaSaveSerial_4_AutoClearHistory;
//    }
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
    public boolean isInDebug() {
        return this.debugConfig.isInDebug;
    }

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
    public void saveExternalEditor(String newEditor) {
        cfgBean.externalEditor = newEditor;
        super.saveProperty("BasicSettings.externalEditor", cfgBean.externalEditor);
//        this.updateBasicSetting("externalEditor", newEditor);
//        this.writeFileOnNecessary();
        super.writeFile();
    }

    // externalCompareTool
    @Override
    public void saveExternalCompareTool(String ntool) {
        cfgBean.externalCompareTool = ntool;
        super.saveProperty("BasicSettings.externalCompareTool", cfgBean.externalCompareTool);
//        this.updateBasicSetting("externalCompareTool", externalCompareTool);
//        this.writeFileOnNecessary();
        super.writeFile();
    }
//    @Override
//    public void writeInitialConfig() {
//        dom.createNewDocument();
//        Element root = dom.getDocument().addElement(BStudio_root);
//        writeToFile();
//    }

    @Override
    public String getExternalCompareTool() {
        if (cfgBean.externalCompareTool == null) {
            cfgBean.externalCompareTool = "";
        }
        if (!cfgBean.externalCompareTool.startsWith("\"")) {
            cfgBean.externalCompareTool = "\"" + cfgBean.externalCompareTool + "\"";
        }
        return cfgBean.externalCompareTool;
    }
}
