package com.bca.utool.app;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.bca.pub.cl.ClassPool;
import com.bca.pub.pif.I_BcaPassCoder;
import com.bca.pub.tools.I_ProgressAct;
import com.bca.pub.tools.Layouttool;
import com.bca.pub.tools.ProgressbarWindow;
import com.bca.pub.tools.Toolfunc;
import com.bca.pub.tools.Wftool;
import com.bca.service.meta.hibernate.HibDataConnect;
import com.bca.studio.BStudioConfig;
import com.bca.studio.tools.BstudioGlobal;
import com.bca.studio.tools.Nbtool_bstudio;
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
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

/**
 * 
 * @author pxz
 */
public class BcaUToolApp extends AbstractStudioApp {

    final static com.bca.log.LogTextPane logTextPane = com.bca.log.LogTextPane.getLogTextPane();
    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog("btool", "btool-log.properties");
//    static public AppVersion ver; // 
    public final BStudioConfig cfg = BStudioConfig.getConfig();
    private final UToolFrame mainFrame; // = new UToolFrame(this);
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

    /**
     * Creates a new instance of BcaUTool
     */
    public BcaUToolApp() {
        log.setStackLogLevel(pubcfg.getLogLevelsForStackPrinting());

        I_ProgressAct cancelAct = new I_ProgressAct() {

            @Override
            public void cancelProgress() { System.exit(0);
            }
        };

        bar = new ProgressbarWindow("start BcaUToolApp....", cancelAct);
        bar.setMaximum(12);
        bar.setVisible(true); // ???????
        bar.setAlwaysOnTop(true);

        super.sinit();   // ?????super??? log???????

        mainFrame = new UToolFrame(this);
        mainFrame.setTitle("BCA-UTool Studio"); // Main Frame
        //
        // mainFrame.getContentPane().add( new
        // NCanvasForm(),"mainCanvasDockingPanel" ); //Add Docking Panel to
        // mainFrame in card way
        // mainFrame.showCard("mainCanvasDockingPanel"); //Show
        // mainCanvasDockingPanel in mainFrame
        //
        bar.incValue();
        mainFrame.wfinit(bar);
    }

    public static BcaUToolApp getApp() {
        return (BcaUToolApp) app;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
//        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                final BcaUToolApp appx = new BcaUToolApp();

                BcaUToolApp.log.info("BCA-Studio start.");

//                Timetool.logSysTime(Toolfunc.getCurrentFunctionName());

//                appSplash();

//                appx.init();
//                Timetool.logSysTime(Toolfunc.getCurrentFunctionName());
//                Wftool l;

                appx.mainFrame.setIconImage(com.bca.pub.tools.Wftool.getImage("BcaToolkit.gif", WfIconType.Util));
                appx.mainFrame.setBounds(Layouttool.getScreenBounds()); // .getCenterBounds(800, 600));

                appx.mainFrame.setVisible(true);
                appx.getBar().close();
            }
//            private void appSplash() {
//                if (true) {
//                    System.out.println("Splash???????");
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
        Nbtool_bstudio.updateMainTitle();
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
    // public AbstractConfig_classic getBStudioConfig() {
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

    // BweNotifyManager?§µ?I_Notifyable n = getNotifyRequest(host, app) ?????getAppName??????????????
    public String getAppName() {
        return "";
    }

//    @Override
//    public BStudioConfig getBStudioConfig() {
//        return cfg;
//    }
    public BweClientApi getBweClientApi() {
        return bweClientApi;
    }

    public ClassPool getClassPool() {
        return classPool;
    }

