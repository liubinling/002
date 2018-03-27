/*
 * PojoCreateModel.java
 *
 * Created on 2007年8月3日, 下午3:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql;

import com.bca.api.local.MetaClientApi;
import com.bca.api.pub.Ret;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_AbstractTableElement;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.db.meta.unit.Meta_TableElement;
import com.bca.kernel.LocalMetaDataContainer;
import com.bca.toolkit.top.tools.sql.qb.QbSceneModel;
import com.bca.toolkit.top.tools.sql.qb.QbTableBean;
import com.bca.toolkit.top.tools.sql.qb.QbTable_JTableModel;
import java.util.LinkedHashMap;
import com.bca.pub.gui.util.TransferableArrayList;
import com.bca.pub.tools.Filetool;
import com.bca.pub.tools.Ostool;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Systool;
import com.bca.pub.tools.Wftool;
import com.bca.templ.pub.AbstractStudioApp;
import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.orm.PojoField;
import com.bca.toolkit.top.tools.orm.PojoSourceCreatorUnit;
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.Creator_4_tables;
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.Cf_BoncEpmUIOptionDialog;
import com.bca.toolkit.top.tools.sql.impl.boncLink.QbTablePopDialog_BoncLink;
import com.bca.toolkit.top.tools.sql.impl.qry.FactoryUiTool_Qry;
import com.bca.toolkit.top.tools.sql.impl.qry.SqlSourceCreator_4_Query;
import com.bca.toolkit.top.tools.sql.impl.sh.Cf_ShFactoryOptionDialog;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemBean;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import com.bca.toolkit.top.tools.sql.qb.QbRightPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import com.bca.db.meta.unit.FilesInfo;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.commons.net.ntp.TimeStamp;

/**
 *
 * @author pxz
 */
public class SqlCreateModel implements java.io.Serializable, DropTargetListener {

    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * @param aUrl the url to set
     */
    public static void setUrl(String aUrl) {
        url = aUrl;
    }
    
    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    private final transient BcaTool_SqlBuilderTopForm sqlBuilderPanel;
    public transient QbSceneModel sceneModel;
    public String alias;
    private final transient TreeMap<Integer, QbOutputFieldUnit> outputFields = new TreeMap<Integer, QbOutputFieldUnit>();
    public String recentPackageName = "pxz.demo.pojo";
    public SrcFactoryBean activeFactory_SQL;
    public SrcFactoryBean activeFactory;
   //测试
    public Creator_4_tables tablesCreator = new Creator_4_tables();
//    SrcFactoryBean activeFactory = null;
    public Map<String, PojoField> pojoFields = new LinkedHashMap<String, PojoField>();
    public Meta_Table dbTable;
    //用于存储表字段注释的属性
    private List<Map<String, String>> sqlComment;
    private int picNum;
    String modelType = new String();
    private static String url;
    /**
     * 输出的代码中，字段屏蔽标记。 key: 是功能类型： listdiv, add update view exp imp ... List:
     * 是改功能中屏蔽的字段。 不在里面的 都不屏蔽 （缺省是输出的)
     */
    private List<String> colSortList = new ArrayList<String>();
    final Map<String, Set<String>> maskedFields = new HashMap<String, Set<String>>();
    final Map<String, WebUiMemBean> uiStyles = new HashMap<String, WebUiMemBean>();
    final Map<String, Set<String>> groupFields = new HashMap<String, Set<String>>();
    private int compPerRow = 2;   // 记录在详细表单中，每行摆几个控件。
    private boolean confirmReplaceAtDos = true;  // dos脚本中，覆盖前是否需要确认。
    //保存SQL预览内容
    public String sqlPreview = "";
    private FilesListDialog flDialog = null;
    public final static String INSERT="INSERT", REMOVE="REMOVE", UPDATE="UPDATE", QUERY="QUERY";
    private Map<String,Boolean> filesToCreate;    //将要创建的文件路径集合
//    public String jspPath = "/app/hxhk/demo";
//    private I_SqlCreator sqlCreator;
    /**
     * Creates a new instance of PojoCreateModel
     */
    public SqlCreateModel(BcaTool_SqlBuilderTopForm sqlBuilderPanel) {
        this.sqlBuilderPanel = sqlBuilderPanel;
        activeFactory_SQL = new SrcFactoryBean("QueryBuilder", new SqlSourceCreator_4_Query(), new Cf_ShFactoryOptionDialog(), null, new FactoryUiTool_Qry());
        activeFactory = activeFactory_SQL;
        
    }
    
    public void clear() {
        outputFields.clear();
        uiStyles.clear();
        maskedFields.clear();
        groupFields.clear();
    }
    
