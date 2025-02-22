/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.cyber.cyberbotsprojects.ui;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import org.ufal.cyber.cyberbotsprojects.project.CyberBotsProject;

/**
 * Árvore de projeto CyberBots.
 * 
 * @author Matheus Pedro (UFAL)
 */
public class CyberBotsLogicalView implements LogicalViewProvider {
    
    private final CyberBotsProject project;
    
    public CyberBotsLogicalView(CyberBotsProject project) {
        this.project = project;
    }

    @Override
    public Node createLogicalView() {
        //Get the robots directory, creating if deleted:
        FileObject robots = project.getRobotsFolder(true);

        //Get the DataObject that represents it:
        DataFolder robotDataObject =
                DataFolder.findFolder(robots);

        //Get its default node—we'll wrap our node around it to change the
        //display name, icon, etc:
        Node realRobotsFolderNode = robotDataObject.getNodeDelegate();

        //This FilterNode will be our project node:
        return new CyberBotsProjectNode(realRobotsFolderNode, project);
    }

    @Override
    public Node findPath(Node node, Object o) {
        //leave unimplemented for now
        return null;
    }
    
    private static class CyberBotsProjectNode extends AbstractNode {
        
        private final CyberBotsProject project;
        
        public CyberBotsProjectNode(Node node, CyberBotsProject project) {
            super(new CyberBotsChildren(node, project), Lookups.singleton(project));
            this.project = project;
        }
        
        @Override
        public Image getIcon(int type) {
            return ImageUtilities.loadImage(
                    "org/ufal/cyber/cyberbotsprojects/resources/cyberbots_project_icon_16.png");
        }
        
        @Override
        public Image getOpenedIcon(int type) {
            return getIcon(type);
        }
        
        @Override
        public String getDisplayName() {
            return project.getProjectDirectory().getName();
        }
    }
    
    private static class CyberBotsChildren extends Children.Keys<String> {
        
        private final CyberBotsProject project;
        private final Node node;
        
        public CyberBotsChildren(Node node, CyberBotsProject project) {
            this.project = project;
            this.node = node;
        }
        
        @Override
        protected void addNotify() {
            setKeys(new String[]{CyberBotsProject.ROBOTS_DIR});
        }

        @Override
        protected Node[] createNodes(String key) {
            if (CyberBotsProject.ROBOTS_DIR.equals(key)) {
                try {
                    return new Node[]{new RobotsNode(node, project)};
                } catch (DataObjectNotFoundException ex) {
                    Logger.getLogger(CyberBotsLogicalView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return new Node[0];
        }
        
    }
    
    private static final class RobotsNode extends FilterNode {
        
        public RobotsNode(Node node, CyberBotsProject project) throws DataObjectNotFoundException {
            super(node, new FilterNode.Children(node),
                //The projects system wants the project in the Node's lookup.
                //NewAction and friends want the original Node's lookup.
                //Make a merge of both:
                new ProxyLookup(
                    Lookups.singleton(project),
                    node.getLookup())
                );
        }
        
        @Override
        public Image getIcon(int type) {
            return ImageUtilities.loadImage(
                    "org/ufal/cyber/cyberbotsprojects/resources/robot_icon_16.png");
        }

        @Override
        public Image getOpenedIcon(int type) {
            return getIcon(type);
        }

        @Override
        public String getDisplayName() {
            return CyberBotsProject.ROBOTS_DIR;
        }
        
    }
}
