package com.stepanov.documentserver.controller;

import com.stepanov.documentserver.model.Document;
import com.stepanov.documentserver.service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private DocumentServiceImpl documentService;

    @Autowired
    public DocumentController(DocumentServiceImpl documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public List<Document> getDocumentsList() {
        return documentService.getDocumentsList();
    }

    @GetMapping("/{id}")
    public Optional<Document> getDocumentById(@PathVariable("id") long id) {
        return documentService.getDocumentById(id);
    }

    @PostMapping("/save")
    public void saveDocument(@RequestBody Document document) {
        documentService.saveDocument(document);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDocumentById(@PathVariable("id") long id) {
        documentService.deleteDocumentById(id);
    }
}

