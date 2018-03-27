/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.estyle;

import com.bca.api.pub.Ret;
import com.bca.pub.gui.WfTreeNode;
import com.bca.pub.gui.WfTreeNode.TreeNodeType;
import com.bca.pub.tools.Filetool;
import com.bca.pub.tools.Layouttool;
import java.io.File;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author pxz
 */
public class CfEStypeTreeModel extends DefaultTreeModel {

    final WfTreeNode rootNode;
//    final WfTreeNode agentRegEditorNode = new WfTreeNode(TreeNodeType.Leaf, "代理部署");
//    
//    enum NodeType_onRuntime {ProcessDir, Process}

    /** Creates a new instance of BcaResTreeModel_onRuntime */
    public CfEStypeTreeModel() {
        super(new WfTreeNode(TreeNodeType.Branch, "BCA-CF编辑风格库"));
        rootNode = (WfTreeNode) getRoot();
//        
//        rootNode.add(agentRegEditorNode);
//        agentRegEditorNode.setClientProperty("Form", new BcaMmc_AgentDistForm());
        this.loadStypeLib("status" + File.separator + "bcacf.estyle");
    }

//    final Ret ret = new Ret();
//    boolean refresh(final int refreshTimes, final int interval_ms) {
//        Thread t = new Thread() {
//            public void run() {
//                for(int i = 0; i<refreshTimes; i++) {
//                    try {
//                        sleep(interval_ms);
//                    } catch (InterruptedException ex) {
//                    }
//                    try {
//                        agentRegEditorNode.removeAllChildren();
//                        WfProcessRegApi baseApi = BGlobal.getApp().getWfBaseClientApi();
//                        Map<Integer, WfProcessBean> processes = baseApi.getAllProcesses(ret);
//                        if(!ret.isRetSuccess()) {
//                            continue;
//                        }
//                        for(WfProcessBean p : processes.values()) {
//                            WfTreeNode n = new WfTreeNode(TreeNodeType.Leaf, p);
//                            n.setDisplayText(String.format("%s(%s)", p.getProcessName(), p.getIp()));
//                            n.setClientProperty("NodeType", NodeType_onRuntime.Process);
//                            
//                            agentRegEditorNode.add(n);
//                        }
//                    } finally {
//                        nodeStructureChanged(agentRegEditorNode);
//                    }
//                }
//            }
//        };
//        t.start();
//        return true;
//    }
//    
//    NodeType_onRuntime getNodeType(WfTreeNode tn) {
//        Object nt = tn.getClientProperty("NodeType");
//        return nt == null ? NodeType_onRuntime.ProcessDir : (NodeType_onRuntime)nt;
//    }
    private void loadStypeLib(String fn) {
        final Ret r = new Ret();
        CfEStyleBean f = (CfEStyleBean) Filetool.restoreObjFromSerialzeFile(fn, r);
        if (f != null) {
            this.rootNode.removeAllChildren();
            loadFolder(rootNode, f);
        }
    }

    private void loadFolder(WfTreeNode tn, CfEStyleBean f) {
        tn.setUserObject(f);
//        for (CfEStyleBean sf : f.getChildrenFolders()) {
//            WfTreeNode sn = new WfTreeNode(TreeNodeType.Branch, sf);
//            tn.add(sn);
//        }
        for (CfEStyleBean sf : f.getChildrenBeans()) {
            WfTreeNode sn = new WfTreeNode(TreeNodeType.Branch, sf);
            tn.add(sn);
        }
    }

    void newNode(TreePath parentPath) {
        WfTreeNode tn = (WfTreeNode) parentPath.getLastPathComponent();
        if (tn == null) {
            return;
        }
        CfStypeItemDialog dlg = new CfStypeItemDialog();
        Layouttool.setDlgCenterBounds(dlg);
        dlg.setVisible(true);
    }

    void delNode(TreePath tp) {
    }
}
