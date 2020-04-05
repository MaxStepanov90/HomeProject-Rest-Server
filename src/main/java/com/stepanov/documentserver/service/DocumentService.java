package com.stepanov.documentserver.service;

import com.stepanov.documentserver.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    Optional<Document> getDocumentById(Long id);
    List<Document> getDocumentsList();
    void saveDocument(Document document);
}
