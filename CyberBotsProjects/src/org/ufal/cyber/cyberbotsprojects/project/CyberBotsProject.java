/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.cyber.cyberbotsprojects.project;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.ProjectState;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.openide.filesystems.FileObject;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.ufal.cyber.cyberbotsprojects.ui.CyberBotsLogicalView;

/**
 * Classe que modela projetos do tipo CyberBots.
 * 
 * @author Matheus Pedro (UFAL)
 */
public class CyberBotsProject implements Project {
    
    public static final String ROBOTS_DIR = "robots";
    
    private final FileObject projectDir;
    private final ProjectState projectState;
    private final LogicalViewProvider logicalView;
    private Lookup lookup;
    
    public CyberBotsProject(FileObject projectDir, ProjectState projectState) {
        this.projectDir = projectDir;
        this.projectState = projectState;
        
        logicalView = new CyberBotsLogicalView(this);
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }
    
    public FileObject getRobotsFolder(boolean create) {
        FileObject result =
            projectDir.getFileObject(ROBOTS_DIR);

        if (result == null && create) {
            try {
                result = projectDir.createFolder (ROBOTS_DIR);
            } catch (IOException ex) {
                Logger.getLogger(CyberBotsProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public Lookup getLookup() {
        if (lookup == null) {
            lookup = Lookups.fixed(new Object[]{
                        this, //handy to expose a project in its own lookup
                        projectState, //allow outside code to mark the project as needing saving
                        new ActionProviderImpl(), //Provides standard actions like Build and Clean
                        loadProperties(), //The project properties
                        new Info(), //Project information implementation
                        logicalView, //Logical view of project implementation
                    });
        }
        return lookup;
    }
    
    private Properties loadProperties() {
        FileObject fob = projectDir.getFileObject(CyberBotsProjectFactory.PROJECT_DIR
                + "/" + CyberBotsProjectFactory.PROJECT_PROPFILE);

        Properties properties = new NotifyProperties(projectState);
        if (fob != null) {
            try {
                properties.load(fob.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(CyberBotsProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return properties;
    }

    private static class NotifyProperties extends Properties {

        private final ProjectState state;

        NotifyProperties(ProjectState state) {
            this.state = state;
        }

        @Override
        public Object put(Object key, Object val) {
            Object result = super.put(key, val);

            if (((result == null) != (val == null)) || (result != null
                    && val != null && !val.equals(result))) {
                state.markModified();
            }
            return result;
        }
        
    }
    
    private final class ActionProviderImpl implements ActionProvider {

        @Override
        public String[] getSupportedActions() {
            return new String[0];
        }

        @Override
        public void invokeAction(String string, Lookup lookup) throws IllegalArgumentException {
            //do nothing
        }

        @Override
        public boolean isActionEnabled(String string, Lookup lookup) throws IllegalArgumentException {
            return false;
        }

    }

    /**
     * Implementation of project system's ProjectInformation class
     */
    private final class Info implements ProjectInformation {

        @Override
        public Icon getIcon() {
            return new ImageIcon(ImageUtilities.loadImage(
                    "org/ufal/cyber/cyberbotsprojects/resources/cyberbots_project_icon_16.png"));
        }

        @Override
        public String getName() {
            return getProjectDirectory().getName();
        }

        @Override
        public String getDisplayName() {
            return getName();
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public Project getProject() {
            return CyberBotsProject.this;
        }

    }
}
