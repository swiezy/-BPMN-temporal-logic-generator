/*
 * Copyright (c) 2015, pl.edu.agh
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package pl.edu.agh.kis.core.panels;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.openide.util.Exceptions;
import pl.edu.agh.kis.core.PatternExtractor;
import pl.edu.agh.kis.core.data.BPMNParser;
import pl.edu.agh.kis.core.data.Edge;
import pl.edu.agh.kis.core.data.AtomNode;
import pl.edu.agh.kis.exceptions.BodyArgumentsException;
import pl.edu.agh.kis.exceptions.DuplicateDefinitionException;
import pl.edu.agh.kis.core.data.LogicFormula;
import pl.edu.agh.kis.core.data.Node;
import pl.edu.agh.kis.core.data.StructNodeType;
import pl.edu.agh.kis.core.data.TemporalLogicDefinition;
import pl.edu.agh.kis.core.data.TemporalLogicParser;
import pl.edu.agh.kis.core.utilities.BpmnFilter;
import pl.edu.agh.kis.core.utilities.ColorStyledDocument;
import pl.edu.agh.kis.core.utilities.GraphUtils;
import pl.edu.agh.kis.exceptions.BadHeaderException;
import pl.edu.agh.kis.exceptions.NoLogicPatternDefinition;
import pl.edu.agh.kis.exceptions.BadPatternException;

/**
 *
 * @author Jakub Piotrowski
 * Main panel, which contains whole application functionality
 */
public class GeneratorPanel extends javax.swing.JPanel {

    private BufferedWriter writer;
    private File bpmnFile;
    private Graph<Node, Edge> graph;
    private PatternExtractor patternExtract;

