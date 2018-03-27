/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql;

/**
 *
 * @author pxz
 */
public interface I_SqlFactoryForm {

    void reloadSqlScene();

    I_SqlCreator getSourceCreator();

    void refreshSqlPreview(String sql);

    void refillSqlModel();

//    void loadLeftCenForm(QbLeftCenForm leftCenForm);
}
