/*
 * PojoCreateModel.java
 *
 * Created on 2007��8��3��, ����3:20
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
   //����
    public Creator_4_tables tablesCreator = new Creator_4_tables();
//    SrcFactoryBean activeFactory = null;
    public Map<String, PojoField> pojoFields = new LinkedHashMap<String, PojoField>();
    public Meta_Table dbTable;
    //���ڴ洢���ֶ�ע�͵�����
    private List<Map<String, String>> sqlComment;
    private int picNum;
    String modelType = new String();
    private static String url;
    /**
     * ����Ĵ����У��ֶ����α�ǡ� key: �ǹ������ͣ� listdiv, add update view exp imp ... List:
     * �ǸĹ��������ε��ֶΡ� ��������� �������� ��ȱʡ�������)
     */
    private List<String> colSortList = new ArrayList<String>();
    final Map<String, Set<String>> maskedFields = new HashMap<String, Set<String>>();
    final Map<String, WebUiMemBean> uiStyles = new HashMap<String, WebUiMemBean>();
    final Map<String, Set<String>> groupFields = new HashMap<String, Set<String>>();
    private int compPerRow = 2;   // ��¼����ϸ���У�ÿ�аڼ����ؼ���
    private boolean confirmReplaceAtDos = true;  // dos�ű��У�����ǰ�Ƿ���Ҫȷ�ϡ�
    //����SQLԤ������
    public String sqlPreview = "";
    private FilesListDialog flDialog = null;
    public final static String INSERT="INSERT", REMOVE="REMOVE", UPDATE="UPDATE", QUERY="QUERY";
    private Map<String,Boolean> filesToCreate;    //��Ҫ�������ļ�·������
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
                                    boolean b = Wftool.confirmDialog("С��", "���ڱ༭��ORMӳ�佫�����ǡ�ȷ����?");
                                    if (!b) {
                                        dtde.dropComplete(true);
                                        return;
                                    }
                                }
                                Meta_TableElement te = (Meta_TableElement) e;
