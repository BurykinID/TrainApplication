package com.app.train.ui.uploadData;

import com.app.train.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

@Route(value = "/uploadTrainData")
public class UploadDataWithFile extends VerticalLayout {

    private Upload upload;

    public UploadDataWithFile () {

        MemoryBuffer memoryBuffer = new MemoryBuffer();
        

        configureUpload(upload);

    }

    private void configureUpload (Upload upload) {

        upload.setMaxFiles(1);
        upload.setMaxFileSize(1_048_576);


    }
}
