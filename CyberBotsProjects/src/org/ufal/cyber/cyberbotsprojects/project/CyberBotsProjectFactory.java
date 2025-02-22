/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ufal.cyber.cyberbotsprojects.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.lookup.ServiceProvider;

/**
 * Fabrica de projetos do tipo CyberBots.
 * 
 * @author Matheus Pedro (UFAL)
 */
@ServiceProvider(service=ProjectFactory.class)
public class CyberBotsProjectFactory implements ProjectFactory {
    
    public static final String PROJECT_DIR = "cyberbots_project";
    public static final String PROJECT_PROPFILE = "project.properties";

    @Override
    public boolean isProject(FileObject projectDirectory) {
        return projectDirectory.getFileObject(PROJECT_DIR) != null;
    }

    @Override
    public Project loadProject(FileObject projectDirectory, ProjectState projectState) throws IOException {
        return isProject(projectDirectory) ? new CyberBotsProject(projectDirectory, projectState) : null;
    }

    @Override
    public void saveProject(Project project) throws IOException, ClassCastException {
        FileObject projectRoot = project.getProjectDirectory();
        if (projectRoot.getFileObject(PROJECT_DIR) == null) {
            throw new IOException ("Project dir " + projectRoot.getPath() + " deleted," +
                    " cannot save project");
        }

        //Force creation of the robots/ dir if it was deleted
        project.getLookup().lookup(CyberBotsProject.class).getRobotsFolder(true);

        //Find the properties file pvproject/project.properties,
        //creating it if necessary
        String propsPath = PROJECT_DIR + "/" + PROJECT_PROPFILE;
        FileObject propertiesFile = projectRoot.getFileObject(propsPath);
        if (propertiesFile == null) {
            //Recreate the properties file if needed
            propertiesFile = projectRoot.createData(propsPath);
        }

        Properties properties = (Properties) project.getLookup().lookup (Properties.class);
        File f = FileUtil.toFile(propertiesFile);
        properties.store(new FileOutputStream(f), "CyberBots Project Properties");
    }
    
}