    public WebUiMemBean getUiStyle(String fieldName) {
        return uiStyles.get(fieldName);
    }
    
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }
    
    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }
    
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }
    
    @Override
    public void dragExit(DropTargetEvent dte) {
    }
    
    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (int i = 0; i < flavors.length; i++) {
                if (flavors[i].isMimeTypeEqual(DataFlavor.javaSerializedObjectMimeType)) { // .isFlavorJavaFileListType()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Object o = tr.getTransferData(flavors[i]);
                    if (o instanceof TransferableArrayList) {
                        TransferableArrayList list = (TransferableArrayList) o;
                        alias = list.getProperties().getProperty("alias");
                        
                        for (Object e : list) {
                            if (e instanceof Meta_AbstractTableElement) {
                                if (this.dbTable != null) {
                                    boolean b = Wftool.confirmDialog("小心", "正在编辑的ORM映射将被覆盖。确认吗?");
                                    if (!b) {
                                        dtde.dropComplete(true);
                                        return;
                                    }
                                }
                                Meta_TableElement te = (Meta_TableElement) e;
//                                Point pt = dtde.getLocation();
//                                createTableCell(alias, te.getName(), DbObjType.Table, dtde.getLocation().x, dtde.getLocation().y);
                                if (com.bca.pub.cfg.SGlobal.debugLogEnable) {
                                    log.debug("创建持久类:%s", te.getName());
                                }
                                if (createORM(te)) {
                                    getSqlBuilderPanel().reloadORM();
                                }
//                            } else if(e instanceof Meta_ViewElement) {
//                                Meta_ViewElement te = (Meta_ViewElement) e;
//                                Point pt = dtde.getLocation();
////                                createTableCell(alias, te.getName(), DbObjType.View, dtde.getLocation().x, dtde.getLocation().y);
                                dtde.dropComplete(true);
                                return;
                            } else {
                                log.error("TransferableArrayList中有未支持的对象类型：%s", e.getClass());
                                continue;
                            }
                        }
//                    } else if (o instanceof String) {
//                        if("create new EtlTransferCell".equals(o)) {
//                            this.createTransferCell(dtde.getLocation().x, dtde.getLocation().y);
//                        } else if("create new EtlVarCell".equals(o)) {
//                            this.createVarCell(dtde.getLocation().x, dtde.getLocation().y);
//                        }
                    } else {
                        log.warn("unknown transfered object in DND:%s. ignored.", o.getClass().getName());
                    }
                    dtde.dropComplete(true);
                    return;
                }
            }
            dtde.dropComplete(false);
        } catch (Exception e) {
            log.error(com.bca.pub.tools.Toolfunc.getCallLocation(e.getStackTrace()) + ":" + e.getMessage(), e);
            try {
                dtde.dropComplete(false);
            } catch (Exception ex) {
            }
        } finally {
        }
    }
    
    private boolean createORM(Meta_TableElement te) {
        
        activeFactory.pojoAttr.alias = alias;
        activeFactory.pojoAttr.schema = te.getSchema();
        activeFactory.pojoAttr.tableName = te.getName();
        activeFactory.pojoAttr.dbObjType = te.getDbObjType();
        activeFactory.pojoAttr.className = Stringtool.getRecommendName(activeFactory.pojoAttr.tableName, Stringtool.RecommendNameType.ClassName);  // (pojoAttr.tabelName);
        activeFactory.pojoAttr.packageName = recentPackageName;
        
        BcaToolkit app = BcaToolkit.getApp();
        app.checkLocalMetaContainerReady();
        LocalMetaDataContainer m = app.getLocalMetaDataContainer();
//        StringBuffer errInfo = new StringBuffer();
        Meta_Table mtable = m.getTableDetailInfoFromDataConnect(activeFactory.pojoAttr.alias, activeFactory.pojoAttr.schema, activeFactory.pojoAttr.catalog, activeFactory.pojoAttr.tableName, true);
        if (mtable == null) {
            Wftool.messageDialogFmt("错误", JOptionPane.ERROR_MESSAGE, "查找表<%s.%s>元数据错误，不能建立ORM.", activeFactory.pojoAttr.schema, activeFactory.pojoAttr.tableName);
            return false;
        }
        activeFactory.pojoAttr.tableComment = mtable.getTableComment().replace('r', ' ').replace('\n', ' ');
        dbTable = mtable;
        pojoFields.clear();
        for (I_WfColumn col : dbTable.getColumns()) {
            PojoField f = new PojoField();
            f.col = col;
            f.varName = Stringtool.getRecommendName(col.getSqlName(), Stringtool.RecommendNameType.VarName);  // (pojoAttr.tabelName);
            String sqltypen = f.col.getSqlTypeName().toLowerCase();
            if (sqltypen.startsWith("number") || sqltypen.startsWith("decimal") || sqltypen.startsWith("int")) {
                f.varType = app.getBcaToolkitConfig().getDataWizConfig().getConvertedJavaType(col.getSqlName(), f.col.getSqlTypeName());
            }
            if (f.varType == null || f.varType.isEmpty()) {
                f.varType = col.getJavaVarType();
            }
            
            pojoFields.put(f.col.getSqlName(), f);
        }
        return true;
    }
    // 
    String recentPojoSavePath = Systool.getStartDir() + File.separator + "flowclasses" + File.separator + "po";
    boolean locateSaveDir = true;
    boolean abortSaveFlag = false;
    
    public void createSource() {
      
        if("树状表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "ExtendTable";
        }else if("分页表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "PagiTable";
        }else if("普通表格联动柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "TableBar";
        }else if("柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "Bar";
        }else if("饼图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "Pie";
        }else if("线型图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Line";
        }else if("增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "CRUD";
        }else if("散点图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Scatter";
        }else if("雷达图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Radar";
        }else if("仪表盘图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Gauge";
        }else if("后端分页增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "BackCRUD";
        }else{
            modelType = "PagiTable";
        }
        
        locateSaveDir = true;
        abortSaveFlag = false;

//        PojoSourceCreatorUnit pojoSourceUnit = new PojoSourceCreatorUnit("pojo");
//        pojoSourceUnit.setFileName(String.format("%s.java", pojoAttr.className));
//
//        PojoSourceCreatorUnit hbmSourceUnit = new PojoSourceCreatorUnit("hbm");
//        hbmSourceUnit.setFileName(String.format("%s.hbm.xml", pojoAttr.className));
//
//        Map<String, PojoSourceCreatorUnit> sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();
//        sourceUnits.put(pojoSourceUnit.getCreatorId(), pojoSourceUnit);
//        sourceUnits.put(hbmSourceUnit.getCreatorId(), hbmSourceUnit);
//        I_SqlCreator sqlCreator = sqlBuilderPanel.getSourceCreator();  // new PojoSourceCreator_4_Hibernate(sourceUnits);
        if (activeFactory == null || activeFactory.creator == null) {
            return;
        }
        
        if (sceneModel.getNodes().size() > 1) {
            createTables();
            //tablesCreator.init(this, activeFactory.pojoAttr, pojoFields, dbTable);
        }else{
            if (activeFactory.pojoAttr.getProperty("pkgHome").equals("")) {
            Wftool.messageDialog(false, "请点击工厂选项进行设置");
            return;
            }
            int i = 0;
            if("后端分页增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory()) && !this.isFieldMasked("ui", "delete")) {
                for(PojoField f : pojoFields.values()) {
                    if(!this.isFieldMasked("desc_0", f.col.getSqlName())) {
                        i++;
                    }
                }
                if(i == 0) {
                    Wftool.messageDialog(false, "删除功能已打开，请选择desc批量开关");
                    return;
                }
            }
            if("分页表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory()) && !this.isFieldMasked("ui", "delete")) {
                for(PojoField f : pojoFields.values()) {
                    if(!this.isFieldMasked("desc_0", f.col.getSqlName())) {
                        i++;
                    }
                }
                if(i == 0) {
                    Wftool.messageDialog(false, "请选择desc开关用来描述查询字段");
                    return;
                }
            }

            for (QbTableBean tb : sceneModel.getNodes().values()) {
                dbTable = tb.getTableDetail();
                create(activeFactory.creator, tb.getTableModel(), tb.getCnAlias());

            }
        }
        if(BcaTool_SqlBuilderTopForm.isIsDirectlyCopyFiles()){    //直接部署时文件列表须再次弹出
                BcaTool_SqlBuilderTopForm.setIsDirectlyCopyFiles(false);  //重置是否直接部署为false
                java.awt.EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                       if(flDialog!=null){
                            flDialog.getRunButton1().setEnabled(true);
                            flDialog.setVisible(true);   //重新打开窗口，供用户点击运行按钮打开默认访问页面
                            flDialog=null;
                       }
                    }
                });
            //对文件绝对路径和项目名称等信息进行增删改查操作
             List<String> projNameList = activeFactory.creator.projNameList();
             Set<FilesInfo> files=new HashSet<FilesInfo>();
             Timestamp createTime=new Timestamp(System.currentTimeMillis());
             for(String s:projNameList){
                     FilesInfo file=new FilesInfo(s, activeFactory.pojoAttr.getProperty("projHomeDir"), this.modelType, getUrl(), createTime);
                     files.add(file);
             }
             String SQL_query="select * from tiantiantest where filepath=? and projhome=? and modeltype=? and visiteurl=? ";
             String SQL_insert="insert into tiantiantest values(?, ?, ?, ?, ?) ";
             String SQL_update="update tiantiantest set filepath=?, projhome=?, modeltype=? , visiteurl=? , createtime=?";
             String[] SQLs={SQL_query, SQL_insert, SQL_update};
             manageUserInfo(SQLs, INSERT, files);              
             return;
            }else if(flDialog != null && flDialog.isIsDefaultClose()){   //非直接部署时有可能是用户点击文件列表窗体的取消按钮
                flDialog.setIsDefaultClose(false);                       //或者直接关闭文件列表窗体导致，此时应该终止程序，
                return;                                                 //因为这一操作表明用户中途不想生成代码了
            }
        



