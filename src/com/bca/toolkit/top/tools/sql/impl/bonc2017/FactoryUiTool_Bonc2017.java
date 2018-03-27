/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.bonc2017;

import com.bca.toolkit.top.tools.sql.I_FactoryUiTool;
import com.bca.toolkit.top.tools.sql.impl.util.Uimemtool_web;
import com.bca.toolkit.top.tools.sql.qb.QbLeftCenForm;

/**
 *
 * @author admin
 */
public class FactoryUiTool_Bonc2017 implements I_FactoryUiTool {

    @Override
    public void loadLeftCenForm(QbLeftCenForm inst) {
        Uimemtool_web.getInst().loadLeftCenForm(inst);
    }
}
