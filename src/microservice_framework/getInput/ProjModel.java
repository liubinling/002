package microservice_framework.getInput;

import java.util.HashMap;
/**
 * 框架创建实体类
 * @author eve
 *
 */

public class ProjModel {

    /**
     * @return the isFtp
     */
    public boolean isIsFtp() {
        return isFtp;
    }

    /**
     * @param isFtp the isFtp to set
     */
    public void setIsFtp(boolean isFtp) {
        this.isFtp = isFtp;
    }

    /**
     * @return the isSftp
     */
    public boolean getIsSftp() {
        return isSftp;
    }

    /**
     * @param isSftp the isSftp to set
     */
    public void setIsSftp(boolean isSftp) {
        this.isSftp = isSftp;
    }

    /**
     * @return the mserviceR_URL
     */
    public String getMserviceR_URL() {
        return mserviceR_URL;
    }

    /**
     * @param mserviceR_URL the mserviceR_URL to set
     */
    public void setMserviceR_URL(String mserviceR_URL) {
        this.mserviceR_URL = mserviceR_URL;
    }

    /**
     * @return the mserviceURL
     */
    public String getMserviceURL() {
        return mserviceURL;
    }

    /**
     * @param mserviceURL the mserviceURL to set
     */
    public void setMserviceURL(String mserviceURL) {
        this.mserviceURL = mserviceURL;
    }

    /**
     * @return the isMServiceRD
     */
    public boolean isIsMServiceRD() {
        return isMServiceRD;
    }

    /**
     * @param isMServiceRD the isMServiceRD to set
     */
    public void setIsMServiceRD(boolean isMServiceRD) {
        this.isMServiceRD = isMServiceRD;
    }

    /**
     * @return the isMServiceManage
     */
    public boolean isIsMServiceManage() {
        return isMServiceManage;
    }

    /**
     * @param isMServiceManage the isMServiceManage to set
     */
    public void setIsMServiceManage(boolean isMServiceManage) {
        this.isMServiceManage = isMServiceManage;
    }

    /**
     * @return the isUnitTest
     */
    public boolean isIsUnitTest() {
        return isUnitTest;
    }

    /**
     * @param isUnitTest the isUnitTest to set
     */
    public void setIsUnitTest(boolean isUnitTest) {
        this.isUnitTest = isUnitTest;
    }

    /**
     * @return the cacheVersion
     */
    public String getCacheVersion() {
        return cacheVersion;
    }

