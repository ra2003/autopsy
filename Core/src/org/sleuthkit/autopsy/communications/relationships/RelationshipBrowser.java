/*
 * Autopsy Forensic Browser
 *
 * Copyright 2019 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obt ain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.communications.relationships;

import java.awt.Component;
import javax.swing.JPanel;
import org.openide.util.Lookup;
import org.sleuthkit.autopsy.communications.ModifiableProxyLookup;

/**
 * Displays the Relationship information for the currently selected accounts.
 *
 */
public final class RelationshipBrowser extends JPanel implements Lookup.Provider {

    private SelectionInfo currentSelection;
    
    private final MessagesViewer messagesViewer;
    private final ContactsViewer contactsViewer;
    private final SummaryViewer summaryViewer;
    private final MediaViewer mediaViewer;
    
    private final ModifiableProxyLookup proxyLookup;

    /**
     * Creates new form RelationshipBrowser
     */
    public RelationshipBrowser() {
        messagesViewer = new MessagesViewer();
        contactsViewer = new ContactsViewer();
        summaryViewer = new SummaryViewer();
        mediaViewer = new MediaViewer();
        
        proxyLookup = new ModifiableProxyLookup(messagesViewer.getLookup());
        
         initComponents();
        
        tabPane.add(summaryViewer.getDisplayName(), summaryViewer);
        tabPane.add(messagesViewer.getDisplayName(), messagesViewer);
        tabPane.add(contactsViewer.getDisplayName(), contactsViewer);
        tabPane.add(mediaViewer.getDisplayName(), mediaViewer);
        
        
    }

    /**
     * Sets the value of currentSelection and passes the SelectionInfo onto the
     * currently selected or visible tab.
     *
     * @param info Currently selected account nodes
     */
    public void setSelectionInfo(SelectionInfo info) {
        currentSelection = info;
        ((RelationshipsViewer) tabPane.getSelectedComponent()).setSelectionInfo(info);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        tabPane = new javax.swing.JTabbedPane();

        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tabPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPaneStateChanged(evt);
            }
        });
        scrollPane.setViewportView(tabPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPaneStateChanged
        if(currentSelection != null) {
            ((RelationshipsViewer) tabPane.getSelectedComponent()).setSelectionInfo(currentSelection);
        }
        
        Component selectedComponent = tabPane.getSelectedComponent();
        if(selectedComponent instanceof Lookup.Provider) {
            Lookup lookup = ((Lookup.Provider)selectedComponent).getLookup();
            proxyLookup.setNewLookups(lookup);
        }
    }//GEN-LAST:event_tabPaneStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTabbedPane tabPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public Lookup getLookup() {
        return proxyLookup;
    }
}