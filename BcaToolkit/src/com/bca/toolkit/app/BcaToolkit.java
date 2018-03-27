/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.app;

import com.bca.agent.dagent.log.po.WfLogBproc;
import com.bca.agent.dagent.log.po.WfLogBprocPara;
import com.bca.agent.timer.log.po.WfLogSchedule;
import com.bca.api.local.BweClientApi;
import com.bca.api.local.DClientApi;
import com.bca.api.local.MClientApi;
import com.bca.api.local.MetaClientApi;
import com.bca.api.local.PersistClientApi;
import com.bca.api.local.WfBaseClientApi;
import com.bca.api.pub.Ret;
import com.bca.deploy.po.CfgBasicSetting;
import com.bca.kernel.LocalDeployContainer;
import com.bca.kernel.LocalMetaDataContainer;
import com.bca.kernel.LocalResContainer;
import com.bca.kernel.po.WfActRetValuesReg;
import com.bca.kernel.po.WfConnectionStyleReg;
import com.bca.kernel.po.WfFlowBuildRt;
import com.bca.kernel.po.WfFlowRegRt;
import com.bca.kernel.templ.WfActPropReg;
import com.bca.kernel.templ.WfEditStyle;
import com.bca.kernel.util.GeorageRegister;
import com.bca.pub.blog.po.WfLogNodePrepare;
import com.bca.pub.cfg.SGlobal;
import com.bca.pub.cl.ClassPool;
import com.bca.pub.gui.auth.BcaLoginPanel;
import com.bca.pub.pif.I_BcaPassCoder;
import com.bca.pub.tools.I_ProgressAct;
import com.bca.pub.tools.Layouttool;
import com.bca.pub.tools.ProgressbarWindow;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Toolfunc;
import com.bca.pub.tools.Wftool;
import com.bca.service.meta.hibernate.HibDataConnect;
import com.bca.studio.tools.BstudioGlobal;
import com.bca.templ.po.TRouteDiaglamReg;
import com.bca.templ.po.TRouteDir;
import com.bca.templ.po.TlArgsReg;
import com.bca.templ.po.TlDomainReg;
import com.bca.templ.po.TlFolder;
import com.bca.templ.po.TlTemplateReg;
import com.bca.templ.po.TlTemplateTextReg;
import com.bca.templ.po.TlTemplateTextSave;
import com.bca.templ.pub.AbstractStudioApp;
import com.bca.templ.pub.I_StudioConfig;
import com.bca.tools.log.Log;
import java.awt.Rectangle;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import com.bca.pub.wfconst.Const.WfIconType;
import com.bca.studio.app.BStudioLoginService;
import com.bca.toolkit.BcaToolkitConfig;
import com.bca.toolkit.BcaToolkitConfigBean;
import com.bca.toolkit.BcaToolkitFrame;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import org.jdesktop.swingx.JXLoginPanel.Status;

/**
 *
 * @author pxz
 */
public class BcaToolkit extends AbstractStudioApp {

    /**
     * @return the isPluginFlag
     */
    public boolean isIsPluginFlag() {
        return isPluginFlag;
    }

    /**
     * @param isPluginFlag the isPluginFlag to set
     */
    public static void setIsPluginFlag(boolean isPluginFlag) {
        BcaToolkit.isPluginFlag = isPluginFlag;
    }

//    final static com.bca.log.LogTextPane logTextPane = com.bca.log.LogTextPane.getLogTextPane();
    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog("BcaToolkit", "BcaToolkit-log.properties");
//    static public AppVersion ver; // 
    public final BcaToolkitConfig cfg = BcaToolkitConfig.getConfig();
    private final BcaToolkitFrame mainFrame; // = new BcaToolkitFrame(this);
    private WfBaseClientApi wfBaseClientApi;
    private BweClientApi bweClientApi;
    private DClientApi dclientApi;
    private MClientApi mClientApi;
//    private ShellClientApi shellClientApi;
    public LocalResContainer localResContainer;
    public LocalDeployContainer localDeployContainer;
    public LocalMetaDataContainer localMetaDataContainer = LocalMetaDataContainer.getLocalMetaDataContainer();
    final ClassPool classPool = ClassPool.getClassPool();
    private ProgressbarWindow bar;

    private static boolean isPluginFlag = false;   // Eclipse plugin flag
    private static boolean isCancel = false;  //indicate if the login window has been closed by the user

