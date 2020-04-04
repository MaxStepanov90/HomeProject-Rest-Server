package com.stepanov.documentserver.service;

import com.stepanov.documentserver.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    public Optional<Document> getDocumentById(Long id);
    public List<Document> getDocumentsList();
    public void saveDocument(Document document);
}
