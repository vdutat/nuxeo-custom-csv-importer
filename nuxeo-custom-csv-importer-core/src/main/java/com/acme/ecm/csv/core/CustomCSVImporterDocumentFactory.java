package com.acme.ecm.csv.core;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.csv.core.DefaultCSVImporterDocumentFactory;

public class CustomCSVImporterDocumentFactory extends DefaultCSVImporterDocumentFactory {

    private static final long serialVersionUID = 1L;

    protected static final Log log = LogFactory.getLog(CustomCSVImporterDocumentFactory.class);

    @Override
    public void createDocument(CoreSession session, String parentPath, String name, String type,
            Map<String, Serializable> values) {
        log.warn("<createDocument> ");
        super.createDocument(session, parentPath, name, type, values);
    }

}
