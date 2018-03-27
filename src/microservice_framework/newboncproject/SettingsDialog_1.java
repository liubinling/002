/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservice_framework.newboncproject;

import microservice_framework.baseFrames.BaseDialog;
import static microservice_framework.baseFrames.BaseDialog.getFHEIGHT;
import static microservice_framework.baseFrames.BaseDialog.getFWIDTH;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author ur
 */
public class SettingsDialog_1 extends BaseDialog {

    /**
     * @return the proGroupTFD
     */
    public javax.swing.JTextField getProGroupTFD() {
        return proGroupTFD;
    }

    /**
     * @param proGroupTFD the proGroupTFD to set
     */
    public void setProGroupTFD(javax.swing.JTextField proGroupTFD) {
        this.proGroupTFD = proGroupTFD;
    }

    /**
     * @return the departmentTFD
     */
    public javax.swing.JTextField getDepartmentTFD() {
        return departmentTFD;
    }

    /**
     * @param departmentTFD the departmentTFD to set
     */
    public void setDepartmentTFD(javax.swing.JTextField departmentTFD) {
        this.departmentTFD = departmentTFD;
    }

    /**
     * @return the proLocationTFD
     */
    public javax.swing.JTextField getProLocationTFD() {
        return proLocationTFD;
    }

    /**
     * @param proLocationTFD the proLocationTFD to set
     */
    public void setProLocationTFD(javax.swing.JTextField proLocationTFD) {
        this.proLocationTFD = proLocationTFD;
    }

    /**
     * @return the portNumberTFD
     */
    public javax.swing.JTextField getPortNumberTFD() {
        return portNumberTFD;
    }

    /**
     * @param portNumberTFD the portNumberTFD to set
     */
    public void setPortNumberTFD(javax.swing.JTextField portNumberTFD) {
        this.portNumberTFD = portNumberTFD;
    }

    /**
     * @return the frameworkVersionBox
     */
    public javax.swing.JComboBox getFrameworkVersionBox() {
        return frameworkVersionBox;
    }

    /**
     * @param frameworkVersionBox the frameworkVersionBox to set
     */
    public void setFrameworkVersionBox(javax.swing.JComboBox frameworkVersionBox) {
        this.frameworkVersionBox = frameworkVersionBox;
    }

    /**
     * @return the jdkVersionBox
     */
    public javax.swing.JComboBox getJdkVersionBox() {
        return jdkVersionBox;
    }

    /**
     * @param jdkVersionBox the jdkVersionBox to set
     */
    public void setJdkVersionBox(javax.swing.JComboBox jdkVersionBox) {
        this.jdkVersionBox = jdkVersionBox;
    }

    /**
     * @return the versionTFD
     */
    public javax.swing.JTextField getVersionTFD() {
        return versionTFD;
    }

    /**
     * @param versionTFD the versionTFD to set
     */
    public void setVersionTFD(javax.swing.JTextField versionTFD) {
        this.versionTFD = versionTFD;
    }

    /**
     * @return the artifactIdTFD
     */
    public javax.swing.JTextField getArtifactIdTFD() {
        return artifactIdTFD;
    }

    /**
     * @param artifactIdTFD the artifactIdTFD to set
     */
    public void setArtifactIdTFD(javax.swing.JTextField artifactIdTFD) {
        this.artifactIdTFD = artifactIdTFD;
    }

    /**
     * @return the groupIdTFD
     */
    public javax.swing.JTextField getGroupIdTFD() {
        return groupIdTFD;
    }

    /**
     * @param groupIdTFD the groupIdTFD to set
     */
    public void setGroupIdTFD(javax.swing.JTextField groupIdTFD) {
        this.groupIdTFD = groupIdTFD;
    }

    /**
     * @return the projectNameTFD
     */
    public javax.swing.JTextField getProjectNameTFD() {
        return projectNameTFD;
    }

