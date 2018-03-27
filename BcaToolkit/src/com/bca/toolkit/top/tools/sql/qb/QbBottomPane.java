
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.pub.gui.WideEditorPane;
import javax.swing.JScrollPane;

/**
 *
 * @author ur
 */
public class QbBottomPane extends JScrollPane{
    public static javax.swing.JEditorPane sqlEditPane;
    public QbBottomPane(){
        initComponents();
    }
    
    private void initComponents() {
        sqlEditPane = new WideEditorPane();
        sqlEditPane.setEditable(false);
        this.setViewportView(sqlEditPane);
    }
    //设置编辑窗格的可编辑性
    public static void setSqlEditableState(boolean editable){
            sqlEditPane.setEditable(editable);
    }
    //获取编辑窗格的内容
    public static String getSqlText(){
        return sqlEditPane.getText();
    }
    
    
}
