
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
    //���ñ༭����Ŀɱ༭��
    public static void setSqlEditableState(boolean editable){
            sqlEditPane.setEditable(editable);
    }
    //��ȡ�༭���������
    public static String getSqlText(){
        return sqlEditPane.getText();
    }
    
    
}