    public DClientApi getDclientApi() {
        return dclientApi;
    }

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
    public UToolFrame getMainFrame() {
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

//    public void init() {
////        log.info(Toolfunc.locateClassFile("net.infonode.docking.TabWindow"));
//
//        // if (true) {
//        // return;
//        // }
//        checkWfBaseConnection(true);
//        checkBweClientReady();
//        checkLocalContainer();
////        checkPassword();
//    // localResContainer.loadSubjectTemplatesFromWfBase();
//    }
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

    // ?????? main????
    public boolean mainLoad(StringBuffer errInfo) {
        log.setStackLogLevel(BstudioGlobal.getPubConfig().getLogLevelsForStackPrinting());
        Ret b = wfinit();
        if (!b.isRetSuccess()) {
            errInfo.append(b.getInfo());
            return false;
        }

        if (!loadFlowData()) {
            errInfo.append("???????????????????????????????????????????¦Ë?????");
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

            log.debug(Toolfunc.locateClassFile(GeorageRegister.class.getName()));

            I_BcaPassCoder pc = GeorageRegister.getPassCoder(pf);
            if (pc != null) {
                try {
                    pwd = pc.getOrigText(pwd);
                } catch (Exception ex) {
                    this.bar.close();
                    Wftool.messageDialog(false, "???????????,btool?????????\n??????????????????????????????");
               // Wftool.messageDialog(true, "System.exit:" + Toolfunc.getCurrentFunctionName());
                     System.exit(0);
                }
            }
        }
//        if ("".equals(pwd)) {   // ????????????????
        return true;
//        }

//        BStudioLoginService serv = new BStudioLoginService();
//        serv.setRightPassword(pwd.toCharArray());
//        BcaLoginPanel panel = new BcaLoginPanel(serv, null, null, false);
//
//        panel.recreateLoginPanel_p();
//        panel.setUserName("bca");
//
//        bar.setVisible(false);
//
//        app.getMainFrame().setAlwaysOnTop(true);
//        Status loginStatus = BcaLoginPanel.showLoginDialog(app.getMainFrame(), panel);
//        bar.setVisible(true);
//        app.getMainFrame().setAlwaysOnTop(false);
//        //
//        return Status.SUCCEEDED == loginStatus;

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
            Wftool.messageDialog(false, "???????");
                         System.exit(0);
            return Ret.getFailureRet("??????");
        }

        boolean persistClientReady = checkPersistClientReady();
        PersistClientApi psApi = getPersistClientApi(); // ??§Ñ?????????get??????????????studio??work???????????persistClientApi???????

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
        // "???????????????server???????????\n\n???server????linux?????????hosts?????????ip??????";
        // JOptionPane.showMessageDialog(Nbtool_btool.getNbMainWindow(), msg,
        // "????????", JOptionPane.ERROR_MESSAGE);
        // // Wftool.messageDialog(false,
        // "???????????????server???????????\n\n???server????linux?????????hosts?????????ip??????");
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
                System.exit(0);
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
//    //-- ??????????????????? begin ---------------------------------------------
//    private static Logger logger = Logger.getLogger(BcaUTool.class.getSimpleName());
    //??????????????-splash:?????????splash screen?????
    private static final SplashScreen splash = SplashScreen.getSplashScreen();
    private static Rectangle splashBounds;
    private static Graphics2D splashGraphics;

    /**
     * ?????splash
     */
    protected static void initSplash() throws Exception {
        if (splash == null) {
            throw new Exception("no splash image specified eg. -splash:mysplash.png \n ??????jar???????");
        }
        //?????splash screen????????
        splashBounds = splash.getBounds();
        //???????????¦Æ??????¦Æ??????splash
        splashGraphics = (Graphics2D) splash.createGraphics();
        if (splashGraphics == null) {
            throw new Exception("no SplashScreen Graphics2D");
        }
        splashGraphics.setColor(Color.YELLOW);
        splashGraphics.drawRect(0, 0, splashBounds.width - 1, splashBounds.height - 1);
//        ??????????
//        splash.setImageURL(BcaUTool.class.getResource("/com/bca/studio/app/splash.gif"));
    }

    /**
     * ????splash?????????
     */
    protected static void updateSplash(String status, int progress) {
        if (splash == null) {
            return;
        }
        if (splashGraphics == null) {
            return;
            //???splash??????????splash???????
        }
        drawSplash(splashGraphics, status, progress);
        splash.update();
    }

    /**
     * ?????????????????????????????
     */
    protected static void drawSplash(Graphics2D splashGraphics, String status, int progress) {
        int barWidth = splashBounds.width * 50 / 100;
        splashGraphics.setComposite(AlphaComposite.Clear);                  //????

        splashGraphics.fillRect(1, 260, splashBounds.width - 2, 20);
        splashGraphics.setPaintMode();

        splashGraphics.setColor(new Color(162, 198, 238));                   //???¨®????????¦Ë???????

        splashGraphics.fillRect(0, 279, 366, 16);                            //????????¦Ë??

        splashGraphics.setColor(Color.BLACK);                                //??????

        splashGraphics.drawString(status, 8, 292);                           //???¦Ë??

//        splashGraphics.setColor(Color.BLACK);                              //???????????
//        splashGraphics.drawRect(10, 266, barWidth + 2, 10);                //????????¦Ë??

        splashGraphics.setColor(Color.YELLOW);                               //????????

        int width = progress * barWidth / 100;                               //????????

        splashGraphics.fillRect(0, 266, width + 99, 9);                     //?????¦Ë??

//        splashGraphics.setColor(Color.WHITE);                               //????????????
//        splashGraphics.fillRect(11 + width + 1, 266, barWidth - width, 9);  //?????????¦Ë??
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

    @Override
    public String getExternalEditor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getExternalExternalCompareTool() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //-- ??????????????????? begin ---------------------------------------------

    @Override
    public I_StudioConfig getStudioConfig() {
        return cfg;
    }

    @Override
    public boolean reloadConfig() {
        cfg.reloadConfig();
        return true;
    }
}