    /**
     * @param projectNameTFD the projectNameTFD to set
     */
    public void setProjectNameTFD(javax.swing.JTextField projectNameTFD) {
        this.projectNameTFD = projectNameTFD;
    }

    /**
     * @return the settingsDialog_1
     */
    public SettingsDialog_2 getSettingsDialog_2() {
        return settingsDialog_2;
    }

    /**
     * @param settingsDialog_2
     */
    public void setSettingsDialog_2(SettingsDialog_2 settingsDialog_2) {
        this.settingsDialog_2 = settingsDialog_2;
    }
    private static final String EMAILTOOLTIP="�����û��Ķ������Ż򶫷������ӹ�˾���䣨xxx@xxx.xxx��"; 
    private static String defaultPath;
    private SettingsDialog_2 settingsDialog_2;   //��1������ҳ��
    private int FRAMEHEIGHT; //����߶�
    private int FRAMEWIDTH;  //������
    /**
     * Creates new form SettingsDialog_1
     */
    public SettingsDialog_1() {
        initComponents();
        wfinit();
    }

    /** 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperPanel = new javax.swing.JPanel();
        topLabel1 = new javax.swing.JLabel();
        topLabel1.setFont(new Font("����", Font.BOLD, 12));
        topLabel2 = new javax.swing.JLabel();
        topLabel2.setFont(new Font("����", Font.PLAIN, 12));
        topLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new Font("����", Font.PLAIN, 12));
        projectNameTFD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setFont(new Font("����", Font.PLAIN, 12));
        groupIdTFD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setFont(new Font("����", Font.PLAIN, 12));
        artifactIdTFD = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel10.setFont(new Font("����", Font.PLAIN, 12));
        versionTFD = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel11.setFont(new Font("����", Font.PLAIN, 12));
        jLabel12 = new javax.swing.JLabel();
        jLabel12.setFont(new Font("����", Font.PLAIN, 12));
        jLabel13 = new javax.swing.JLabel();
        jLabel13.setFont(new Font("����", Font.PLAIN, 12));
        portNumberTFD = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        proLocationCBox = new javax.swing.JCheckBox();
        openDirectoryButton = new javax.swing.JButton();
        proLocationTFD = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel14.setFont(new Font("����", Font.PLAIN, 12));
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setFont(new Font("����", Font.PLAIN, 12));
        emailTFD = new javax.swing.JTextField();
        departmentTFD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel6.setFont(new Font("����", Font.PLAIN, 12));
        proGroupTFD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel7.setFont(new Font("����", Font.PLAIN, 12));
        jdkVersionBox = new javax.swing.JComboBox();
        frameworkVersionBox = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        nextButton = new javax.swing.JButton();
        finishButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("New MicroService Project");
        setMinimumSize(new java.awt.Dimension(6, 7));

        upperPanel.setMinimumSize(new java.awt.Dimension(7, 1));

        topLabel1.setFont(new java.awt.Font("����", 1, 12)); // NOI18N
        topLabel1.setText("MicroService Project");
        topLabel1.setMinimumSize(new java.awt.Dimension(9, 1));

        topLabel2.setText("Configure MicroService project settings");
        topLabel2.setMinimumSize(new java.awt.Dimension(15, 1));

        topLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/microservice_framework/images/open_file.png"))); // NOI18N
        topLabel3.setMinimumSize(new java.awt.Dimension(1, 1));

        javax.swing.GroupLayout upperPanelLayout = new javax.swing.GroupLayout(upperPanel);
        upperPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(topLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(topLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        upperPanelLayout.setVerticalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addComponent(topLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(topLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(topLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Project name:");
        jLabel1.setMinimumSize(new java.awt.Dimension(5, 1));

        projectNameTFD.setMinimumSize(new java.awt.Dimension(1, 3));

        jLabel8.setText("Group Id:");
        jLabel8.setMinimumSize(new java.awt.Dimension(3, 1));

        groupIdTFD.setMinimumSize(new java.awt.Dimension(1, 3));
        groupIdTFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupIdTFDActionPerformed(evt);
            }
        });

        jLabel9.setText("Artifact Id:");
        jLabel9.setMinimumSize(new java.awt.Dimension(5, 1));

        artifactIdTFD.setMinimumSize(new java.awt.Dimension(1, 3));

        jLabel10.setText("Version:");
        jLabel10.setMinimumSize(new java.awt.Dimension(3, 1));

        versionTFD.setMinimumSize(new java.awt.Dimension(1, 3));

        jLabel11.setText("jdk Version:");
        jLabel11.setMinimumSize(new java.awt.Dimension(5, 1));

        jLabel12.setText("MicroService Framework Version:");
        jLabel12.setMinimumSize(new java.awt.Dimension(11, 1));

        jLabel13.setText("Port Number:");
        jLabel13.setMinimumSize(new java.awt.Dimension(5, 1));

        portNumberTFD.setMinimumSize(new java.awt.Dimension(1, 3));
        portNumberTFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portNumberTFDActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)), "Project location"));
        jPanel1.setMinimumSize(new java.awt.Dimension(45, 18));

        proLocationCBox.setSelected(true);
        proLocationCBox.setText("Use default location");
        proLocationCBox.setMinimumSize(new java.awt.Dimension(7, 1));
        proLocationCBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                proLocationCBoxStateChanged(evt);
            }
        });
        proLocationCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proLocationCBoxActionPerformed(evt);
            }
        });

        openDirectoryButton.setText("Browse");
        openDirectoryButton.setEnabled(false);
        openDirectoryButton.setMinimumSize(new java.awt.Dimension(3, 1));
        openDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openDirectoryButtonActionPerformed(evt);
            }
        });

        proLocationTFD.setEnabled(false);
        proLocationTFD.setMinimumSize(new java.awt.Dimension(1, 3));

        jLabel14.setText("Location:");
        jLabel14.setMinimumSize(new java.awt.Dimension(10, 3));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proLocationCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(proLocationTFD, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(openDirectoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(proLocationCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(proLocationTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(openDirectoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)), "��Ŀ��Ϣ"));
        jPanel2.setMinimumSize(new java.awt.Dimension(61, 16));

        jLabel5.setText("��ϵ���䣺");
        jLabel5.setMinimumSize(new java.awt.Dimension(4, 1));

        emailTFD.setText("�����붫�����Ż��ӹ�˾�����ַ");
        emailTFD.setMinimumSize(new java.awt.Dimension(1, 3));
        emailTFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTFDActionPerformed(evt);
            }
        });

        departmentTFD.setMinimumSize(new java.awt.Dimension(1, 3));
        departmentTFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentTFDActionPerformed(evt);
            }
        });

        jLabel6.setText("���ţ�");
        jLabel6.setMinimumSize(new java.awt.Dimension(2, 1));

        proGroupTFD.setMinimumSize(new java.awt.Dimension(1, 3));
        proGroupTFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proGroupTFDActionPerformed(evt);
            }
        });

        jLabel7.setText("��Ŀ��");
        jLabel7.setMinimumSize(new java.awt.Dimension(4, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailTFD, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(proGroupTFD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(departmentTFD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departmentTFD, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proGroupTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jdkVersionBox.setMinimumSize(new java.awt.Dimension(3, 1));
        jdkVersionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdkVersionBoxActionPerformed(evt);
            }
        });

        frameworkVersionBox.setMinimumSize(new java.awt.Dimension(2, 1));
        frameworkVersionBox.setName(""); // NOI18N

        nextButton.setText("Next >");
        nextButton.setEnabled(false);
        nextButton.setMinimumSize(new java.awt.Dimension(3, 1));
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        finishButton.setText("Finish");
        finishButton.setEnabled(false);
        finishButton.setMinimumSize(new java.awt.Dimension(3, 1));

        backButton.setText("< Back");
        backButton.setEnabled(false);
        backButton.setMinimumSize(new java.awt.Dimension(3, 1));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.setMinimumSize(new java.awt.Dimension(3, 1));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(portNumberTFD, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(groupIdTFD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(artifactIdTFD, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(projectNameTFD, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(versionTFD, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jdkVersionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(frameworkVersionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(23, 23, 23))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(upperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(projectNameTFD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupIdTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artifactIdTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(versionTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jdkVersionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frameworkVersionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portNumberTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(84, 84, 84))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proLocationCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proLocationCBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proLocationCBoxActionPerformed

    private void emailTFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTFDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTFDActionPerformed

    private void departmentTFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentTFDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departmentTFDActionPerformed

    private void proGroupTFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proGroupTFDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proGroupTFDActionPerformed

    private void groupIdTFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupIdTFDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_groupIdTFDActionPerformed

    private void openDirectoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDirectoryButtonActionPerformed
        // Open a dialog for choosing a project location 
        openDirectory();
    }//GEN-LAST:event_openDirectoryButtonActionPerformed

    private void proLocationCBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_proLocationCBoxStateChanged
        getProLocationTFD().setEnabled(!proLocationCBox.isSelected());
        openDirectoryButton.setEnabled(!proLocationCBox.isSelected());
        reviewInputState();
    }//GEN-LAST:event_proLocationCBoxStateChanged
//���ȡ�����رմ���
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
//�����һ�����رյ�ǰ���ڣ�����һ������
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        
          java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                        setVisible(false);
                        if(settingsDialog_2==null){
                            settingsDialog_2=new SettingsDialog_2();
                            settingsDialog_2.setSettingsDialog_1(SettingsDialog_1.this);
                        }
                        settingsDialog_2.setSize((int) (getSCREENWIDTH()/3), (int) (getSCREENHEIGHT()/1.18));
                        settingsDialog_2.setLocation(getLocation());
                        settingsDialog_2.setVisible(true);
            }
        });
    }//GEN-LAST:event_nextButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void jdkVersionBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdkVersionBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdkVersionBoxActionPerformed

    private void portNumberTFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portNumberTFDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portNumberTFDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new SettingsDialog_1().setVisible(true);
            }
        });
    }
//��ʼ��ҳ��
    private void wfinit() {
        changeSwingLooksAndFeels();  //����ҳ����ͳߴ�
            setSize((int) (getSCREENWIDTH()/3), (int) (getSCREENHEIGHT()/1.18));
        /*upperPanel = new javax.swing.JPanel();
        topLabel1 = new javax.swing.JLabel();
        topLabel2 = new javax.swing.JLabel();
        topLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        projectNameTFD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        groupIdTFD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        artifactIdTFD = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        versionTFD = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        portNumberTFD = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        proLocationCBox = new javax.swing.JCheckBox();
        openDirectoryButton = new javax.swing.JButton();
        proLocationTFD = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        emailTFD = new javax.swing.JTextField();
        departmentTFD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        proGroupTFD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jdkVersionBox = new javax.swing.JComboBox();
        frameworkVersionBox = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        nextButton = new javax.swing.JButton();
        finishButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();*/
        FRAMEHEIGHT=getHeight();
        FRAMEWIDTH=getWidth();
       