    /**
     * @param cacheVersion the cacheVersion to set
     */
    public void setCacheVersion(String cacheVersion) {
        this.cacheVersion = cacheVersion;
    }
	/**
	 * 保存路径
	 */
	private String savePath;
	/**
	 * 项目名称
	 */
	private String projectName;	
	/**
	 * groupId
	 */
	private String groupId;
	/**
	 * artifactId
	 */
	private String artifactId;
	/**
	 * 项目版本号
	 */
	private String projectVersion;
	/**
	 * jdk版本号
	 */
	private String jdkVersion;
	/**
	 * 框架版本号
	 */
	private String frameVersion;
	/**
	 * 打包方式
	 */
	private String packMode;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户工号
	 */
	private String userWorkNumber;
	/**
	 * 用户邮箱
	 */
	private String userMail;
	/**
	 * 用户项目组
	 */
	private String projTeam;
	/**
	 * 用户部门
	 */
	private String department;
	/**
	 * 端口号
	 */
	private String portNumber;
	/**
	 * 前端框架名称
	 */
	private String frontFrameName;
	/**
	 * 门户安全服务名
	 */
	private String psServiceName;
	/**
	 * 门户安全url前缀
	 */
	private String psCasServiceUrlPrefix;
	/**
	 * 门户安全登陆地址
	 */
	private String psCasServerLoginUrl;
	/**
	 * 门户安全默认页
	 */
	private String psDefaultPage;
	/**
	 * 门户安全***地址
	 */
	private String psSecurityServiceURL;
        /**
         * 缓存版本号
         */
        private String cacheVersion;
	/**
	 * 缓存url地址
	 */
	private String cacheServiceUrl;
        /**
	 * 是否用SFTP协议
	 */
	private boolean isSftp;
	/**
	 * sftp  Ip
	 */
	private String sftpIp;
	/**
	 * sftp 端口号
	 */
	private String sftpPort;
	/**
	 * sftp 用户名
	 */
	private String sftpUsername;
	/**
	 * sftp 密码
	 */
	private String sftpPassword;
        /**
	 * 是否用FTP协议
	 */
	private boolean isFtp;
	/**
	 * sftp 路径
	 */
	private String sftpRemotedir;
	/**
	 * ftp IP
	 */
	private String ftpIp;
	/**
	 * sftp 端口号
	 */
	private String ftpPort;
	/**
	 * sftp 用户名
	 */
	private String ftpUsername;
	/**
	 * sftp 密码
	 */
	private String ftpPassword;
	/**
	 * sftp 路径
	 */
	private String ftpRemotedir;
	/**
	 * 是否包含 前端组件
	 */
	private boolean isFrontFrame;
	/**
	 * 是否包含 门户安全组件
	 */
	private boolean isPortalSecurity;
	/**
	 * 是否包含 缓存服务组件
	 */
	private boolean isCacheService;
	/**
	 * 是否包含 上传下载组件
	 */
	private boolean isFileUpDownLoad;
	/**
	 * 是否包含 账期管理组件
	 */
	private boolean isPaymantManage;
	/**
	 * 是否包含 apijson组件
	 */
	private boolean isApijson;
	/**
	 * 是否包含 swagger组件
	 */
	private boolean isSwagger;
	/**
	 * 是否包含 部署方式组件
	 */
	private boolean isDeploymentWay;
        /**
	 * 是否包含单元测试组件
	 */
	private boolean isUnitTest;
        /**
	 * 是否包含微服务管理
	 */
	private boolean isMServiceManage;
        /**
	 * 是否包含微服务注册与发现
	 */
	private boolean isMServiceRD;
        /**
	 * 微服务访问地址
	 */
	private String mserviceURL;
        /**
	 * 微服务注册中心访问地址
	 */
	private String mserviceR_URL;
	/**
	 * 主数据源
	 */
	private DataSource mainDataSource;
	/**
	 * 多数据源
	 */
	