//        if (!abortSaveFlag) {
//            sourceUnits.clear();
//            PojoSourceCreatorUnit zxPojoJspUnit = new PojoSourceCreatorUnit("ZxPojoJsp");
//            zxPojoJspUnit.setFileName(String.format("ZX_%s.jsp", pojoAttr.className));
//            sourceUnits.put(zxPojoJspUnit.getCreatorId(), zxPojoJspUnit);
//
//            c = new PojoSourceCreator_4_ZxJspPage(sourceUnits);
//            create(c);
//        }
        if (!abortSaveFlag) {
                String s = "recentPojoSavePath + File.separator + \"src\" + File.separator + activeFactory.pojoAttr.className + modelType";
                Ostool.runExplorer(recentPojoSavePath + File.separator + "src" + File.separator + activeFactory.pojoAttr.className + modelType, log);
                BcaTool_SqlBuilderTopForm.setIsDirectlyCopyFiles(false);  //重置是否直接部署为false
        }else{
            BcaTool_SqlBuilderTopForm.setIsDirectlyCopyFiles(false);
        }
        //对文件绝对路径和项目名称等信息进行增删改查操作
        List<String> projNameList = activeFactory.creator.projNameList();
        Set<FilesInfo> files=new HashSet<FilesInfo>();
        Timestamp createTime=new Timestamp(System.currentTimeMillis());
        for(String s:projNameList){
        	FilesInfo file=new FilesInfo(s, activeFactory.pojoAttr.getProperty("projHomeDir"), this.modelType, getUrl(), createTime);
        	files.add(file);
        }
        String SQL_query="select * from tiantiantest where filepath=? and projhome=? and modeltype=? and visiteurl=? ";
        String SQL_insert="insert into tiantiantest values(?, ?, ?, ?, ?) ";
        String SQL_update="update tiantiantest set filepath=?, projhome=?, modeltype=?, visiteurl=?, createtime=? where filepath=? and projhome=? and modeltype=? and visiteurl=? ";
        String[] SQLs={SQL_query, SQL_insert, SQL_update};
        manageUserInfo(SQLs, INSERT, files);
    }
    private void createTables() {
        //activeFactory.pojoAttr.alias = cnAlias;  //别名
        activeFactory.pojoAttr.schema = dbTable.getSchema();    //模式
        activeFactory.pojoAttr.tableName = dbTable.getName();   //
        activeFactory.pojoAttr.dbObjType = dbTable.getTableElement().getDbObjType();

        //        pojoAttr.packageName = "com.hxhk.tss.cust";
        if (activeFactory.pojoAttr.packageName == null) {
            activeFactory.pojoAttr.packageName = "com.hxhk.tss.demo";
        }
        activeFactory.pojoAttr.className = Stringtool.getRecommendName(activeFactory.pojoAttr.tableName, Stringtool.RecommendNameType.ClassName);  // (pojoAttr.tabelName);
        activeFactory.pojoAttr.tableComment = dbTable.getTableComment();
        // 
        tablesCreator.init(this);
        //获取目标文件列表
        List<String> projNameList = tablesCreator.projNameList();
        Map<String,String> files = getFileExistsAndModify(projNameList);
        //判断是否直接部署文件到目标路径
        
        if(BcaTool_SqlBuilderTopForm.isIsDirectlyCopyFiles()) {
            flDialog = new FilesListDialog(files, null, true);
            flDialog.setVisible(true);
            if(!BcaTool_SqlBuilderTopForm.isIsDirectlyCopyFiles()) {
                return;
            }
        }
        
        Ret ret = tablesCreator.createAllPojoSource();
        if (ret.isRetSuccess()) {
            if (locateSaveDir && (!BcaTool_SqlBuilderTopForm.isIsDirectlyCopyFiles())) {
                Filetool.createPaths(recentPojoSavePath);
                JFileChooser fc = new JFileChooser(recentPojoSavePath);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //弹出保存窗口，让用户选择生成代码的保存路径，把生成的代码文件保存
                fc.showSaveDialog(BcaToolkit.getApp().getMainFrame());
                if (fc == null) {
                    abortSaveFlag = true;
                    return;
                }
                File f = fc.getSelectedFile();
                if (f == null) {
                    abortSaveFlag = true;
                    return;
                }
                recentPojoSavePath = f.getAbsolutePath();
                locateSaveDir = false;
            }
//            StringBuffer errInfo = new StringBuffer();

                String absolutePath= tablesCreator.getSaveDir(recentPojoSavePath);
                Filetool.createPaths(absolutePath);
          //  }
            //String[] subDirs = screator.getSubPaths();
            //for (String subdir : subDirs) {
           //     Filetool.createPaths(absolutePath + File.separator + subdir);
           // }
        //如果直接部署文件，则在此处生成文件
        if( getSqlBuilderPanel().isIsDirectlyCopyFiles()){
            if(flDialog != null){
                 filesToCreate = flDialog.getFilesAndStateMap();
                 for (PojoSourceCreatorUnit u : tablesCreator.getSourceUnits().values()) {
                    String wholeFileName = u.GetTargetPath() + File.separator + u.getFileName();
    //                String hbmWholeName = String.format("%s%s%s.hbm.xml", recentPojoSavePath, File.separator, pojoAttr.className);
                    // 如果不保存文件，则直接跳过。
                    if (!filesToCreate.containsKey(wholeFileName) || !filesToCreate.get(wholeFileName)) {
                        continue;
                    }
                    File file =  new File(wholeFileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    u.restoreSpecialSymbolText();
                    Filetool.createPaths(u.GetTargetPath());   //非常重要，必须先创建文件父目录，然后才能写入文件！！！
                    Filetool.saveStringToTextFile(wholeFileName, u.getCode(), u.getCharset().isEmpty() ? "GBK" : u.getCharset()); //  BStudioConfigBean.getInst().getCodeFactory_encoding());
    //                Filetool.saveStringToTextFile(hbmWholeName, hbmSource.toString(), errInfo);
                }
                Runtime run = Runtime.getRuntime();
                String path = activeFactory.pojoAttr.getProperty("projHomeDir") + File.separator + "src" + File.separator + "main" + File.separator + "frontend";
                String cmd =  "cmd /k start npm run build:views";
                Process p = null;
                try {
                    p = run.exec(cmd, null, new File(path));  
//                    final InputStream is1 = p.getInputStream();    

                } catch (Exception e) {
//                    try{   
//                  p.getErrorStream().close();   
//                  p.getInputStream().close();   
//                  p.getOutputStream().close();   
//                 }   
//               catch(Exception ee){}
                }
                setUrl("http://localhost:8080/microservice-ui/" + activeFactory.pojoAttr.className + this.modelType + "/" + activeFactory.pojoAttr.className + this.modelType);
                openURL(getUrl());
            }
        }else{  //如果不直接部署文件，在此处生成文件
            for (PojoSourceCreatorUnit u : tablesCreator.getSourceUnits().values()) {
                String wholeFileName = absolutePath + File.separator + u.getFileName();
//                String hbmWholeName = String.format("%s%s%s.hbm.xml", recentPojoSavePath, File.separator, pojoAttr.className);
                u.restoreSpecialSymbolText();
                Filetool.saveStringToTextFile(wholeFileName, u.getCode(), u.getCharset().isEmpty() ? "GBK" : u.getCharset()); //  BStudioConfigBean.getInst().getCodeFactory_encoding());
                setUrl("http://localhost:8080/microservice-ui/" + activeFactory.pojoAttr.className + this.modelType + "/" + activeFactory.pojoAttr.className + this.modelType);
//                Filetool.saveStringToTextFile(hbmWholeName, hbmSource.toString(), errInfo);
            }
        }
        
            tablesCreator.fixCompareScript(absolutePath);
        } else {
            Wftool.messageDialogFmt("错误", JOptionPane.ERROR_MESSAGE, "创建POJO代码<%s>失败：%s", tablesCreator.getClass().getName(), ret.toString());
        }
    }
    private void create(I_SqlCreator screator, QbTable_JTableModel jtm, String cnAlias) {
        activeFactory.pojoAttr.alias = cnAlias;  //别名
        activeFactory.pojoAttr.schema = dbTable.getSchema();    //模式
        activeFactory.pojoAttr.tableName = dbTable.getName();   //
        activeFactory.pojoAttr.dbObjType = dbTable.getTableElement().getDbObjType();

        //        pojoAttr.packageName = "com.hxhk.tss.cust";
        if (activeFactory.pojoAttr.packageName == null) {
            activeFactory.pojoAttr.packageName = "com.hxhk.tss.demo";
        }
        activeFactory.pojoAttr.className = Stringtool.getRecommendName(activeFactory.pojoAttr.tableName, Stringtool.RecommendNameType.ClassName);  // (pojoAttr.tabelName);
        activeFactory.pojoAttr.tableComment = dbTable.getTableComment();
        // 
        List<Map<String, String>> uiColSort = jtm.getColNames();
        screator.init(this, cnAlias, activeFactory.pojoAttr, pojoFields, dbTable, uiColSort);
        //获取目标文件列表
        List<String> projNameList = screator.projNameList();
        Map<String,String> files = getFileExistsAndModify(projNameList);
        //判断是否直接部署文件到目标路径
        
        if(BcaTool_SqlBuilderTopForm.isIsDirectlyCopyFiles()) {
            flDialog = new FilesListDialog(files, null, true);
            flDialog.setVisible(true);
            if(!BcaTool_SqlBuilderTopForm.isIsDirectlyCopyFiles()) {
                return;
            }
        }
        
        Ret ret = screator.createAllPojoSource();
        if (ret.isRetSuccess()) {
            if (locateSaveDir && (!BcaTool_SqlBuilderTopForm.isIsDirectlyCopyFiles())) {
                Filetool.createPaths(recentPojoSavePath);
                JFileChooser fc = new JFileChooser(recentPojoSavePath);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //弹出保存窗口，让用户选择生成代码的保存路径，把生成的代码文件保存
                fc.showSaveDialog(BcaToolkit.getApp().getMainFrame());
                if (fc == null) {
                    abortSaveFlag = true;
                    return;
                }
                File f = fc.getSelectedFile();
                if (f == null) {
                    abortSaveFlag = true;
                    return;
                }
                recentPojoSavePath = f.getAbsolutePath();
                locateSaveDir = false;
            }
//            StringBuffer errInfo = new StringBuffer();

                String absolutePath= screator.getSaveDir(recentPojoSavePath);
                Filetool.createPaths(absolutePath);
          //  }
            //String[] subDirs = screator.getSubPaths();
            //for (String subdir : subDirs) {
           //     Filetool.createPaths(absolutePath + File.separator + subdir);
           // }
        //如果直接部署文件，则在此处生成文件
        if( getSqlBuilderPanel().isIsDirectlyCopyFiles()){
            if(flDialog != null){
                 filesToCreate = flDialog.getFilesAndStateMap();
                 for (PojoSourceCreatorUnit u : screator.getSourceUnits().values()) {
                    String wholeFileName = u.GetTargetPath() + File.separator + u.getFileName();
    //                String hbmWholeName = String.format("%s%s%s.hbm.xml", recentPojoSavePath, File.separator, pojoAttr.className);
                    // 如果不保存文件，则直接跳过。
                    if (!filesToCreate.containsKey(wholeFileName) || !filesToCreate.get(wholeFileName)) {
                        continue;
                    }
                    File file =  new File(wholeFileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    u.restoreSpecialSymbolText();
                    Filetool.createPaths(u.GetTargetPath());   //非常重要，必须先创建文件父目录，然后才能写入文件！！！
                    Filetool.saveStringToTextFile(wholeFileName, u.getCode(), u.getCharset().isEmpty() ? "GBK" : u.getCharset()); //  BStudioConfigBean.getInst().getCodeFactory_encoding());
    //                Filetool.saveStringToTextFile(hbmWholeName, hbmSource.toString(), errInfo);
                }
                Runtime run = Runtime.getRuntime();
                String path = activeFactory.pojoAttr.getProperty("projHomeDir") + File.separator + "src" + File.separator + "main" + File.separator + "frontend";
                String cmd =  "cmd /k start npm run build:views";
                Process p = null;
                try {
                    p = run.exec(cmd, null, new File(path));  
//                    final InputStream is1 = p.getInputStream();    
//    //获取进城的错误流   
//    final InputStream is2 = p.getErrorStream();   
//    //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流   
//   new Thread() {   
//      public void run() {   
//         BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));   
//          try {   
//              String line1 = null;   
//              while ((line1 = br1.readLine()) != null) {   
//                    if (line1 != null){}   
//                }   
//          } catch (IOException e) {   
//               e.printStackTrace();   
//          }   
//          finally{   
//               try {   
//                 is1.close();   
//               } catch (IOException e) {   
//                  e.printStackTrace();   
//              }   
//            }   
//          }   
//       }.start();   
//                                   
//     new Thread() {    
//        public void  run() {    
//         BufferedReader br2 = new  BufferedReader(new  InputStreamReader(is2));    
//            try {    
//               String line2 = null ;    
//               while ((line2 = br2.readLine()) !=  null ) {    
//                    if (line2 != null){}   
//               }    
//             } catch (IOException e) {    
//                   e.printStackTrace();   
//             }    
//            finally{   
//               try {   
//                   is2.close();   
//               } catch (IOException e) {   
//                   e.printStackTrace();   
//               }   
//             }   
//          }    
//        }.start();     
//                                   
//        p.waitFor();   
//        p.destroy();    
//       System.out.println("我想被打印...");
                } catch (Exception e) {
//                    try{   
//                  p.getErrorStream().close();   
//                  p.getInputStream().close();   
//                  p.getOutputStream().close();   
//                 }   
//               catch(Exception ee){}
                }
                setUrl("http://localhost:8080/microservice-ui/" + activeFactory.pojoAttr.className + this.modelType + "/" + activeFactory.pojoAttr.className + this.modelType);
                openURL(getUrl());
            }
        }else{  //如果不直接部署文件，在此处生成文件
            for (PojoSourceCreatorUnit u : screator.getSourceUnits().values()) {
                String wholeFileName = absolutePath + File.separator + u.getFileName();
//                String hbmWholeName = String.format("%s%s%s.hbm.xml", recentPojoSavePath, File.separator, pojoAttr.className);
                u.restoreSpecialSymbolText();
                Filetool.saveStringToTextFile(wholeFileName, u.getCode(), u.getCharset().isEmpty() ? "GBK" : u.getCharset()); //  BStudioConfigBean.getInst().getCodeFactory_encoding());
                setUrl("http://localhost:8080/microservice-ui/" + activeFactory.pojoAttr.className + this.modelType + "/" + activeFactory.pojoAttr.className + this.modelType);
//                Filetool.saveStringToTextFile(hbmWholeName, hbmSource.toString(), errInfo);
            }
        }
        
            screator.fixCompareScript(absolutePath);
        } else {
            Wftool.messageDialogFmt("错误", JOptionPane.ERROR_MESSAGE, "创建POJO代码<%s>失败：%s", screator.getClass().getName(), ret.toString());
        }
    }
    //用系统默认的浏览器打开代码工厂创建的网址
    public static void openURL(String url) {
        try {
            browse(url);
        } catch (Exception e) {
        }
    }
    
    public static void browse(String url) throws Exception {
        String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Mac OS")) { 
             //苹果的打开方式 
             Class fileMgr = Class.forName("com.apple.eio.FileManager"); 
             Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class }); 
             openURL.invoke(null, new Object[] { url }); 
        } else if (osName.startsWith("Windows")) { 
             //windows的打开方式。
             Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url); 
        } else { 
             // Unix or Linux的打开方式 
             String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" }; 
             String browser = null; 
             for (int count = 0; count < browsers.length && browser == null; count++) 
                 //执行代码，在brower有值后跳出， 
                 //这里是如果进程创建成功了，==0是表示正常结束。 
                 if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0) 
                     browser = browsers[count]; 
                 if (browser == null) 
                     throw new Exception("Could not find web browser"); 
                 else
    //这个值在上面已经成功的得到了一个进程。 
                     Runtime.getRuntime().exec(new String[] { browser, url }); 
               } 
    }

    /**
     * @return the outputFields
     */
    public Map<Integer, QbOutputFieldUnit> getOutputFields() {
        return outputFields;
    }
    
    public synchronized void addFieldOut(QbTable_JTableModel tableModel, int row) {
        QbRightPanel.setSmodel(this); //把自身信息传递给SQL预览面板类的属性
        int index;
        if (outputFields.isEmpty()) {
            index = 0;
        } else {
            index = outputFields.lastKey();
        }
        index++;
        QbOutputFieldUnit f = new QbOutputFieldUnit();
        f.setIndex(index);
        f.setSourceType(1);
        f.setJtableModel(tableModel);
        f.setRow(row);
        // 
        I_WfColumn col = (I_WfColumn) tableModel.getValueAt(row, 0);
        f.setOutFieldName(col.getSqlName());
        //
        outputFields.put(index, f);
    }
    
    public String createSQL() {
//        I_SqlCreator sqlCreator = sqlBuilderPanel.getSourceCreator();  // new PojoSourceCreator_4_Hibernate(sourceUnits);
        if (activeFactory_SQL == null || activeFactory_SQL.creator == null) {
            return "(没有创建工具)";
        }
        QbTable_JTableModel jtm;
        String cnAlias = "";
        List<Map<String, String>> uiColSort = new ArrayList<Map<String, String>>();
        for (QbTableBean tb : sceneModel.getNodes().values()) {
            dbTable = tb.getTableDetail();
            cnAlias = tb.getCnAlias();
            jtm = tb.getTableModel();
            uiColSort = jtm.getColNames();
        }
        //初始化pojoAttr（非常重要）
        activeFactory.pojoAttr.alias = cnAlias;  //别名
        activeFactory.pojoAttr.schema = dbTable.getSchema();    //模式
        activeFactory.pojoAttr.tableName = dbTable.getName();   //
        activeFactory.pojoAttr.dbObjType = dbTable.getTableElement().getDbObjType();
        //初始化pojoFields（非常重要）
        pojoFields.clear();
        
        //        pojoAttr.packageName = "com.hxhk.tss.cust";
        if (activeFactory.pojoAttr.packageName == null) {
            activeFactory.pojoAttr.packageName = "com.hxhk.tss.demo";
        }
        activeFactory.pojoAttr.className = Stringtool.getRecommendName(activeFactory.pojoAttr.tableName, Stringtool.RecommendNameType.ClassName);  // (pojoAttr.tabelName);
        activeFactory.pojoAttr.tableComment = dbTable.getTableComment();
        this.activeFactory_SQL.creator.init(this, alias, activeFactory.pojoAttr, pojoFields, dbTable, uiColSort);
        String sql = activeFactory_SQL.creator.createSelectSQL(outputFields);

//        StringBuffer sql = new StringBuffer();
//        for (QbOutputFieldUnit of : outputFields.values()) {
//            sql.append(of.getOutFieldName()).append(',');
//        }
//        return sql.toString();
        return sql;
    }
    
    public void onTableRemoved(QbTableBean tbean) {
        Set<Integer> s = new HashSet<Integer>();
        for (QbOutputFieldUnit u : outputFields.values()) {
            //
            if (tbean.getTableDetail().getSqlName().equals(u.getJtableModel().getTbean().getTableDetail().getSqlName())) {
                s.add(u.getIndex());
            }
        }
        for (Integer index : s) {
            outputFields.remove(index);
        }
        
        this.uiStyles.clear();
    }

    /**
     * @return the sceneModel
     */
    public QbSceneModel getSceneModel() {
        return sceneModel;
    }

    /**
     * @param sceneModel the sceneModel to set
     */
    public void setSceneModel(QbSceneModel sceneModel) {
        this.sceneModel = sceneModel;
    }