        //���������ı���ؼ�������
        JTextField[] textFields={getProjectNameTFD(), getGroupIdTFD(), getArtifactIdTFD(), getVersionTFD(), getPortNumberTFD(), getProLocationTFD(), getEmailTFD(), getDepartmentTFD(), getProGroupTFD()};
        for(JTextField textField:textFields){  //Ϊ�ı���JTextField��Ӽ�����
            addListenerToTextField(textField);
            if(getSCREENHEIGHT()>800) {
                textField.setPreferredSize(new Dimension(FRAMEWIDTH/4*3, FRAMEHEIGHT/30));
            }
            else {
                textField.setPreferredSize(new Dimension((int)(FRAMEWIDTH/4), FRAMEHEIGHT/40));
                //textField.setPreferredSize(new Dimension(10, 10));
            }
            
        }
        if(getSCREENHEIGHT()>800) {
                upperPanel.setPreferredSize(new Dimension(FRAMEWIDTH, FRAMEHEIGHT/10));
                topLabel2.setPreferredSize(new Dimension((int)(upperPanel.getPreferredSize().width/2), (int)(upperPanel.getPreferredSize().height/2.5)));
            }
            else {
                upperPanel.setPreferredSize(new Dimension((int)(FRAMEWIDTH), FRAMEHEIGHT/11));
                topLabel2.setPreferredSize(new Dimension((int)(upperPanel.getPreferredSize().width/5*4), (int)(upperPanel.getPreferredSize().height/2.5)));
                topLabel3.setPreferredSize(new Dimension((int)(upperPanel.getPreferredSize().width/3), (int)(upperPanel.getPreferredSize().height/3)));
                getJdkVersionBox().setPreferredSize(new Dimension((int)(FRAMEWIDTH/4), FRAMEHEIGHT/40));
                getFrameworkVersionBox().setPreferredSize(new Dimension((int)(FRAMEWIDTH/5), FRAMEHEIGHT/40));
                getProLocationTFD().setPreferredSize(new Dimension((int)(FRAMEWIDTH/6), FRAMEHEIGHT/40));
                jPanel2.setPreferredSize(new Dimension((int)(FRAMEWIDTH/1.5), (int)(FRAMEHEIGHT/4.3)));  //��ť�ϱߵ����
                jPanel1.setPreferredSize(new Dimension((int)(FRAMEWIDTH/1.5), (int)(FRAMEHEIGHT/6.6)));  //������ĿĿ¼�����
        }
        
