package com.stepanov.documentserver.controller;

import com.stepanov.documentserver.model.Document;
import com.stepanov.documentserver.service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Document>> getDocumentsList() {
        return ResponseEntity.ok(documentService.getDocumentsList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Document>> getDocumentById(@PathVariable("id") long id) {
        Optional<Document> documentOptional = documentService.getDocumentById(id);
        if (!documentOptional.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(documentOptional);
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