//    /**
//     * @return the sqlCreator
//     */
//    public I_SqlCreator getSqlCreator() {
//        return sqlCreator;
//    }
//
//    /**
//     * @param sqlCreator the sqlCreator to set
//     */
//    public void setSqlCreator(I_SqlCreator sqlCreator) {
//        this.sqlCreator = sqlCreator;
//    }
    public String getJspPath() {
        String s = this.activeFactory.pojoAttr.getProperty("jspPath");
        return s == null ? "" : s;
    }

    /**
     * @param activeFactory the activeFactory to set
     */
    public void setActiveFactory(SrcFactoryBean activeFactory) {
        this.activeFactory = activeFactory;
    }
    
    public void maskField(String grp, String fieldName) {
        Set<String> list = getMaskedFieldList(grp);
        list.add(fieldName);
    }
//移除group变量
   public void removeGroup(String grp, String fieldName) {
        Set<String> list = groupFields.get(grp);
        if (list != null) {
            list.remove(fieldName);
        }
    }  
    
    public void removeFieldMask(String grp, String fieldName) {
        Set<String> list = maskedFields.get(grp);
        if (list != null) {
            list.remove(fieldName);
        }
    }
    //添加group变量
     public void addGroup(String grp, String fieldName) {
        Set<String> list = groupFields.get(grp);
        if(list==null){
            list=new HashSet<String>(); 
        }
        list.add(fieldName);
        groupFields.put(grp, list);
    }
    
    private synchronized Set<String> getMaskedFieldList(String grp) {
        Set<String> list = maskedFields.get(grp);
        if (list == null) {
            list = new HashSet<String>();
            maskedFields.put(grp, list);
        }
        return list;
    }
    //获取group变量列表
     private synchronized Set<String> getgroupFieldList(String grp) {
        Set<String> list = groupFields.get(grp);
        if (list == null) {
            list = new HashSet<String>();
            groupFields.put(grp, list);
        }
        return list;
    }
    public boolean isFieldMasked(String grp, String fieldName) {
        Set<String> list = maskedFields.get(grp);
        return list == null ? false : list.contains(fieldName);
    }
    //判断是否为group变量
    public boolean isGroupField(String grp, String fieldName) {
        Set<String> list = groupFields.get(grp);
        return list == null ? false : list.contains(fieldName);
    }
    
    public void removeAllFieldMasks() {
        maskedFields.clear();
    }
    //移除所有group变量
     public void removeAllGroupFields() {
        groupFields.clear();
    }
    
    public TableCellRenderer getCellRenderer_sw(I_WfColumn col) {
        activeFactory.creator.setSModel(this);
        return activeFactory.creator.getCellRenderer_sw(col);
    }
    
    public void maskAllFields(String grp) {
        Set<String> list = getMaskedFieldList(grp);
        for (I_WfColumn col : dbTable.getColumns()) {
            if (!list.contains(col.getSqlName())) {
                list.add(col.getSqlName());
            }
        }
    }
    
    public TableCellRenderer getCellRenderer_estyle(final I_WfColumn col) {
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                WebUiMemBean uiBean = uiStyles.get(col.getSqlName());
                JLabel j = new JLabel();
                j.setForeground(Color.BLUE);
                if (uiBean != null) {
                    j.setText(uiBean.getStyleName());
                }
                return j;
            }
        };
    }

    /**
     * 缺省：将所有字段打开，但是把exp imp的输出关闭。
     */
    public void resetFieldMasks() {
        maskedFields.clear();
        this.maskField("ui", "exp");
        this.maskField("ui", "imp");
        this.maskField("ui", "print");
    }

    /**
     * @return the compPerRow
     */
    public int getCompPerRow() {
        return compPerRow;
    }

    /**
     * @param compPerRow the compPerRow to set
     */
    public void setCompPerRow(int compPerRow) {
        this.compPerRow = compPerRow;
    }

    /**
     * @return the confirmReplaceAtDos
     */
    public boolean isConfirmReplaceAtDos() {
        return confirmReplaceAtDos;
    }

    /**
     * @param confirmReplaceAtDos the confirmReplaceAtDos to set
     */
    public void setConfirmReplaceAtDos(boolean confirmReplaceAtDos) {
        this.confirmReplaceAtDos = confirmReplaceAtDos;
    }
    
    public void loadFrom(SqlCreateModel orig) {
        this.alias = orig.alias;
        this.pojoFields.clear();
        this.pojoFields.putAll(orig.pojoFields);
        //  this.dbTable = orig.dbTable;   这个不能恢复。不然新的结构不能生效。
        this.maskedFields.clear();
        this.maskedFields.putAll(orig.maskedFields);
        //设置group变量集合的值
        this.groupFields.clear();
        this.groupFields.putAll(orig.groupFields);
        this.compPerRow = orig.compPerRow;
        this.confirmReplaceAtDos = orig.confirmReplaceAtDos;
//        this.colSortList = orig.colSortList;
        List<String> listNew = this.colSortList;
        this.colSortList = orig.colSortList;
        StringBuilder sb = new StringBuilder();
        //将表字段注释值赋给当前SqlCreateModel类
        this.sqlComment = orig.sqlComment;
        //将联动图形个数赋给当前SqlCreateModel类的picNum属性中,并传给QbTablePopDialog_BoncLink
        this.picNum = orig.picNum;
        QbTablePopDialog_BoncLink.PicNum = picNum;
        //setSqlComment();
        for (String f : listNew) {
            if (!colSortList.contains(f)) {
                colSortList.add(f);
                maskFieldX(f);
                sb.append(f).append(", ");
            }
        }
        //
        uiStyles.clear();
        this.uiStyles.putAll(orig.uiStyles);
        // 
        if (sb.length() != 0) {
            sb.setLength(sb.length() - 2);
            Wftool.messageDialog(true, "本次模型比原模型新增了字段，放在最后:" + sb.toString());
        }
    }

    /**
     * @return the colSortList
     */
    public List<String> getColSortList() {
        return colSortList;
    }

    /**
     * @param colSortList the colSortList to set
     */
    public void setColSortList(List<String> colSortList) {
        this.colSortList = colSortList;
    }
    
    public void saveUiStyle(String sqlName, WebUiMemBean uiBean) {
        uiStyles.put(sqlName, uiBean);
    }
    
    public void removeUiStyle(String sqlName) {
        uiStyles.remove(sqlName);
    }
    
    private void maskFieldX(String f) {
        maskField("bindingSeqCol", f);
        maskField("viewBindingCol", f);
        maskField("verify", f);
        maskField("imp", f);
        maskField("exp", f);
        maskField("view", f);
        maskField("update", f);
        maskField("add", f);
        maskField("listdev", f);
        maskField("criteria", f);
        maskField("select_"+QbTablePopDialog_BoncLink.selectNO, f);
        maskField("kpiId_"+QbTablePopDialog_BoncLink.selectNO, f);
        maskField("parentId_"+QbTablePopDialog_BoncLink.selectNO, f);
        removeGroup("group_"+QbTablePopDialog_BoncLink.selectNO, f);
        removeGroup("order_"+QbTablePopDialog_BoncLink.selectNO, f);
        removeGroup("where_"+QbTablePopDialog_BoncLink.selectNO, f);
        
    }
