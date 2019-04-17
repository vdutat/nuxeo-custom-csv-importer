package com.acme.ecm.csv.core.operations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.csv.core.CSVImporter;
import org.nuxeo.ecm.csv.core.CSVImporterOptions;
import org.nuxeo.ecm.csv.core.CSVImporterOptions.ImportMode;
import org.nuxeo.ecm.csv.core.operation.CSVImportOperation;
import org.nuxeo.runtime.api.Framework;

import com.acme.ecm.csv.core.CustomCSVImporterDocumentFactory;

/**
 *
 */
@Operation(id=CSVImportOperation.ID, category=Constants.CAT_DOCUMENT, label="CSVImport", description="Describe here what your operation does.")
public class CustomCSVImportOperation {

    @Context
    protected CoreSession mSession;

    @Param(name = "path")
    protected String mPath;

    @Param(name = "sendReport", required = false)
    protected boolean mSendReport;

    @Param(name = "documentMode", required = false)
    protected boolean mDocumentMode;

    protected static final Log log = LogFactory.getLog(CustomCSVImportOperation.class);

    @OperationMethod
    public String importCSV(Blob blob) {
        log.warn("<importCSV> ");
        ImportMode importMode = mDocumentMode ? ImportMode.IMPORT : ImportMode.CREATE;
        CSVImporterOptions options = new CSVImporterOptions.Builder().sendEmail(mSendReport)
                                                                     .importMode(importMode)
                                                                     .documentModelFactory(new CustomCSVImporterDocumentFactory())
                                                                     .build();
        CSVImporter csvImporter = Framework.getService(CSVImporter.class);
        return csvImporter.launchImport(mSession, mPath, blob, options);
    }
}