    /**
     * Creates a new instance of BcaToolkit
     */
    // public BcaToolkit() {
    private BcaToolkit(String[] args) {
    	setIsPluginFlag(true);
        BcaToolkit.isCancel = false;
        log.setStackLogLevel(pubcfg.getLogLevelsForStackPrinting());
        parseBStudioArgs(args);

        I_ProgressAct cancelAct = new I_ProgressAct() {
            @Override
            public void cancelProgress() {
                System.exit(0);
            }
        };

        bar = new ProgressbarWindow("启动bca-toolkit....", cancelAct);
        bar.setMaximum(12);
        bar.setVisible(true); // 显示窗口
        bar.setAlwaysOnTop(true);

        super.sinit();   // 这句在super之后， log初始化之前

        mainFrame = new BcaToolkitFrame(this);
        mainFrame.setTitle("BCA Toolkit"); // Main Frame
        // mainFrame.getContentPane().add( new
        // NCanvasForm(),"mainCanvasDockingPanel" ); //Add Docking Panel to
        // mainFrame in card way
        // mainFrame.showCard("mainCanvasDockingPanel"); //Show
        // mainCanvasDockingPanel in mainFrame
        bar.incValue();
        mainFrame.wfinit(bar);

        this.mainLoad(new StringBuffer());
    }

    public static BcaToolkit getApp() {
        return (BcaToolkit) app;
    }