////保存表中注释一列的内容
    public void saveSqlComment() {
        QbTable_JTableModel qbJTable;
        for(QbTableBean tb : sceneModel.getNodes().values()){
            qbJTable= tb.getTableModel();
            sqlComment = qbJTable.getColNames();
        }
        
    }
//把表字段注释内容添加到表格
    public void setSqlComment() {
        QbTable_JTableModel qbJTable;
        for(QbTableBean tb : sceneModel.getNodes().values()){
            qbJTable= tb.getTableModel();
            qbJTable.setColNames(sqlComment);
        }
    }

    public void setPicNum(int PicNum) {
      this.picNum = PicNum;
    }
    public int setPicNum() {
      return picNum;
    }

    public BcaTool_SqlBuilderTopForm getSqlBuilderPanel() {
		return sqlBuilderPanel;
    }
     /**
     * 获取文件存在状态以及修改日期的集合
     * 
     * @param list 文件绝对路径集合
     * @return 文件存在状态以及修改日期的集合
     */
    private Map<String, String> getFileExistsAndModify(List<String> list) {
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        File file;
        for (String filePath : list) {
            file = new File(filePath);
            // 文件是否存在
            if (file.exists()) {
                cal.setTimeInMillis(file.lastModified());
                // 放入文件路径和修改日期
                map.put(filePath, sdf.format(cal.getTime()));
            } else {
                // 放入文件路径
                map.put(filePath, null);
            }
        }
        return map;
    }
//对文件绝对路径和项目名称等信息进行增删改查操作
    public Set<FilesInfo> manageUserInfo(String[] SQLs, String transactionType, Set<FilesInfo> files) {
        AbstractStudioApp app=AbstractStudioApp.getApp();
        String dataBaseAlias="bcaDatabase";
        
        Ret ret=new Ret();
        app.checkLocalMetaContainerReady();
        LocalMetaDataContainer mc = app.getLocalMetaDataContainer();
        MetaClientApi metaApi = mc.getMetaClientApi();
        Set<FilesInfo> set;
        set=metaApi.manageUserInfo(dataBaseAlias, SQLs, transactionType, files, ret);
        if(transactionType.equals("QUERY"))
            return set;
        else
            return null;
    }
}
