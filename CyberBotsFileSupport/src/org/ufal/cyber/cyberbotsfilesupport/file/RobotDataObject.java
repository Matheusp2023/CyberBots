/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/templateDataObjectAnno.java to edit this template
 */
package org.ufal.cyber.cyberbotsfilesupport.file;

import java.io.IOException;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_Robot_LOADER=Files of Robot"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_Robot_LOADER",
        mimeType = "text/urdf+xml",
        extension = {"urdf", "URDF"}
)
@DataObject.Registration(
        mimeType = "text/urdf+xml",
        iconBase = "org/ufal/cyber/cyberbotsfilesupport/resources/cyberbots_urdf_icon_16.png",
        displayName = "#LBL_Robot_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/text/urdf+xml/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class RobotDataObject extends MultiDataObject {

    public RobotDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/urdf+xml", false);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

}