    public static void main(final String[] args) throws UnsupportedLookAndFeelException {

//        if (false) {   // splash 好像没搞定。先跳过
//            //-- 启动程序时的闪屏界面 begin -----------------------------------------
//            try {
//                //初始化splash
//                initSplash();
//            } catch (Exception ex) {
//                log.error(com.bca.pub.tools.Toolfunc.getCallLocation(ex.getStackTrace()) + ":" + ex.getMessage(), ex);
//            }
//            //在下面你初始化程序的过程中调用updateSplash来更新splash
//
//            final String[] stages = {"加载A模块", "初始化B模块", "配置C模块"};
//            int stage = 0;
//            for (int i = 0; i <= 100; i += 5) {
//                String status = "Loading " + stages[stage] + "...";
//                if (splash != null) {
//                    updateSplash(status, i);
//                    splash.update();
//                }
//                try {
//                    Thread.sleep(500);
//                    if (i == 30) {
//                        stage = 1;
//                    } else if (i == 60) {
//                        stage = 2;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
////        //在这儿你可以主动关闭splash
////        if (splash != null) {
////            splash.close();
////        }
//        //-- 启动程序时的闪屏界面 end -------------------------------------------
//        }
//        UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
//        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SGlobal.parseConfigArgs(args);
                final BcaToolkit appx = new BcaToolkit(args);

                BcaToolkit.log.info("BCA-toolkit start.");

//                Timetool.logSysTime(Toolfunc.getCurrentFunctionName());
//                appSplash();
//                appx.init();
//                Timetool.logSysTime(Toolfunc.getCurrentFunctionName());
//                Wftool l;
                appx.mainFrame.setIconImage(com.bca.pub.tools.Wftool.getImage("BcaToolkit.gif", WfIconType.Util));
                appx.mainFrame.setBounds(Layouttool.getScreenBounds()); // .getCenterBounds(800, 600));
                if(!BcaToolkit.isCancel){
                    appx.mainFrame.setVisible(true);
                    appx.mainFrame.toFront();   //使打开的代码工厂主页面显示在最前面
                }
                appx.getBar().close();
            }
//            private void appSplash() {
//                if (true) {
//                    System.out.println("Splash装载被跳过。");
//                    return;
//                }
//                final SplashScreen splash = SplashScreen.getSplashScreen();
//                if (splash == null) {
//                    System.out.println("SplashScreen.getSplashScreen() returned null");
//                    return;
//                }
//                Graphics2D g = (Graphics2D) splash.createGraphics();
//                if (g == null) {
//                    System.out.println("g is null");
//                    return;
//                }
//                for (int i = 0; i < 100; i++) {
//                    renderSplashFrame(g, i);
//                    splash.update();
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                    }
//                }
//                splash.close();
//            }
//
//            void renderSplashFrame(Graphics2D g, int frame) {
//                final String[] comps = {"foo", "bar", "baz"};
//                g.setComposite(AlphaComposite.Clear);
//                g.fillRect(130, 250, 280, 40);
//                g.setPaintMode();
//                g.setColor(Color.BLACK);
//                g.drawString("Loading " + comps[(frame / 5) % 3] + "...", 130, 260);
//                g.fillRect(130, 270, (frame * 10) % 280, 20);
//            }
        });
    }

    @Override
    public boolean checkBweClientReady() {
        if (bweClientApi == null) {
            bweClientApi = new BweClientApi();
        }
        if (bweClientApi.isReady() == false) {
            bweClientApi.connectServer();
        }
//        Nbtool_bstudio.updateMainTitle();
        return bweClientApi.isReady();
    }

    @Override
    public boolean checkDClientReady() {
        if (dclientApi == null) {
            dclientApi = new DClientApi();
        }
        if (dclientApi.isReady() == false) {
            dclientApi.connectServer();
        }
        return dclientApi.isReady();
    }

    // protected BcaCmsClientApi cmsApi = null;
    //
    // public WfFlowRegRt getWorkingWfFlowReg() {
    // return null;
    // }
    //    
    // public WfFlowBuildRt getWorkingWfFlowBuild() {
    // return null;
    // }
    //    
    // public AbstractConfig_classic getBcaToolkitConfig() {
    // return null;
    // }
    public boolean checkLocalContainer() {
        checkWfBaseConnection(false);
        if (!wfBaseClientApi.isReady()) {
            return false;
        }
        if (localDeployContainer == null) {
            localDeployContainer = new LocalDeployContainer(wfBaseClientApi);
            localDeployContainer.loadCenterConfigs(true, new StringBuffer());
        }
        if (localResContainer == null) {
            localResContainer = new LocalResContainer(wfBaseClientApi);
            localResContainer.loadSubjectTemplatesFromWfBase();
        }
        //
        return true;
    }

    @Override
    public boolean checkLocalMetaContainerReady() {
        StringBuffer errInfo = new StringBuffer();
        return this.localMetaDataContainer.checkReady(errInfo);
    }

    public boolean checkMClientReady() {
        if (mClientApi == null) {
            mClientApi = new MClientApi();
        }
        if (!mClientApi.isReady()) {
            mClientApi.connectServer();
        }
        // if (com.bca.pub.cfg.LogSwitch.debugLogEnable) log.debug("mClientApi.isReady: %b", mClientApi.isReady());
        return mClientApi.isReady();
    }

    // public boolean checkMClientReady() {
    // if (mClientApi == null) {
    // mClientApi = new MClientApi();
    // }
    // if (!mClientApi.isReady()) {
    // mClientApi.connectServer();
    // }
    // // if (com.bca.pub.cfg.LogSwitch.debugLogEnable) log.debug("mClientApi.isReady: %b", mClientApi.isReady());
    // return mClientApi.isReady();
    // }
    // public boolean checkLocalMetaContainerReady() {
    // StringBuffer errInfo = new StringBuffer();
    // return this.localMetaDataContainer.checkReady(errInfo);
    // }
    public WfBaseClientApi checkWfBaseConnection(boolean reconnectForcely) {
        if (reconnectForcely) {
            wfBaseClientApi = null;
        }
        if (wfBaseClientApi != null && wfBaseClientApi.isReady()) {
            return wfBaseClientApi;
        }
        wfBaseClientApi = new WfBaseClientApi();
        Ret i = wfBaseClientApi.connectServer();
        // localResContainer = new LocalResContainer(wfBaseClientApi);
        // localResContainer.loadSubjectTemplatesFromWfBase();
        //

        return wfBaseClientApi.isReady() ? wfBaseClientApi : null;
    }

    // BweNotifyManager中，I_Notifyable n = getNotifyRequest(host, app) 语句根据getAppName返回的名称寻找代理。
    @Override
    public String getAppName() {
        return "";
    }

    public BcaToolkitConfig getBcaToolkitConfig() {
        return cfg;
    }

    public BcaToolkitConfigBean getBcaToolkitConfigBean() {
        return cfg.getCfgBean();
    }

    @Override
    public BweClientApi getBweClientApi() {
        return bweClientApi;
    }

    @Override
    public ClassPool getClassPool() {
        return classPool;
    }

    @Override
    public DClientApi getDclientApi() {
        return dclientApi;
    }

    @Override
    public LocalDeployContainer getLocalDeployContainer() {
        return this.localDeployContainer;
    }

    @Override
    public LocalMetaDataContainer getLocalMetaDataContainer() {
        return this.localMetaDataContainer;
    }

    @Override
    public LocalResContainer getLocalResContainer() {
        return this.localResContainer;
    }

    @Override
    public Log getLog() {
        return log;
    }

    @Override
    public BcaToolkitFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public MetaClientApi getMetaClientApi() {
        return localMetaDataContainer.getMetaClientApi();
    }

    // public MetaClientApi getMetaClientApi() {
    // throw new UnsupportedOperationException("Not supported yet.");
    // }
    @Override
    public String getRmiName() {
        return "";
    }

    // public LocalDeployContainer getLocalDeployContainer() {
    // return localDeployContainer;
    // }
    //    
    // public LocalMetaDataContainer getLocalMetaDataContainer() {
    // return localMetaDataContainer;
    // }
    //    
    // public LocalResContainer getLocalResContainer() {
    // return localResContainer;
    // }
    //    
    // public MetaClientApi getMetaClientApi() {
    // return localMetaDataContainer.getMetaClientApi();
    // }
    @Override
    public WfBaseClientApi getWfBaseClientApi() {
        return wfBaseClientApi;
    }

    @Override
    public WfFlowBuildRt getWorkingWfFlowBuild() {
        return BstudioGlobal.getActiveWfFlowBuild();
    }

    @Override
    public WfFlowRegRt getWorkingWfFlowReg() {
        WfFlowBuildRt b = BstudioGlobal.getActiveWfFlowBuild();
        if (b == null) {
            return null;
        }
        return b.flow;
    }

    public boolean loadFlowData() {
        this.checkWfBaseConnection(true);
        getWfBaseClientApi();
        // wfBaseClientApi = new WfBaseClientApi();
        // if (!Ret.isRetSuccess(wfBaseClientApi.connectServer())) {
        // log.error("wfBaseClientApi.connectServer failed. BStudio hated.");
        // return false;
        // }
        if (!wfBaseClientApi.isReady()) {
            return false;
        }

        localResContainer = new LocalResContainer(wfBaseClientApi);
        localDeployContainer = new LocalDeployContainer(wfBaseClientApi);
        localResContainer.loadSubjectTemplatesFromWfBase();
        //
        return true;
    }

    // 取代传统的 main函数。
    public boolean mainLoad(StringBuffer errInfo) {
        log.setStackLogLevel(BstudioGlobal.getPubConfig().getLogLevelsForStackPrinting());
        Ret b = wfinit();
        if (!b.isRetSuccess()) {
            errInfo.append(b.getInfo());
            return false;
        }

        if (!loadFlowData()) {
            errInfo.append("装载流程失败，建模工具不能工作。请查看日志并检查系统，定位故障。");
        }
        return true;
    }

    @Override
    protected void mainLoopProc() {
    }

    private boolean checkPassword() {
//        if(true)
//              return true;

        String pwd;
        CfgBasicSetting s = localDeployContainer.centerConfigs.get("Settings.BStudioPassword");
        if (s == null) {
            pwd = "shark";
        } else {
            checkLocalMetaContainerReady();
            MetaClientApi api = localMetaDataContainer.getMetaClientApi();
            Ret ret = new Ret();
            pwd = s.getVal();
            if (pwd == null) {
                pwd = "";
            }
            String pf = api.getPasswordFactory(ret);

            // log.debug(Toolfunc.locateClassFile(GeorageRegister.class.getName()));
            I_BcaPassCoder pc = GeorageRegister.getPassCoder(pf);
            if (pc != null) {
                try {
                    pwd = pc.getOrigText(pwd);
                } catch (Exception ex) {
                    this.bar.close();
                    Wftool.messageDialog(false, "密码验证错误,bstudio无法启动。\n请检查服务器、客户端的密码工厂是否一致。");
                    System.exit(0);
                }
            }
        }
        if ("".equals(pwd)) {   // 一般用于：开发调试
            return true;
        }

        BStudioLoginService serv = new BStudioLoginService(this);
//        serv.setRightPassword(pwd.toCharArray());
        BcaLoginPanel panel = new BcaLoginPanel(serv, null, null, false);

        panel.recreateLoginPanel_p();
        panel.setUserName("bca");

        bar.setVisible(false);

        app.getMainFrame().setAlwaysOnTop(true);
        Status loginStatus = BcaLoginPanel.showLoginDialog(app.getMainFrame(), panel);
        bar.setVisible(true);
        app.getMainFrame().setAlwaysOnTop(false);
        //
        return Status.SUCCEEDED == loginStatus;
    }
    boolean wfinitFlag = false;

    private Ret wfinit() {
        if (wfinitFlag) {
            return Ret.getSuccessRet();
        }
        bar.incValue();
        checkWfBaseConnection(true);
        bar.incValue();
        checkBweClientReady();
        bar.incValue();
        checkLocalContainer();
        bar.incValue();
        if (!checkPassword()) {
            this.bar.close();
            // Wftool.messageDialog(false, "登录失败。");
            if (!isIsPluginFlag()) {    //不是插件项目时，退出程序
                System.exit(0);
                BcaToolkit.isCancel = true;
                return Ret.getFailureRet("登录失败");
            }else{                      //是插件项目时，不退出程序，避免Eclipse闪退
                BcaToolkit.isCancel = true;
                return Ret.getFailureRet("登录失败");
            }
            
        }

        boolean persistClientReady = checkPersistClientReady();
        PersistClientApi psApi = getPersistClientApi(); // 很邪门：此处这么get一下，那么发布后的studio就work了。否则基类的persistClientApi仍然为空。

        if (psApi == null || !persistClientReady) {
            log.error("persistClientApi <%s> not ready, system hated.", psApi);
            return Ret.getFailureRet("persistClientApi <%s> not ready, system hated.", psApi);
        }
        bar.incValue();

        final String[] poClasses = new String[]{WfEditStyle.class.getName(),
            WfActPropReg.class.getName(),
            WfActRetValuesReg.class.getName(),
            WfConnectionStyleReg.class.getName(),
            TlDomainReg.class.getName(), TlFolder.class.getName(),
            TlTemplateReg.class.getName(), TlArgsReg.class.getName(),
            TlTemplateTextReg.class.getName(),
            TlTemplateTextSave.class.getName(), TRouteDir.class.getName(),
            TRouteDiaglamReg.class.getName(),
            HibDataConnect.class.getName(),
            WfLogNodePrepare.class.getName(), WfLogBproc.class.getName(),
            WfLogBprocPara.class.getName(), WfLogSchedule.class.getName()
        };

        final Ret ret = new Ret();
        int handle = psApi.getHandle("bca", ret);
        // if(handle <= 0) {
        // if(!persitErrorMsgFlag) {
        // persitErrorMsgFlag = true;
        // String msg =
        // "持久化连接错误。请检查server是否已启动。\n\n如果server端是linux系统，请确认hosts中已将通信的ip地址注册。";
        // JOptionPane.showMessageDialog(Nbtool_bstudio.getNbMainWindow(), msg,
        // "启动错误", JOptionPane.ERROR_MESSAGE);
        // // Wftool.messageDialog(false,
        // "持久化连接错误。请检查server是否已启动。\n\n如果server端是linux系统，请确认hosts中已将通信的ip地址注册。");
        // }
        // return ;
        // }
        assert handle > 0;
        try {
            psApi.checkPoClassLoaded(handle, poClasses, ret);
        } finally {
            psApi.freeHandle(handle, ret);
        }
        bar.incValue();
        wfinitFlag = true;

        return Ret.getSuccessRet();
    }

    //-- Edward Gao code begin -------------------------------------------------
    @Override
    public void shutdownApp() {
        if (!isIsPluginFlag()) {  //不是插件项目时，退出程序
            System.exit(0);
            this.mainFrame.setVisible(false);
            this.mainFrame.dispose();
        }else{                      //是插件项目时，不退出程序，避免Eclipse闪退
            this.mainFrame.setVisible(false);
            this.mainFrame.dispose();
        }
      
        
    }
    //-- Edward Gao code end ---------------------------------------------------
    // public boolean checkLocalMetaContainerReady() {
    // StringBuffer errInfo = new StringBuffer();
    // return this.localMetaDataContainer.checkReady(errInfo);
    // }
    // public BcaCmsClientApi getCmsApi() {
    // return cmsApi;
    // }
    //
    // public IvrPoolNT_Client getIvrPoolNT() {
    // checkCmsApiReady();
    // return ivrPoolNT;
    // }
    // public MClientApi getMClientApi() {
    // return mClientApi;
    // }
