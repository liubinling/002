/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservice_framework.baseFrames;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import microservice_framework.newboncproject.SettingsDialog_1;

/**
 *
 * @author ur
 */
public abstract class BaseDialog extends JFrame{

    /**
     * @return the SCREENWIDTH
     */
    public static int getSCREENWIDTH() {
        return SCREENWIDTH;
    }

    /**
     * @return the FWIDTH
     */
    public static int getFWIDTH() {
        return FWIDTH;
    }

    /**
     * @param aFWIDTH the FWIDTH to set
     */
    public static void setFWIDTH(int aFWIDTH) {
        FWIDTH = aFWIDTH;
    }

    /**
     * @return the SCREENHEIGHT
     */
    public static int getSCREENHEIGHT() {
        return SCREENHEIGHT;
    }
    //电脑屏幕的宽和高
    private static final int SCREENWIDTH=1600;
    private static final int SCREENHEIGHT=900;
    private static int FWIDTH=getSCREENWIDTH()/3;
    private static int FHEIGHT=getSCREENHEIGHT();
    private static final int LOCX=getSCREENWIDTH()/3; 
    private static final int LOCY=getSCREENHEIGHT()/20;
    public static boolean isQualified=false;
    public void changeSwingLooksAndFeels(){
        try {
                setFHEIGHT((int) (getFHEIGHT() / 1.2));
                setFWIDTH((int)(getFWIDTH()));
            setSize(getFWIDTH(), getFHEIGHT());
            setLocation(getLOCX(), getLOCY());
            //改为Windows风格
            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[3].getClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SettingsDialog_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SettingsDialog_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SettingsDialog_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SettingsDialog_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(this);
        //修改完毕
        //设置页面标题和图标
        URL url=this.getClass().getResource("/microservice_framework/images/rainDrops.jpg");
        Image image = Toolkit.getDefaultToolkit().getImage(url).getScaledInstance(20, -1, Image.SCALE_DEFAULT);
        setIconImage(image);
        setTitle("New MicroService Project");
    }

    /**
     * @return the FHEIGHT
     */
    public static int getFHEIGHT() {
        return FHEIGHT;
    }

    /**
     * @param aFHEIGHT the FHEIGHT to set
     */
    public static void setFHEIGHT(int aFHEIGHT) {
        FHEIGHT = aFHEIGHT;
    }

    /**
     * @return the LOCX
     */
    public static int getLOCX() {
        return LOCX;
    }

    /**
     * @return the LOCY
     */
    public static int getLOCY() {
        return LOCY;
    }
}
