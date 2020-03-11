/*
 * Autopsy Forensic Browser
 *
 * Copyright 2019 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.contentviewers.contextviewer;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.sleuthkit.autopsy.casemodule.Case;
import org.sleuthkit.autopsy.casemodule.NoCurrentCaseException;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataContentViewer;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.directorytree.DirectoryTreeTopComponent;
import org.sleuthkit.datamodel.AbstractFile;
import org.sleuthkit.datamodel.BlackboardArtifact;
import static org.sleuthkit.datamodel.BlackboardArtifact.ARTIFACT_TYPE.TSK_ASSOCIATED_OBJECT;
import org.sleuthkit.datamodel.BlackboardAttribute;
import org.sleuthkit.datamodel.SleuthkitCase;
import org.sleuthkit.datamodel.TskCoreException;

/**
 * Displays additional context for the selected file, such as its source, and
 * usage, if known.
 *
 */
public final class ContextUsagePanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ContextUsagePanel.class.getName());
    private static final int ARTIFACT_STR_MAX_LEN = 1024;
    private static final int ATTRIBUTE_STR_MAX_LEN = 200;

    // defines a list of artifacts that provide context for a file
    private static final List<BlackboardArtifact.ARTIFACT_TYPE> SOURCE_CONTEXT_ARTIFACTS = new ArrayList<>();
    static {
        SOURCE_CONTEXT_ARTIFACTS.add(TSK_ASSOCIATED_OBJECT);
    }

    private BlackboardArtifact sourceContextArtifact;

    /**
     * Creates new form ContextViewer
     */
    public ContextUsagePanel(String sourceName, String sourceText, BlackboardArtifact associatedArtifact, Boolean showUsageHeading) {

        initComponents();
        sourceContextArtifact = associatedArtifact;
        setUsageName(sourceName);
        setUsageText(sourceText);
        showUsageLabel(showUsageHeading);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jUsageGoToResultButton = new javax.swing.JButton();
        jUsageLabel = new javax.swing.JLabel();
        jUsageNameLabel = new javax.swing.JLabel();
        jUsageTextLabel = new javax.swing.JLabel();
        jBlankLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(495, 120));

        org.openide.awt.Mnemonics.setLocalizedText(jUsageGoToResultButton, org.openide.util.NbBundle.getMessage(ContextUsagePanel.class, "ContextUsagePanel.jUsageGoToResultButton.text")); // NOI18N
        jUsageGoToResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsageGoToResultButtonActionPerformed(evt);
            }
        });

        jUsageLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jUsageLabel, org.openide.util.NbBundle.getMessage(ContextUsagePanel.class, "ContextUsagePanel.jUsageLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jUsageNameLabel, org.openide.util.NbBundle.getMessage(ContextUsagePanel.class, "ContextUsagePanel.jUsageNameLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jUsageTextLabel, org.openide.util.NbBundle.getMessage(ContextUsagePanel.class, "ContextUsagePanel.jUsageTextLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jBlankLabel, org.openide.util.NbBundle.getMessage(ContextUsagePanel.class, "ContextUsagePanel.jBlankLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jUsageLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBlankLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUsageNameLabel)
                        .addGap(36, 36, 36)
                        .addComponent(jUsageTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jUsageGoToResultButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jUsageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUsageTextLabel)
                    .addComponent(jUsageNameLabel)
                    .addComponent(jBlankLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jUsageGoToResultButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jUsageGoToResultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUsageGoToResultButtonActionPerformed
        final DirectoryTreeTopComponent dtc = DirectoryTreeTopComponent.findInstance();

        // Navigate to the source context artifact.
        if (sourceContextArtifact != null) {
            dtc.viewArtifact(sourceContextArtifact);
        }
    }//GEN-LAST:event_jUsageGoToResultButtonActionPerformed

    /**
     * Sets the usage label string.
     *
     * @param nameLabel String value for usage label.
     */
    private void setUsageName(String nameLabel) {
        jUsageNameLabel.setText(nameLabel);
    }

    /**
     * Sets the Usage text string.
     *
     * @param text String value for Usage text.
     */
    private void setUsageText(String text) {
        jUsageTextLabel.setText(text);
        showUsageButton(!text.isEmpty());
        showUsageText(true);
    }

    private void showUsageLabel(boolean show) {
        if (!show) {
            jUsageLabel.setText(" ");
        }
        jUsageLabel.setVisible(show);        
    }
    
    private void showUsageText(boolean show) {
        jUsageTextLabel.setVisible(show);
    }
    
    private void showUsageButton(boolean show) {
        jUsageGoToResultButton.setEnabled(show);        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBlankLabel;
    private javax.swing.JButton jUsageGoToResultButton;
    private javax.swing.JLabel jUsageLabel;
    private javax.swing.JLabel jUsageNameLabel;
    private javax.swing.JLabel jUsageTextLabel;
    // End of variables declaration//GEN-END:variables
}
