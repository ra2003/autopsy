/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011 Basis Technology Corp.
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
package org.sleuthkit.autopsy.corecomponents;

import java.awt.Component;
import java.awt.Cursor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import org.openide.nodes.Node;
import org.openide.util.lookup.ServiceProvider;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataContentViewer;
import org.sleuthkit.autopsy.datamodel.DataConversion;
import org.sleuthkit.datamodel.Content;
import org.sleuthkit.datamodel.TskException;

/**
 * Hex view of file contents.
 */
@ServiceProvider(service = DataContentViewer.class)
public class DataContentViewerHex extends javax.swing.JPanel implements DataContentViewer {

    private static long currentOffset = 0;
    private static long pageLength = 10240;
    private static int currentPage = 1;
    private Content dataSource;
    // for error handling
    private String className = this.getClass().toString();

    /** Creates new form DataContentViewerHex */
    public DataContentViewerHex() {
        initComponents();
        this.resetComponent();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hexViewerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputViewPane = new JTextPane(){
            public boolean getScrollableTracksViewportWidth() {
                return (getSize().width < 700);
            }};
            this.outputViewPane.setBackground(new java.awt.Color(255, 255, 255)); // to make sure the background color is white
            totalPageLabel = new javax.swing.JLabel();
            ofLabel = new javax.swing.JLabel();
            currentPageLabel = new javax.swing.JLabel();
            pageLabel = new javax.swing.JLabel();
            prevPageButton = new javax.swing.JButton();
            nextPageButton = new javax.swing.JButton();
            pageLabel2 = new javax.swing.JLabel();

            jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

            outputViewPane.setEditable(false);
            outputViewPane.setFont(new java.awt.Font("Courier New", 0, 11)); // NOI18N
            outputViewPane.setMinimumSize(new java.awt.Dimension(700, 20));
            outputViewPane.setPreferredSize(new java.awt.Dimension(700, 400));
            jScrollPane1.setViewportView(outputViewPane);

            totalPageLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.totalPageLabel.text_1")); // NOI18N

            ofLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.ofLabel.text_1")); // NOI18N

            currentPageLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.currentPageLabel.text_1")); // NOI18N

            pageLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.pageLabel.text_1")); // NOI18N

            prevPageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/corecomponents/arrow_left.gif"))); // NOI18N
            prevPageButton.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.prevPageButton.text")); // NOI18N
            prevPageButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    prevPageButtonActionPerformed(evt);
                }
            });

            nextPageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/corecomponents/arrow_right.gif"))); // NOI18N
            nextPageButton.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.nextPageButton.text")); // NOI18N
            nextPageButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    nextPageButtonActionPerformed(evt);
                }
            });

            pageLabel2.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.pageLabel2.text")); // NOI18N

            javax.swing.GroupLayout hexViewerPanelLayout = new javax.swing.GroupLayout(hexViewerPanel);
            hexViewerPanel.setLayout(hexViewerPanelLayout);
            hexViewerPanelLayout.setHorizontalGroup(
                hexViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(hexViewerPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pageLabel)
                    .addGap(18, 18, 18)
                    .addComponent(currentPageLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(ofLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(totalPageLabel)
                    .addGap(51, 51, 51)
                    .addComponent(pageLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(prevPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(nextPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(374, Short.MAX_VALUE))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
            );
            hexViewerPanelLayout.setVerticalGroup(
                hexViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(hexViewerPanelLayout.createSequentialGroup()
                    .addGroup(hexViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pageLabel)
                        .addGroup(hexViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentPageLabel)
                            .addComponent(ofLabel)
                            .addComponent(totalPageLabel))
                        .addComponent(pageLabel2)
                        .addComponent(nextPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(prevPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 622, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(hexViewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 411, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(hexViewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void prevPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevPageButtonActionPerformed
        //@@@ this is part of the code dealing with the data viewer. could be copied/removed to implement the scrollbar
        currentOffset -= pageLength;
        currentPage = currentPage - 1;
        currentPageLabel.setText(Integer.toString(currentPage));
        setDataView(dataSource, currentOffset, false);
    }//GEN-LAST:event_prevPageButtonActionPerformed

    private void nextPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageButtonActionPerformed
        //@@@ this is part of the code dealing with the data viewer. could be copied/removed to implement the scrollbar
        currentOffset += pageLength;
        currentPage = currentPage + 1;
        currentPageLabel.setText(Integer.toString(currentPage));
        setDataView(dataSource, currentOffset, false);
    }//GEN-LAST:event_nextPageButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentPageLabel;
    private javax.swing.JPanel hexViewerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextPageButton;
    private javax.swing.JLabel ofLabel;
    private javax.swing.JTextPane outputViewPane;
    private javax.swing.JLabel pageLabel;
    private javax.swing.JLabel pageLabel2;
    private javax.swing.JButton prevPageButton;
    private javax.swing.JLabel totalPageLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Sets the DataView (The tabbed panel)
     *
     * @param dataSource  the content that want to be shown
     * @param offset      the starting offset
     * @param reset       whether to reset the dataView or not
     */
    public void setDataView(Content dataSource, long offset, boolean reset) {
        // change the cursor to "waiting cursor" for this operation
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            try {
                this.dataSource = dataSource;
                byte[] data;

                if (!reset && dataSource.getSize() > 0) {
                    data = dataSource.read(offset, pageLength); // read the data
                } else {
                    // empty file
                    data = null;
                }

                // I set the -1 to for empty node or directory
                if (reset) {
                    data = null;
                }

                // set the data on the bottom and show it
                Boolean setVisible = false;

                if (data != null) {
                    setVisible = true;
                }

                // disable or enable the next button
                if (!reset && offset + pageLength < dataSource.getSize()) {
                    nextPageButton.setEnabled(true);
                } else {
                    nextPageButton.setEnabled(false);
                }

                if (offset == 0) {
                    prevPageButton.setEnabled(false);
                    currentPage = 1; // reset the page number
                } else {
                    prevPageButton.setEnabled(true);
                }

                if (setVisible) {
                    int totalPage = (int) (dataSource.getSize() / pageLength) + 1;
                    totalPageLabel.setText(Integer.toString(totalPage));
                    currentPageLabel.setText(Integer.toString(currentPage));
                    setComponentsVisibility(true); // shows the components that not needed

                    // set the output view
                    outputViewPane.setText(DataConversion.byteArrayToHex(data, pageLength, offset, outputViewPane.getFont()));
                } else {
                    // reset or hide the labels
                    totalPageLabel.setText("");
                    currentPageLabel.setText("");
                    outputViewPane.setText(""); // reset the output view
                    setComponentsVisibility(false); // hides the components that not needed
                }

                outputViewPane.moveCaretPosition(0);
            } catch (TskException ex) {
                // TODO: maybe make this error bubble up further
                Logger.getLogger(this.className).log(Level.WARNING, "Error while trying to show the hex content.", ex);
            }
        } finally {
            this.setCursor(null);
        }
    }

    @Override
    public void setNode(Node selectedNode) {
        if (selectedNode != null) {
            Content content = (selectedNode).getLookup().lookup(Content.class);
            if (content != null) {
                this.setDataView(content, 0, false);
                return;
            }
        }

        this.setDataView(null, 0, true);
    }

    @Override
    public String getTitle() {
        return "Hex View";
    }
    
    @Override
    public String getToolTip() {
        return "Displays the binary contents of a file as hexidecimal, with "
                + "bytes that are displayable as ASCII characters on the right.";
    }

    @Override
    public DataContentViewer getInstance() {
        return new DataContentViewerHex();
    }

    @Override
    public void resetComponent() {
        // clear / reset the fields
        this.dataSource = null;
        currentPageLabel.setText("");
        totalPageLabel.setText("");
        prevPageButton.setEnabled(false);
        nextPageButton.setEnabled(false);
        setComponentsVisibility(false); // hides the components that not needed
    }

    /**
     * To set the visibility of specific components in this class.
     *
     * @param isVisible  whether to show or hide the specific components
     */
    private void setComponentsVisibility(boolean isVisible) {
        currentPageLabel.setVisible(isVisible);
        totalPageLabel.setVisible(isVisible);
        ofLabel.setVisible(isVisible);
        prevPageButton.setVisible(isVisible);
        nextPageButton.setVisible(isVisible);
        pageLabel.setVisible(isVisible);
        pageLabel2.setVisible(isVisible);
    }

    @Override
    public boolean isSupported(Node node) {
        return true;
    }
    
    @Override
    public boolean isPreferred(Node node, boolean isSupported) {
        return false;
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