//    //-- 程序启动时的闪屏处理 begin ---------------------------------------------
//    private static Logger logger = Logger.getLogger(BcaToolkit.class.getSimpleName());
    //在这儿你能获得通过-splash:设置给程序的splash screen的实例
    private static final SplashScreen splash = SplashScreen.getSplashScreen();
    private static Rectangle splashBounds;
    private static Graphics2D splashGraphics;

    /**
     * 初始化splash
     */
    protected static void initSplash() throws Exception {
        if (splash == null) {
            throw new Exception("no splash image specified eg. -splash:mysplash.png \n 请直接从jar文件启动");
        }
        //下面给splash screen画一个边框
        splashBounds = splash.getBounds();
        //在这儿初始化图形对象，该图形对象取自splash
        splashGraphics = (Graphics2D) splash.createGraphics();
        if (splashGraphics == null) {
            throw new Exception("no SplashScreen Graphics2D");
        }
        splashGraphics.setColor(Color.YELLOW);
        splashGraphics.drawRect(0, 0, splashBounds.width - 1, splashBounds.height - 1);
//        更改显示的图片
//        splash.setImageURL(BcaToolkit.class.getResource("/com/bca/studio/app/splash.gif"));    
    }

    /**
     * 更新splash，这个方法
     */
    protected static void updateSplash(String status, int progress) {
        if (splash == null) {
            return;
        }
        if (splashGraphics == null) {
            return;
            //重画splash上面的进度并通知splash更新界面
        }
        drawSplash(splashGraphics, status, progress);
        splash.update();
    }

    /**
     * 这个方法画一个进度条，不再详述。
     */
    protected static void drawSplash(Graphics2D splashGraphics, String status, int progress) {
        int barWidth = splashBounds.width * 50 / 100;
        splashGraphics.setComposite(AlphaComposite.Clear);                  //清理

        splashGraphics.fillRect(1, 260, splashBounds.width - 2, 20);
        splashGraphics.setPaintMode();

        splashGraphics.setColor(new Color(162, 198, 238));                   //设置出现字幕的位置的背景色

        splashGraphics.fillRect(0, 279, 366, 16);                            //字幕背景色位置

        splashGraphics.setColor(Color.BLACK);                                //字幕颜色

        splashGraphics.drawString(status, 8, 292);                           //字幕位置

//        splashGraphics.setColor(Color.BLACK);                              //进度条边框颜色
//        splashGraphics.drawRect(10, 266, barWidth + 2, 10);                //进度条边框位置
        splashGraphics.setColor(Color.YELLOW);                               //进度条颜色

        int width = progress * barWidth / 100;                               //进度条宽度

        splashGraphics.fillRect(0, 266, width + 99, 9);                     //进度条位置

//        splashGraphics.setColor(Color.WHITE);                               //进度条背景颜色
//        splashGraphics.fillRect(11 + width + 1, 266, barWidth - width, 9);  //进度条背景位置
    }

    @Override
    protected void prepareShutdown() {
    }

    /**
     * @return the bar
     */
    public ProgressbarWindow getBar() {
        return bar;
    }

    private void parseBStudioArgs(String[] args) {
        // 先解析快速GUI通道.  /fastExecChannel:codeFactory
        String fc = Stringtool.findFollowedStringFromArray(args, "/fastExecChannel:", true);
        if (fc == null || fc.isEmpty()) {
            return;
        }
        if ("codeFactory".equals(fc)) {
            BcaToolkitFcSwitch.codeFatory = true;
            return;
        }
    }

    @Override
    public String getExternalEditor() {
        return this.cfg.getCfgBean().externalEditor;
    }

    @Override
    public String getExternalExternalCompareTool() {
        return this.cfg.getCfgBean().externalCompareTool;
    }
    //-- 程序启动时的闪屏处理 begin ---------------------------------------------

    @Override
    public I_StudioConfig getStudioConfig() {
        return cfg;
    }

    @Override
    public boolean reloadConfig() {
        cfg.reloadConfig();
        return true;
    }

//    private void test001() {
//        String s = "com.hxhk.wx.WxFlowTemplate";
//        String d = "bca.demo.d001a";
//        SRDU r = wfBaseClientApi.createBcaFlowByTemplate(s, d, "测试的流程", false, null);
//        log.debug(r);
//    }
}
