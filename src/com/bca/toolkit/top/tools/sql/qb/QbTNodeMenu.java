/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package com.bca.toolkit.top.tools.sql.qb;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author alex
 */
public class QbTNodeMenu implements PopupMenuProvider, ActionListener {

    private static final String DELETE_NODE_ACTION = "deleteNodeAction"; // NOI18N
    private JPopupMenu menu;
    private Widget node;
//    private Point point;
    private QbScene scene;
    private static boolean isDeleteTable = false;
    private static QbRightPanel sqlPreviewPanel;
 
    public QbTNodeMenu(QbScene scene) {
        this.scene = scene;
        menu = new JPopupMenu("QbTNodeMenu");
        JMenuItem item;

        item = new JMenuItem("删除表(从查询中删除)");
        item.setActionCommand(DELETE_NODE_ACTION);
        item.addActionListener(this);
        menu.add(item);
    }

    @Override
    public JPopupMenu getPopupMenu(Widget widget, Point point) {
//        this.point=point;
        this.node = widget;
        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DELETE_NODE_ACTION)) {
            QbTableBean tbean = (QbTableBean) scene.findObject(node);
            if (tbean != null) {
                setIsDeleteTable(true);   //标识正在删除表格
                scene.removeNodeWithEdges(tbean);
                scene.onNodeRemoved(tbean);
                scene.validate();
                if(sqlPreviewPanel!=null)
                    sqlPreviewPanel.refreshSQLPreview();
               // scene.sqlBuilderPanel.refreshSqlPreview(false);   //清空SQL预览
                
            }
        }
    }

    /**
     * @return the isDeleteTable
     */
    public static boolean isIsDeleteTable() {
        return isDeleteTable;
    }

    /**
     * @param aIsDeleteTable the isDeleteTable to set
     */
    public static void setIsDeleteTable(boolean aIsDeleteTable) {
        isDeleteTable = aIsDeleteTable;
    }

    /**
     * @return the sqlPreviewPanel
     */
    public static QbRightPanel getSqlPreviewPanel() {
        return sqlPreviewPanel;
    }

    /**
     * @param aSqlPreviewPanel the sqlPreviewPanel to set
     */
    public static void setSqlPreviewPanel(QbRightPanel aSqlPreviewPanel) {
        sqlPreviewPanel = aSqlPreviewPanel;
    }
}
