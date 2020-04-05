package com.stepanov.documentserver.service;

import com.stepanov.documentserver.model.Document;
import com.stepanov.documentserver.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> getDocumentsList() {
        return documentRepository.findAll();
    }

    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public void saveDocument(Document document) {
        document.setCreationDate(LocalDate.now());
        documentRepository.save(document);
    }
    public void deleteDocumentById(long id) {
        documentRepository.deleteById(id);
    }

}