//                                Point pt = dtde.getLocation();
//                                createTableCell(alias, te.getName(), DbObjType.Table, dtde.getLocation().x, dtde.getLocation().y);
                                if (com.bca.pub.cfg.SGlobal.debugLogEnable) {
                                    log.debug("�����־���:%s", te.getName());
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
                                log.error("TransferableArrayList����δ֧�ֵĶ������ͣ�%s", e.getClass());
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
            Wftool.messageDialogFmt("����", JOptionPane.ERROR_MESSAGE, "���ұ�<%s.%s>Ԫ���ݴ��󣬲��ܽ���ORM.", activeFactory.pojoAttr.schema, activeFactory.pojoAttr.tableName);
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
      
        if("��״���".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "ExtendTable";
        }else if("��ҳ���".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "PagiTable";
        }else if("��ͨ���������ͼ".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "TableBar";
        }else if("��ͼ".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "Bar";
        }else if("��ͼ".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "Pie";
        }else if("����ͼ".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Line";
        }else if("��ɾ�Ĳ���".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "CRUD";
        }else if("ɢ��ͼ".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Scatter";
        }else if("�״�ͼ".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Radar";
        }else if("�Ǳ���ͼ".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Gauge";
        }else if("��˷�ҳ��ɾ�Ĳ���".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
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
            Wftool.messageDialog(false, "��������ѡ���������");
            return;
            }
            int i = 0;
            if("��˷�ҳ��ɾ�Ĳ���".equals(Cf_BoncEpmUIOptionDialog.getModelCategory()) && !this.isFieldMasked("ui", "delete")) {
                for(PojoField f : pojoFields.values()) {
                    if(!this.isFieldMasked("desc_0", f.col.getSqlName())) {
                        i++;
                    }
                }
                if(i == 0) {
                    Wftool.messageDialog(false, "ɾ�������Ѵ򿪣���ѡ��desc��������");
                    return;
                }
            }
            if("��ҳ���".equals(Cf_BoncEpmUIOptionDialog.getModelCategory()) && !this.isFieldMasked("ui", "delete")) {
                for(PojoField f : pojoFields.values()) {
                    if(!this.isFieldMasked("desc_0", f.col.getSqlName())) {
                        i++;
                    }
                }
                if(i == 0) {
                    Wftool.messageDialog(false, "��ѡ��desc��������������ѯ�ֶ�");
                    return;
                }
            }

            for (QbTableBean tb : sceneModel.getNodes().values()) {
                dbTable = tb.getTableDetail();
                create(activeFactory.creator, tb.getTableModel(), tb.getCnAlias());

            }
        }
        if(BcaTool_SqlBuilderTopForm.isIsDirectlyCopyFiles()){    //ֱ�Ӳ���ʱ�ļ��б����ٴε���
                BcaTool_SqlBuilderTopForm.setIsDirectlyCopyFiles(false);  //�����Ƿ�ֱ�Ӳ���Ϊfalse
                java.awt.EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                       if(flDialog!=null){
                            flDialog.getRunButton1().setEnabled(true);
                            flDialog.setVisible(true);   //���´򿪴��ڣ����û�������а�ť��Ĭ�Ϸ���ҳ��
                            flDialog=null;
                       }
                    }
                });
            //���ļ�����·������Ŀ���Ƶ���Ϣ������ɾ�Ĳ����
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
            }else if(flDialog != null && flDialog.isIsDefaultClose()){   //��ֱ�Ӳ���ʱ�п������û�����ļ��б����ȡ����ť
                flDialog.setIsDefaultClose(false);                       //����ֱ�ӹر��ļ��б��嵼�£���ʱӦ����ֹ����
                return;                                                 //��Ϊ��һ���������û���;�������ɴ�����
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
                BcaTool_SqlBuilderTopForm.setIsDirectlyCopyFiles(false);  //�����Ƿ�ֱ�Ӳ���Ϊfalse
        }else{
            BcaTool_SqlBuilderTopForm.setIsDirectlyCopyFiles(false);
        }
        //���ļ�����·������Ŀ���Ƶ���Ϣ������ɾ�Ĳ����
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
        //activeFactory.pojoAttr.alias = cnAlias;  //����
        activeFactory.pojoAttr.schema = dbTable.getSchema();    //ģʽ
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
        //��ȡĿ���ļ��б�
        List<String> projNameList = tablesCreator.projNameList();
        Map<String,String> files = getFileExistsAndModify(projNameList);
        //�ж��Ƿ�ֱ�Ӳ����ļ���Ŀ��·��
        
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
                //�������洰�ڣ����û�ѡ�����ɴ���ı���·���������ɵĴ����ļ�����
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
        //���ֱ�Ӳ����ļ������ڴ˴������ļ�
        if( getSqlBuilderPanel().isIsDirectlyCopyFiles()){
            if(flDialog != null){
                 filesToCreate = flDialog.getFilesAndStateMap();
                 for (PojoSourceCreatorUnit u : tablesCreator.getSourceUnits().values()) {
                    String wholeFileName = u.GetTargetPath() + File.separator + u.getFileName();
    //                String hbmWholeName = String.format("%s%s%s.hbm.xml", recentPojoSavePath, File.separator, pojoAttr.className);
                    // ����������ļ�����ֱ��������
                    if (!filesToCreate.containsKey(wholeFileName) || !filesToCreate.get(wholeFileName)) {
                        continue;
                    }
                    File file =  new File(wholeFileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    u.restoreSpecialSymbolText();
                    Filetool.createPaths(u.GetTargetPath());   //�ǳ���Ҫ�������ȴ����ļ���Ŀ¼��Ȼ�����д���ļ�������
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
        }else{  //�����ֱ�Ӳ����ļ����ڴ˴������ļ�
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
            Wftool.messageDialogFmt("����", JOptionPane.ERROR_MESSAGE, "����POJO����<%s>ʧ�ܣ�%s", tablesCreator.getClass().getName(), ret.toString());
        }
    }
    private void create(I_SqlCreator screator, QbTable_JTableModel jtm, String cnAlias) {
        activeFactory.pojoAttr.alias = cnAlias;  //����
        activeFactory.pojoAttr.schema = dbTable.getSchema();    //ģʽ
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
        //��ȡĿ���ļ��б�
        List<String> projNameList = screator.projNameList();
        Map<String,String> files = getFileExistsAndModify(projNameList);
        //�ж��Ƿ�ֱ�Ӳ����ļ���Ŀ��·��
        
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
                //�������洰�ڣ����û�ѡ�����ɴ���ı���·���������ɵĴ����ļ�����
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
        //���ֱ�Ӳ����ļ������ڴ˴������ļ�
        if( getSqlBuilderPanel().isIsDirectlyCopyFiles()){
            if(flDialog != null){
                 filesToCreate = flDialog.getFilesAndStateMap();
                 for (PojoSourceCreatorUnit u : screator.getSourceUnits().values()) {
                    String wholeFileName = u.GetTargetPath() + File.separator + u.getFileName();
    //                String hbmWholeName = String.format("%s%s%s.hbm.xml", recentPojoSavePath, File.separator, pojoAttr.className);
                    // ����������ļ�����ֱ��������
                    if (!filesToCreate.containsKey(wholeFileName) || !filesToCreate.get(wholeFileName)) {
                        continue;
                    }
                    File file =  new File(wholeFileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    u.restoreSpecialSymbolText();
                    Filetool.createPaths(u.GetTargetPath());   //�ǳ���Ҫ�������ȴ����ļ���Ŀ¼��Ȼ�����д���ļ�������
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
//    //��ȡ���ǵĴ�����   
//    final InputStream is2 = p.getErrorStream();   
//    //���������̣߳�һ���̸߳������׼���������һ���������׼������   
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
//       System.out.println("���뱻��ӡ...");
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
        }else{  //�����ֱ�Ӳ����ļ����ڴ˴������ļ�
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
            Wftool.messageDialogFmt("����", JOptionPane.ERROR_MESSAGE, "����POJO����<%s>ʧ�ܣ�%s", screator.getClass().getName(), ret.toString());
        }
    }
    //��ϵͳĬ�ϵ�������򿪴��빤����������ַ
    public static void openURL(String url) {
        try {
            browse(url);
        } catch (Exception e) {
        }
    }
    
    public static void browse(String url) throws Exception {
        String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Mac OS")) { 
             //ƻ���Ĵ򿪷�ʽ 
             Class fileMgr = Class.forName("com.apple.eio.FileManager"); 
             Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class }); 
             openURL.invoke(null, new Object[] { url }); 
        } else if (osName.startsWith("Windows")) { 
             //windows�Ĵ򿪷�ʽ��
             Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url); 
        } else { 
             // Unix or Linux�Ĵ򿪷�ʽ 
             String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" }; 
             String browser = null; 
             for (int count = 0; count < browsers.length && browser == null; count++) 
                 //ִ�д��룬��brower��ֵ�������� 
                 //������������̴����ɹ��ˣ�==0�Ǳ�ʾ���������� 
                 if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0) 
                     browser = browsers[count]; 
                 if (browser == null) 
                     throw new Exception("Could not find web browser"); 
                 else
    //���ֵ�������Ѿ��ɹ��ĵõ���һ�����̡� 
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
        QbRightPanel.setSmodel(this); //��������Ϣ���ݸ�SQLԤ������������
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
            return "(û�д�������)";
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
        //��ʼ��pojoAttr���ǳ���Ҫ��
        activeFactory.pojoAttr.alias = cnAlias;  //����
        activeFactory.pojoAttr.schema = dbTable.getSchema();    //ģʽ
        activeFactory.pojoAttr.tableName = dbTable.getName();   //
        activeFactory.pojoAttr.dbObjType = dbTable.getTableElement().getDbObjType();
        //��ʼ��pojoFields���ǳ���Ҫ��
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
//�Ƴ�group����
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
    //���group����
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
    //��ȡgroup�����б�
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
    //�ж��Ƿ�Ϊgroup����
    public boolean isGroupField(String grp, String fieldName) {
        Set<String> list = groupFields.get(grp);
        return list == null ? false : list.contains(fieldName);
    }
    
    public void removeAllFieldMasks() {
        maskedFields.clear();
    }
    //�Ƴ�����group����
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
     * ȱʡ���������ֶδ򿪣����ǰ�exp imp������رա�
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
        //  this.dbTable = orig.dbTable;   ������ָܻ�����Ȼ�µĽṹ������Ч��
        this.maskedFields.clear();
        this.maskedFields.putAll(orig.maskedFields);
        //����group�������ϵ�ֵ
        this.groupFields.clear();
        this.groupFields.putAll(orig.groupFields);
        this.compPerRow = orig.compPerRow;
        this.confirmReplaceAtDos = orig.confirmReplaceAtDos;
//        this.colSortList = orig.colSortList;
        List<String> listNew = this.colSortList;
        this.colSortList = orig.colSortList;
        StringBuilder sb = new StringBuilder();
        //�����ֶ�ע��ֵ������ǰSqlCreateModel��
        this.sqlComment = orig.sqlComment;
        //������ͼ�θ���������ǰSqlCreateModel���picNum������,������QbTablePopDialog_BoncLink
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
            Wftool.messageDialog(true, "����ģ�ͱ�ԭģ���������ֶΣ��������:" + sb.toString());
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
////�������ע��һ�е�����
    public void saveSqlComment() {
        QbTable_JTableModel qbJTable;
        for(QbTableBean tb : sceneModel.getNodes().values()){
            qbJTable= tb.getTableModel();
            sqlComment = qbJTable.getColNames();
        }
        
    }
//�ѱ��ֶ�ע��������ӵ����
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
     * ��ȡ�ļ�����״̬�Լ��޸����ڵļ���
     * 
     * @param list �ļ�����·������
     * @return �ļ�����״̬�Լ��޸����ڵļ���
     */
    private Map<String, String> getFileExistsAndModify(List<String> list) {
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        File file;
        for (String filePath : list) {
            file = new File(filePath);
            // �ļ��Ƿ����
            if (file.exists()) {
                cal.setTimeInMillis(file.lastModified());
                // �����ļ�·�����޸�����
                map.put(filePath, sdf.format(cal.getTime()));
            } else {
                // �����ļ�·��
                map.put(filePath, null);
            }
        }
        return map;
    }
//���ļ�����·������Ŀ���Ƶ���Ϣ������ɾ�Ĳ����
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
