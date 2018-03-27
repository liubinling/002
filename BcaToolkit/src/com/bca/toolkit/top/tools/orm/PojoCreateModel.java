/*
 * PojoCreateModel.java
 *
 * Created on 2007年8月3日, 下午3:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.orm;

import com.bca.api.pub.Ret;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_AbstractTableElement;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.db.meta.unit.Meta_TableElement;
import com.bca.kernel.LocalMetaDataContainer;
import java.util.LinkedHashMap;
import com.bca.pub.gui.util.TransferableArrayList;
import com.bca.pub.tools.Filetool;
import com.bca.pub.tools.Ostool;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Systool;
import com.bca.pub.tools.Wftool;
import com.bca.studio.BStudioConfigBean;
import com.bca.toolkit.app.BcaToolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author pxz
 */
public class PojoCreateModel implements DropTargetListener {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaTool_PojoCreatePanel pojoCreatePanel;
    public String alias;

    /** Creates a new instance of PojoCreateModel */
    public PojoCreateModel(BcaTool_PojoCreatePanel pojoCreatePanel) {
        this.pojoCreatePanel = pojoCreatePanel;
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
                        String aliasx = list.getProperties().getProperty("alias");

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
                                    pojoCreatePanel.reloadORM();
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
            e.printStackTrace();
            log.error(e);
            try {
                dtde.dropComplete(false);
            } catch (Exception ex) {
            }
        } finally {
        }
    }

    public String recentPackageName = "pxz.demo.pojo";
    public PojoAttribute pojoAttr;
    public Map<String, PojoField> pojoFields = new LinkedHashMap<String, PojoField>();
    public Meta_Table dbTable;

    private boolean createORM(Meta_TableElement te) {
        pojoAttr = new PojoAttribute();
        pojoAttr.alias = alias;
        pojoAttr.schema = te.getSchema();
        pojoAttr.tableName = te.getName();
        pojoAttr.className = Stringtool.getRecommendName(pojoAttr.tableName, Stringtool.RecommendNameType.ClassName);  // (pojoAttr.tabelName);
        pojoAttr.packageName = recentPackageName;

        BcaToolkit app = BcaToolkit.getApp();
        app.checkLocalMetaContainerReady();
        LocalMetaDataContainer m = app.getLocalMetaDataContainer();
//        StringBuffer errInfo = new StringBuffer();
        Meta_Table mtable = m.getTableDetailInfoFromDataConnect(pojoAttr.alias, pojoAttr.schema, pojoAttr.catalog, pojoAttr.tableName, true);
        if (mtable == null) {
            Wftool.messageDialogFmt("错误", JOptionPane.ERROR_MESSAGE, "查找表<%s.%s>元数据错误，不能建立ORM.", pojoAttr.schema, pojoAttr.tableName);
            return false;
        }
        pojoAttr.tableComment = mtable.getTableComment().replace('r', ' ').replace('\n', ' ');
        dbTable = mtable;
        pojoFields.clear();
        for (I_WfColumn col : dbTable.getColumns()) {
            PojoField f = new PojoField();
            f.col = col;
            f.varName = Stringtool.getRecommendName(col.getSqlName(), Stringtool.RecommendNameType.VarName);  // (pojoAttr.tabelName);
            f.varType = app.getBcaToolkitConfig().getDataWizConfig().getConvertedJavaType(col.getSqlName(), f.col.getSqlTypeName());
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
        I_PojoSourceCreator c = pojoCreatePanel.getSourceCreator();  // new PojoSourceCreator_4_Hibernate(sourceUnits);
        if (c == null) {
            return;
        }
        create(c);

//        if (!abortSaveFlag) {
//            sourceUnits.clear();
//            PojoSourceCreatorUnit zxPojoJspUnit = new PojoSourceCreatorUnit("ZxPojoJsp");
//            zxPojoJspUnit.setFileName(String.format("ZX_%s.jsp", pojoAttr.className));
//            sourceUnits.put(zxPojoJspUnit.getCreatorId(), zxPojoJspUnit);
//
//            c = new PojoSourceCreator_4_ZxJspPage(sourceUnits);
//            create(c);
//        }

        Ostool.runExplorer(recentPojoSavePath, log);
    }

    private void create(I_PojoSourceCreator screator) {
        screator.init(alias, pojoAttr, pojoFields, dbTable);
//        Ret ret = new Ret();
//        final StringBuffer pojoSource = new StringBuffer();
//        final StringBuffer hbmSource = new StringBuffer();


        Ret ret = screator.createAllPojoSource();
        if (ret.isRetSuccess()) {
            if (locateSaveDir) {
                Filetool.createPaths(recentPojoSavePath);
                JFileChooser fc = new JFileChooser(recentPojoSavePath);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showSaveDialog(BcaToolkit .getApp().getMainFrame());
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
            for (PojoSourceCreatorUnit u : screator.getSourceUnits().values()) {
                String absolutePath = screator.getSaveDir(recentPojoSavePath);
                Filetool.createPaths(absolutePath);
                String wholeFileName = absolutePath + File.separator + u.getFileName();
//                String hbmWholeName = String.format("%s%s%s.hbm.xml", recentPojoSavePath, File.separator, pojoAttr.className);
                Filetool.saveStringToTextFile(wholeFileName, u.getCode(), u.getCharset());  //  BStudioConfigBean.getInst().getCodeFactory_encoding());
//                Filetool.saveStringToTextFile(hbmWholeName, hbmSource.toString(), errInfo);

            }
        } else {
            Wftool.messageDialogFmt("错误", JOptionPane.ERROR_MESSAGE, "创建POJO代码<%s>失败：%s", screator.getClass().getName(), ret.toString());
        }
    }
}
