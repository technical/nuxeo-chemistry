/*
 * (C) Copyright 2006-2008 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     bstefanescu
 */
package org.nuxeo.chemistry.shell.app.cmds;

import org.apache.chemistry.Folder;
import org.nuxeo.chemistry.shell.Console;
import org.nuxeo.chemistry.shell.Context;
import org.nuxeo.chemistry.shell.Path;
import org.nuxeo.chemistry.shell.app.ChemistryApp;
import org.nuxeo.chemistry.shell.app.ChemistryCommand;
import org.nuxeo.chemistry.shell.app.utils.SimpleBrowser;
import org.nuxeo.chemistry.shell.command.Cmd;
import org.nuxeo.chemistry.shell.command.CommandLine;
import org.nuxeo.chemistry.shell.command.CommandParameter;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 *
 */
@Cmd(syntax="dump|tree [target:item]", synopsis="Dump a subtree")
public class DumpTree extends ChemistryCommand {

    @Override
    protected void execute(ChemistryApp app, CommandLine cmdLine)
            throws Exception {
        CommandParameter param = cmdLine.getParameter("target");

        Context ctx;
        if (param != null && param.getValue() != null) {
            ctx = app.resolveContext(new Path(param.getValue()));
            if (ctx == null) {
                Console.getDefault().warn("Cannot resolve target: "+param.getValue());
                return;
            }
        } else {
            ctx = app.getContext();
        }

        Folder folder = ctx.as(Folder.class);
        if (folder != null) {
            new SimpleBrowser(folder).browse();
        } else {
            Console.getDefault().warn("Target is not a folder");
        }
    }

}