	private HashMap<String, DataSource> multiDataSource;
	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getProjectVersion() {
		return projectVersion;
	}
	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}
	public String getJdkVersion() {
		return jdkVersion;
	}
	public void setJdkVersion(String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}
	public String getFrameVersion() {
		return frameVersion;
	}
	public void setFrameVersion(String frameVersion) {
		this.frameVersion = frameVersion;
	}
	public String getPackMode() {
		return packMode;
	}
	public void setPackMode(String packMode) {
		this.packMode = packMode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserWorkNumber() {
		return userWorkNumber;
	}
	public void setUserWorkNumber(String userWorkNumber) {
		this.userWorkNumber = userWorkNumber;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getProjTeam() {
		return projTeam;
	}
	public void setProjTeam(String projTeam) {
		this.projTeam = projTeam;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public String getFrontFrameName() {
		return frontFrameName;
	}
	public void setFrontFrameName(String frontFrameName) {
		this.frontFrameName = frontFrameName;
	}
	public String getPsServiceName() {
		return psServiceName;
	}
	public void setPsServiceName(String psServiceName) {
		this.psServiceName = psServiceName;
	}
	public String getPsCasServiceUrlPrefix() {
		return psCasServiceUrlPrefix;
	}
	public void setPsCasServiceUrlPrefix(String psCasServiceUrlPrefix) {
		this.psCasServiceUrlPrefix = psCasServiceUrlPrefix;
	}
	public String getPsCasServerLoginUrl() {
		return psCasServerLoginUrl;
	}
	public void setPsCasServerLoginUrl(String psCasServerLoginUrl) {
		this.psCasServerLoginUrl = psCasServerLoginUrl;
	}
	public String getPsDefaultPage() {
		return psDefaultPage;
	}
	public void setPsDefaultPage(String psDefaultPage) {
		this.psDefaultPage = psDefaultPage;
	}
	public String getPsSecurityServiceURL() {
		return psSecurityServiceURL;
	}
	public void setPsSecurityServiceURL(String psSecurityServiceURL) {
		this.psSecurityServiceURL = psSecurityServiceURL;
	}
	public String getCacheServiceUrl() {
		return cacheServiceUrl;
	}
	public void setCacheServiceUrl(String cacheServiceUrl) {
		this.cacheServiceUrl = cacheServiceUrl;
	}
	public String getSftpIp() {
		return sftpIp;
	}
	public void setSftpIp(String sftpIp) {
		this.sftpIp = sftpIp;
	}
	public String getSftpPort() {
		return sftpPort;
	}
	public void setSftpPort(String sftpPort) {
		this.sftpPort = sftpPort;
	}
	public String getSftpUsername() {
		return sftpUsername;
	}
	public void setSftpUsername(String sftpUsername) {
		this.sftpUsername = sftpUsername;
	}
	public String getSftpPassword() {
		return sftpPassword;
	}
	public void setSftpPassword(String sftpPassword) {
		this.sftpPassword = sftpPassword;
	}
	public String getSftpRemotedir() {
		return sftpRemotedir;
	}
	public void setSftpRemotedir(String sftpRemotedir) {
		this.sftpRemotedir = sftpRemotedir;
	}
	public String getFtpIp() {
		return ftpIp;
	}
	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}
	public String getFtpPort() {
		return ftpPort;
	}
	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}
	public String getFtpUsername() {
		return ftpUsername;
	}
	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}
	public String getFtpPassword() {
		return ftpPassword;
	}
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}
	public String getFtpRemotedir() {
		return ftpRemotedir;
	}
	public void setFtpRemotedir(String ftpRemotedir) {
		this.ftpRemotedir = ftpRemotedir;
	}
	public boolean isFrontFrame() {
		return isFrontFrame;
	}
	public void setFrontFrame(boolean isFrontFrame) {
		this.isFrontFrame = isFrontFrame;
	}
	public boolean isPortalSecurity() {
		return isPortalSecurity;
	}
	public void setPortalSecurity(boolean isPortalSecurity) {
		this.isPortalSecurity = isPortalSecurity;
	}
	public boolean isCacheService() {
		return isCacheService;
	}
	public void setCacheService(boolean isCacheService) {
		this.isCacheService = isCacheService;
	}
	public boolean isFileUpDownLoad() {
		return isFileUpDownLoad;
	}
	public void setFileUpDownLoad(boolean isFileUpDownLoad) {
		this.isFileUpDownLoad = isFileUpDownLoad;
	}
	public boolean isPaymantManage() {
		return isPaymantManage;
	}
	public void setPaymantManage(boolean isPaymantManage) {
		this.isPaymantManage = isPaymantManage;
	}
	public boolean isApijson() {
		return isApijson;
	}
	public void setApijson(boolean isApijson) {
		this.isApijson = isApijson;
	}
	public boolean isSwagger() {
		return isSwagger;
	}
	public void setSwagger(boolean isSwagger) {
		this.isSwagger = isSwagger;
	}
	public boolean isDeploymentWay() {
		return isDeploymentWay;
	}
	public void setDeploymentWay(boolean isDeploymentWay) {
		this.isDeploymentWay = isDeploymentWay;
	}
	public DataSource getMainDataSource() {
		return mainDataSource;
	}
	public void setMainDataSource(DataSource mainDataSource) {
		this.mainDataSource = mainDataSource;
	}
	public HashMap<String, DataSource> getMultiDataSource() {
		return multiDataSource;
	}
	public void setMultiDataSource(HashMap<String, DataSource> multiDataSource) {
		this.multiDataSource = multiDataSource;
	}
	
	

}
