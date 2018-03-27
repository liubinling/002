/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.util;

import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemForm;
import com.bca.toolkit.top.tools.sql.qb.QbLeftCenForm;
import javax.swing.JComponent;

/**
 *
 * @author admin
 */
public class Uimemtool_web {

    private static Uimemtool_web inst;

    public synchronized static Uimemtool_web getInst() {
        if (inst == null) {
            inst = new Uimemtool_web();
        }
        return inst;
    }

    /**
     * װ������м�Ĵ��塣ͨ����QueryBuilder���������
     * @param leftcenForm 
     */
    public void loadLeftCenForm(QbLeftCenForm leftcenForm) {
        JComponent c = leftcenForm.getActiveComp();
        if(c instanceof WebUiMemForm) {
            return ;
        }
        c = new WebUiMemForm();
        leftcenForm.loadComp(c);
        leftcenForm.setTitle("ҳ��Ԫ��Ŀ¼");
    }
}
