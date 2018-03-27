package microservice_framework.getInput;

import java.util.HashMap;
/**
 * ��ܴ���ʵ����
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
	 * ����·��
	 */
	private String savePath;
	/**
	 * ��Ŀ����
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
	 * ��Ŀ�汾��
	 */
	private String projectVersion;
	/**
	 * jdk�汾��
	 */
	private String jdkVersion;
	/**
	 * ��ܰ汾��
	 */
	private String frameVersion;
	/**
	 * �����ʽ
	 */
	private String packMode;
	/**
	 * �û�����
	 */
	private String userName;
	/**
	 * �û�����
	 */
	private String userWorkNumber;
	/**
	 * �û�����
	 */
	private String userMail;
	/**
	 * �û���Ŀ��
	 */
	private String projTeam;
	/**
	 * �û�����
	 */
	private String department;
	/**
	 * �˿ں�
	 */
	private String portNumber;
	/**
	 * ǰ�˿������
	 */
	private String frontFrameName;
	/**
	 * �Ż���ȫ������
	 */
	private String psServiceName;
	/**
	 * �Ż���ȫurlǰ׺
	 */
	private String psCasServiceUrlPrefix;
	/**
	 * �Ż���ȫ��½��ַ
	 */
	private String psCasServerLoginUrl;
	/**
	 * �Ż���ȫĬ��ҳ
	 */
	private String psDefaultPage;
	/**
	 * �Ż���ȫ***��ַ
	 */
	private String psSecurityServiceURL;
        /**
         * ����汾��
         */
        private String cacheVersion;
	/**
	 * ����url��ַ
	 */
	private String cacheServiceUrl;
        /**
	 * �Ƿ���SFTPЭ��
	 */
	private boolean isSftp;
	/**
	 * sftp  Ip
	 */
	private String sftpIp;
	/**
	 * sftp �˿ں�
	 */
	private String sftpPort;
	/**
	 * sftp �û���
	 */
	private String sftpUsername;
	/**
	 * sftp ����
	 */
	private String sftpPassword;
        /**
	 * �Ƿ���FTPЭ��
	 */
	private boolean isFtp;
	/**
	 * sftp ·��
	 */
	private String sftpRemotedir;
	/**
	 * ftp IP
	 */
	private String ftpIp;
	/**
	 * sftp �˿ں�
	 */
	private String ftpPort;
	/**
	 * sftp �û���
	 */
	private String ftpUsername;
	/**
	 * sftp ����
	 */
	private String ftpPassword;
	/**
	 * sftp ·��
	 */
	private String ftpRemotedir;
	/**
	 * �Ƿ���� ǰ�����
	 */
	private boolean isFrontFrame;
	/**
	 * �Ƿ���� �Ż���ȫ���
	 */
	private boolean isPortalSecurity;
	/**
	 * �Ƿ���� ����������
	 */
	private boolean isCacheService;
	/**
	 * �Ƿ���� �ϴ��������
	 */
	private boolean isFileUpDownLoad;
	/**
	 * �Ƿ���� ���ڹ������
	 */
	private boolean isPaymantManage;
	/**
	 * �Ƿ���� apijson���
	 */
	private boolean isApijson;
	/**
	 * �Ƿ���� swagger���
	 */
	private boolean isSwagger;
	/**
	 * �Ƿ���� ����ʽ���
	 */
	private boolean isDeploymentWay;
        /**
	 * �Ƿ������Ԫ�������
	 */
	private boolean isUnitTest;
        /**
	 * �Ƿ����΢�������
	 */
	private boolean isMServiceManage;
        /**
	 * �Ƿ����΢����ע���뷢��
	 */
	private boolean isMServiceRD;
        /**
	 * ΢������ʵ�ַ
	 */
	private String mserviceURL;
        /**
	 * ΢����ע�����ķ��ʵ�ַ
	 */
	private String mserviceR_URL;
	/**
	 * ������Դ
	 */
	private DataSource mainDataSource;
	/**
	 * ������Դ
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
