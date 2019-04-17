package com.acme.ecm.csv.jsf;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.Blobs;
import org.nuxeo.ecm.csv.core.CSVImporter;
import org.nuxeo.ecm.csv.core.CSVImporterOptions;
import org.nuxeo.ecm.csv.jsf.CSVImportActions;
import org.nuxeo.runtime.api.Framework;

import com.acme.ecm.csv.core.CustomCSVImporterDocumentFactory;

@Scope(ScopeType.CONVERSATION)
@Name("csvImportActions")
@Install(precedence = Install.DEPLOYMENT)
public class CustomCSVImportActions extends CSVImportActions implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(CustomCSVImportActions.class);

    public void importCSVFile() throws IOException {
        log.warn("<importCSVFile> ");
        if (csvFile != null) {
            CSVImporterOptions options = new CSVImporterOptions.Builder().sendEmail(notifyUserByEmail)
                .importMode(getImportMode())
                .documentModelFactory(new CustomCSVImporterDocumentFactory())
                .build();
            CSVImporter csvImporter = Framework.getService(CSVImporter.class);
            csvImportId = csvImporter.launchImport(documentManager,
                    navigationContext.getCurrentDocument().getPathAsString(),
                    Blobs.createBlob(csvFile, null, null, csvFileName), options);
        }
    }

}
