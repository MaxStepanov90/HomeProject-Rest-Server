package com.stepanov.documentserver.service;

import com.stepanov.documentserver.model.Document;
import com.stepanov.documentserver.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DocumentServiceImplTest {
    @InjectMocks
    private DocumentServiceImpl documentService;
    @Mock
    private DocumentRepository documentRepository;

    private Document getNewDocument() {
        return new Document((long) 1, "new document", LocalDate.now(), "Hello!");
    }

    @Test
    void getDocumentsList_Contains() {
        List<Document> documents = new ArrayList<>();
        documents.add(getNewDocument());
        when(documentRepository.findAll()).thenReturn(documents);
        assertTrue(documentService.getDocumentsList().containsAll(documents));
        verify(documentRepository, times(1)).findAll();
    }

    @Test
    void getDocumentById() {
        when(documentRepository.findById((long) 1)).thenReturn(java.util.Optional.of(getNewDocument()));
        Optional<Document> foundDocument = documentService.getDocumentById((long) 1);
        assertNotNull(foundDocument);
        verify(documentRepository, times(1)).findById((long) 1);
    }

    @Test
    void saveDocument() {
        documentService.saveDocument(getNewDocument());
        verify(documentRepository,times(1)).save(getNewDocument());
    }

    @Test
    void deleteDocumentById() {
        documentService.deleteDocumentById(1);
        verify(documentRepository,times(1)).deleteById((long) 1);
    }
}