      //  initComponentsOnOwn();
        
        URL url=this.getClass().getResource("/microservice_framework/images/open_file.png");
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        if(getSCREENHEIGHT()<=800)
            image=image.getScaledInstance(35, -1, Image.SCALE_DEFAULT);
        topLabel3.setIcon(new ImageIcon(image));
        getEmailTFD().setText("");
        topLabel3.requestFocusInWindow();   //ʹ��һ���ı���JTextFieldʧȥ���㣬�Ӷ������Զ��������Ľ��������

        
        //����һ����ʱ�������ڸı��ı�������
        Timer timer=new Timer(500,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getProLocationTFD().setText(defaultPath);
            }
        });
        timer.start();
        //��ʼ�����������б��
        getJdkVersionBox().removeAllItems();
        getFrameworkVersionBox().removeAllItems();
        String[] jdkVersions={"1.7", "1.8"};
        String[] frameworkVersions={"1.0.0"};
        for(String s:jdkVersions){
            getJdkVersionBox().addItem(s);
        }
        for(String s:frameworkVersions){
            getFrameworkVersionBox().addItem(s);
        }
    }

    //Ϊ�ı���JTextField��Ӽ�����
    private void addListenerToTextField(JTextField textField){
        textField.addFocusListener(new FocusListener(){  //���������
            @Override
            public void focusGained(FocusEvent e) {
                reviewInputState();
            }
            @Override
            public void focusLost(FocusEvent e) {} 
        });
        Document doc=textField.getDocument();
        doc.addDocumentListener(new DocumentListener(){    //�ı�������
            @Override
            public void insertUpdate(DocumentEvent e) {
                reviewInputState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                reviewInputState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                reviewInputState();
            }
            
        });
    }
    
    /**
     * ����һ���������鿴������Ҫ�û�����Ŀؼ������ݻ�״̬����ͨ������
     * ��ǩ��ʾ������Ϣ
    **/
    private void reviewInputState(){
        //1. У����Ŀ����
        if(getProjectNameTFD().getText().trim()==null || getProjectNameTFD().getText().trim().equals("")){
            setIconForWrong();
            topLabel2.setText("Enter a project name."); //У�����
            nextButton.setEnabled(false);
            return;
        }else{  //�ǿ�ʱ��ҪУ����Ŀ���Ƿ����ظ�
            if(proLocationCBox.isSelected()){
                defaultPath=new File(".").getAbsolutePath();
                if(defaultPath.charAt(defaultPath.length()-1)=='.')
                    defaultPath=defaultPath.substring(0, defaultPath.length()-1);  //ȥ��ĩβ�Ķ��������ַ�
                if(defaultPath.charAt(defaultPath.length()-1)=='\\')
                    defaultPath=defaultPath.substring(0, defaultPath.length()-1);  //ȥ��ĩβ�Ķ��������ַ�
                
            }else{
               defaultPath= getProLocationTFD().getText();
               if(defaultPath==null) defaultPath="";
            }
                File file=new File(defaultPath+File.separator+getProjectNameTFD().getText());
                if(file.exists()){
                    setIconForWrong();
                    topLabel2.setText("A project with this name already exists."); //У�����
                    nextButton.setEnabled(false);
                    return;
                }
           
        }
        //2. У��GroupId
        if(getGroupIdTFD().getText()==null||getGroupIdTFD().getText().equals("")){
            setIconForWrong();
            topLabel2.setText("Enter a group id for the artifact."); //У�����
            nextButton.setEnabled(false);
            return;
         }
        //3. У��Artifact Id
       if(getArtifactIdTFD().getText()==null||getArtifactIdTFD().getText().equals("")){
            setIconForWrong();
            topLabel2.setText("Enter an artifact id."); //У�����
            nextButton.setEnabled(false);
            return;
       }
        //4. У��Project Version
        if(getVersionTFD().getText()==null||getVersionTFD().getText().equals("")){
            setIconForWrong();
            topLabel2.setText("Enter a project version."); //У�����
            nextButton.setEnabled(false);
            return;
        }
        //5. У��Port Number
        if(getPortNumberTFD().getText()==null||getPortNumberTFD().getText().equals("")){
            setIconForWrong();
            topLabel2.setText("Enter a port number."); //У�����
            nextButton.setEnabled(false);
            return;
        }
        //6. У����Ŀ·��Project Location
        if(getProLocationTFD().getText()==null||getProLocationTFD().getText().equals("")){
            setIconForWrong();
            topLabel2.setText("Enter a project location."); //У�����
            nextButton.setEnabled(false);
            return;
        }
       
        //9. У������
        if(getEmailTFD().getText()==null||getEmailTFD().getText().equals("")){
            setIconForWrong();
            topLabel2.setText(EMAILTOOLTIP);
            nextButton.setEnabled(false);
            return;
        }else if(!(emailTFD.getText().matches("^\\w+@\\w+\\.\\w+$"))){
            setIconForWrong();
            topLabel2.setText("�����ʽ����ȷ"); 
            nextButton.setEnabled(false);
            return;
        }                                            //У�����
        //10. У�鲿��
        if(getDepartmentTFD().getText()==null||getDepartmentTFD().getText().equals("")){
            setIconForWrong();
            topLabel2.setText("�����û��������š�"); //У�����
            nextButton.setEnabled(false);
        }else{
            setIconForRight();
            topLabel2.setText("Configure MicroService project settings");
            nextButton.setEnabled(true);
        }
    }
    /**
     * Ϊ��ʾ��ǩ���ô���ͼ��
     */
    public void setIconForWrong(){
                URL url=this.getClass().getResource("/microservice_framework/images/wrong_16px_.png");
                Image image = Toolkit.getDefaultToolkit().getImage(url);
                topLabel2.setIcon(new ImageIcon(image));
    }
    public void setIconForRight(){
                URL url=this.getClass().getResource("/microservice_framework/images/yes_ok_16px.png");
                Image image = Toolkit.getDefaultToolkit().getImage(url);
                topLabel2.setIcon(new ImageIcon(image));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField artifactIdTFD;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField departmentTFD;
    private javax.swing.JTextField emailTFD;
    private javax.swing.JButton finishButton;
    private javax.swing.JComboBox frameworkVersionBox;
    private javax.swing.JTextField groupIdTFD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox jdkVersionBox;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton openDirectoryButton;
    private javax.swing.JTextField portNumberTFD;
    private javax.swing.JTextField proGroupTFD;
    private javax.swing.JCheckBox proLocationCBox;
    private javax.swing.JTextField proLocationTFD;
    private javax.swing.JTextField projectNameTFD;
    private javax.swing.JLabel topLabel1;
    private javax.swing.JLabel topLabel2;
    private javax.swing.JLabel topLabel3;
    private javax.swing.JPanel upperPanel;
    private javax.swing.JTextField versionTFD;
    // End of variables declaration//GEN-END:variables
//Open a dialog for choosing a project location
    private void openDirectory() {
        JFileChooser chooser=new JFileChooser();
        File file=new File(getProLocationTFD().getText());
        if(file.exists() && file.isDirectory()){
            chooser.setCurrentDirectory(file);
        }else{
            chooser.setCurrentDirectory(new File("."));
        }
        chooser.setDialogTitle("ѡ����Ŀ·���Ի���");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.showOpenDialog(this);
        file=chooser.getSelectedFile();
        if(file==null) return;
        defaultPath=file.getPath();
      //  proLocationTFD.setText(path);
      
    }

    private void initComponentsOnOwn() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New MicroService Project");
        setMinimumSize(new java.awt.Dimension(6, 7));

        upperPanel.setMinimumSize(new java.awt.Dimension(7, 1));

        topLabel1.setFont(new java.awt.Font("����", 1, 12)); // NOI18N
        topLabel1.setText("MicroService Project");
        topLabel1.setMinimumSize(new java.awt.Dimension(9, 1));

        topLabel2.setText("Configure MicroService project settings");
        topLabel2.setMinimumSize(new java.awt.Dimension(15, 1));

        topLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/open_file.png"))); // NOI18N
        topLabel3.setMinimumSize(new java.awt.Dimension(1, 1));

        javax.swing.GroupLayout upperPanelLayout = new javax.swing.GroupLayout(upperPanel);
        upperPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(topLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(topLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        upperPanelLayout.setVerticalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addComponent(topLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(topLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(topLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Project name:");
        jLabel1.setMinimumSize(new java.awt.Dimension(5, 1));

        getProjectNameTFD().setMinimumSize(new java.awt.Dimension(1, 3));

        jLabel8.setText("Group Id:");
        jLabel8.setMinimumSize(new java.awt.Dimension(3, 1));

        getGroupIdTFD().setMinimumSize(new java.awt.Dimension(1, 3));
        getGroupIdTFD().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupIdTFDActionPerformed(evt);
            }
        });

        jLabel9.setText("Artifact Id:");
        jLabel9.setMinimumSize(new java.awt.Dimension(5, 1));

        getArtifactIdTFD().setMinimumSize(new java.awt.Dimension(1, 3));

        jLabel10.setText("Version:");
        jLabel10.setMinimumSize(new java.awt.Dimension(3, 1));

        getVersionTFD().setMinimumSize(new java.awt.Dimension(1, 3));

        jLabel11.setText("jdk Version:");
        jLabel11.setMinimumSize(new java.awt.Dimension(5, 1));

        jLabel12.setText("MicroService Framework Version:");
        jLabel12.setMinimumSize(new java.awt.Dimension(11, 1));

        jLabel13.setText("Port Number:");
        jLabel13.setMinimumSize(new java.awt.Dimension(5, 1));

        getPortNumberTFD().setMinimumSize(new java.awt.Dimension(1, 3));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)), "Project location"));
        jPanel1.setMinimumSize(new java.awt.Dimension(45, 18));

        proLocationCBox.setSelected(true);
        proLocationCBox.setText("Use default location");
        proLocationCBox.setMinimumSize(new java.awt.Dimension(7, 1));
        proLocationCBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                proLocationCBoxStateChanged(evt);
            }
        });
        proLocationCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proLocationCBoxActionPerformed(evt);
            }
        });

        openDirectoryButton.setText("Browse");
        openDirectoryButton.setEnabled(false);
        openDirectoryButton.setMinimumSize(new java.awt.Dimension(3, 1));
        openDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openDirectoryButtonActionPerformed(evt);
            }
        });

        getProLocationTFD().setEnabled(false);
        getProLocationTFD().setMinimumSize(new java.awt.Dimension(1, 3));

        jLabel14.setText("Location:");
        jLabel14.setMinimumSize(new java.awt.Dimension(10, 3));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(proLocationCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(getProLocationTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(openDirectoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(proLocationCBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(getProLocationTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(openDirectoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)), "��Ŀ��Ϣ"));
        jPanel2.setMinimumSize(new java.awt.Dimension(61, 16));

        jLabel5.setText("��ϵ���䣺");
        jLabel5.setMinimumSize(new java.awt.Dimension(4, 1));

        getEmailTFD().setText("�����붫�����Ż��ӹ�˾�����ַ");
        getEmailTFD().setMinimumSize(new java.awt.Dimension(1, 3));
        getEmailTFD().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTFDActionPerformed(evt);
            }
        });

        getDepartmentTFD().setMinimumSize(new java.awt.Dimension(1, 3));
        getDepartmentTFD().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentTFDActionPerformed(evt);
            }
        });

        jLabel6.setText("���ţ�");
        jLabel6.setMinimumSize(new java.awt.Dimension(2, 1));

        getProGroupTFD().setMinimumSize(new java.awt.Dimension(1, 3));
        getProGroupTFD().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proGroupTFDActionPerformed(evt);
            }
        });

        jLabel7.setText("��Ŀ��");
        jLabel7.setMinimumSize(new java.awt.Dimension(4, 1));
 
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getDepartmentTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getEmailTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getProGroupTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getDepartmentTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getProGroupTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getEmailTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getJdkVersionBox().setMinimumSize(new java.awt.Dimension(3, 1));

        getFrameworkVersionBox().setMinimumSize(new java.awt.Dimension(2, 1));
        getFrameworkVersionBox().setName(""); // NOI18N

        nextButton.setText("Next >");
        nextButton.setEnabled(false);
        nextButton.setMinimumSize(new java.awt.Dimension(3, 1));
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        finishButton.setText("Finish");
        finishButton.setEnabled(false);
        finishButton.setMinimumSize(new java.awt.Dimension(3, 1));

        backButton.setText("< Back");
        backButton.setEnabled(false);
        backButton.setMinimumSize(new java.awt.Dimension(3, 1));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.setMinimumSize(new java.awt.Dimension(3, 1));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getArtifactIdTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                            .addComponent(getGroupIdTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                            .addComponent(getProjectNameTFD(), javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(getFrameworkVersionBox(), 0, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(getJdkVersionBox(), 0, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(getPortNumberTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(getVersionTFD(), javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getProjectNameTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(getGroupIdTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(getArtifactIdTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(getVersionTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJdkVersionBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getFrameworkVersionBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(getPortNumberTFD(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(finishButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

    }

    /**
     * @return the emailTFD
     */
    public javax.swing.JTextField getEmailTFD() {
        return emailTFD;
    }

    /**
     * @param emailTFD the emailTFD to set
     */
    public void setEmailTFD(javax.swing.JTextField emailTFD) {
        this.emailTFD = emailTFD;
    }
}