    /**
     * Creates new form GeneratorPanel
     */
    public GeneratorPanel() {
        try {
            initComponents();
            generateButton.setEnabled(false);
            patternExtract = new PatternExtractor();
            logicFormulasTextPane.setText(org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.logicFormulasTextPane.text")); // NOI18N

        }catch(Exception e){
            e.printStackTrace(System.out);
            throw e;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pathTextField = new javax.swing.JTextField();
        chooseBpmnButton = new javax.swing.JButton();
        graphPanel = new ImagePanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patternsTextPane = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        generatedLogicTextPane = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        logicFormulasTextPane = new javax.swing.JTextPane();
        chooseLogicFormulasFileButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        pathTextField.setText(org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.pathTextField.text")); // NOI18N
        pathTextField.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        add(pathTextField, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(chooseBpmnButton, org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.chooseBpmnButton.text")); // NOI18N
        chooseBpmnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseBpmnButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        add(chooseBpmnButton, gridBagConstraints);

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        add(graphPanel, gridBagConstraints);

        patternsTextPane.setText(org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.patternsTextPane.text")); // NOI18N
        jScrollPane2.setViewportView(patternsTextPane);
        patternsTextPane.setDocument(new ColorStyledDocument(false));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        add(jScrollPane2, gridBagConstraints);

        jScrollPane3.setViewportView(generatedLogicTextPane);
        generatedLogicTextPane.setDocument(new ColorStyledDocument(true));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 10);
        add(jScrollPane3, gridBagConstraints);

        logicFormulasTextPane.setText(org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.logicFormulasTextPane.text")); // NOI18N
        jScrollPane1.setViewportView(logicFormulasTextPane);
        logicFormulasTextPane.setDocument(new ColorStyledDocument(true));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(jScrollPane1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(chooseLogicFormulasFileButton, org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.chooseLogicFormulasFileButton.text")); // NOI18N
        chooseLogicFormulasFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseLogicFormulasFileButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        add(chooseLogicFormulasFileButton, gridBagConstraints);

        generateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(generateButton, org.openide.util.NbBundle.getMessage(GeneratorPanel.class, "GeneratorPanel.generateButton.text")); // NOI18N
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(generateButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void chooseBpmnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseBpmnButtonActionPerformed
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new BpmnFilter());
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            bpmnFile = fc.getSelectedFile();
            pathTextField.setText(bpmnFile.getAbsolutePath());

            generateButton.setEnabled(true);
            generateButtonActionPerformed(null);
        }
    }//GEN-LAST:event_chooseBpmnButtonActionPerformed

    public static String denull(String s){
        return (s==null?"":s);
    }
    
    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        generatedLogicTextPane.setText("");
        patternsTextPane.setText("");
        BPMNParser parser = new BPMNParser();
        graph = parser.parseBPMN(bpmnFile);
        paintGraph();
        try {
            Graph g2 = parser.parseBPMN(bpmnFile);
            Node n = patternExtract.extractPatterns(g2, GraphUtils.START_NODE);
            patternsTextPane.setText(n.toString());

            TemporalLogicParser logicParser = new TemporalLogicParser();
            Map<StructNodeType, LogicFormula> def;
            try {
                def = logicParser.parse(logicFormulasTextPane.getText());
                TemporalLogicDefinition.setLogicDef(def);
                String tempLogic = n.toTemporalLogic();
                generatedLogicTextPane.setText(tempLogic);
            } catch (BodyArgumentsException e) {
                JOptionPane.showMessageDialog(this, "Bad arguments " + denull(e.getMessage()) + " line:" + e.getLine(), "Parsing temporal logic error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (DuplicateDefinitionException e) {
                JOptionPane.showMessageDialog(this, "Duplicated Definition " + denull(e.getMessage()) + " line:" + e.getLine(), "Parsing temporal logic error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (BadHeaderException e) {
                JOptionPane.showMessageDialog(this, "Bad logic formula headers "+denull(e.getMessage()) + " line:" + e.getLine(), "Parsing temporal logic error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (NoLogicPatternDefinition e) {
                JOptionPane.showMessageDialog(this,"Missing pattern logic definition"+ denull(e.getMessage()), "Missing logic definition", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        } catch (UnexpectedException e) {
            JOptionPane.showMessageDialog(this, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (BadPatternException e) {
            JOptionPane.showMessageDialog(this, "Error", e.toString(), JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_generateButtonActionPerformed

    private Graph cloneGraph(Graph<Node, Edge> src) {
        Graph<Node, Edge> dest = new DirectedSparseGraph();
        for (Node v : src.getVertices()) {
            dest.addVertex(v);
        }

        for (Edge e : src.getEdges()) {
            dest.addEdge(e, src.getIncidentVertices(e));
        }
        return dest;
    }

    private void chooseLogicFormulasFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseLogicFormulasFileButtonActionPerformed
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            loadLogicalFormulas(Paths.get(file.getAbsolutePath()));
        }
    }//GEN-LAST:event_chooseLogicFormulasFileButtonActionPerformed

    private void loadLogicalFormulas(Path path) {
        StyledDocument doc = logicFormulasTextPane.getStyledDocument();
        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
        Consumer<String> textConsumer = (String line) -> {
            try {
                doc.insertString(doc.getLength(), line + "\n", null);
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }
        };
        try {
            Files.lines(path).forEachOrdered(textConsumer);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void paintGraph() {
        // get graphPanel dimension
        Dimension dim = graphPanel.getSize();

        // create layout
        // Jung layouts - http://jung.sourceforge.net/doc/api/edu/uci/ics/jung/algorithms/layout/package-summary.html
        Layout<AtomNode, Edge> layout = new FRLayout(graph);
        layout.setSize(new Dimension((int) dim.getWidth() - (int) (dim.getWidth() * 0.05), (int) dim.getHeight() - (int) (dim.getHeight() * 0.05)));

        // remove old JPanel
        this.remove(graphPanel);

        // create and add new graphPanel
        graphPanel = new BasicVisualizationServer<AtomNode, Edge>(layout);
        ((BasicVisualizationServer) graphPanel).getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        graphPanel.addComponentListener(new ComponentAdapter() {
            private Dimension last_size = null;

            @Override
            public void componentResized(ComponentEvent evt) {
                Dimension new_size = evt.getComponent().getSize();
                if (this.last_size == null || !new_size.equals(this.last_size)) {
                    Layout<AtomNode, Edge> layout = ((BasicVisualizationServer) graphPanel).getGraphLayout();
                    try {
                        layout.setSize(new_size);
                        ((BasicVisualizationServer) graphPanel).setGraphLayout(layout);
                        this.last_size = new_size;
                    } catch (UnsupportedOperationException ex) {
                        // Ignore...
                    }
                }
            }
        });
        graphPanel.setPreferredSize(dim);
        GridBagConstraints gridBagConstraints;

        GroupLayout graphPanelLayout = new GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 12.0;
        gridBagConstraints.insets = new Insets(10, 5, 5, 10);
        add(graphPanel, gridBagConstraints);

        this.revalidate();
        this.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseBpmnButton;
    private javax.swing.JButton chooseLogicFormulasFileButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JTextPane generatedLogicTextPane;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane logicFormulasTextPane;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JTextPane patternsTextPane;
    // End of variables declaration//GEN-END:variables
}
