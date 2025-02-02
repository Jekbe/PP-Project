package edu.uph.ii.ppproject.controllers;

import edu.uph.ii.ppproject.domain.Document;
import edu.uph.ii.ppproject.domain.User;
import edu.uph.ii.ppproject.repositories.DocumentRepository;
import edu.uph.ii.ppproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class DocumentController {
    private DocumentRepository documentRepository;
    private UserRepository userRepository;

    @Autowired
    private void setDocumentRepository(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/documents")
    public String documentsList(Model model) {
        List<Document> documents = documentRepository.findAll();
        model.addAttribute("documents", documents);
        return "documents/documents";
    }

    @GetMapping("/documentForm")
    public String documentForm(Model model, @RequestParam(value = "Id", required = false) Long id) {
        Document document = id != null ? documentRepository.findById(id).orElse(new Document()) : new Document();
        List<User> users = userRepository.findAll();

        model.addAttribute("document", document);
        model.addAttribute("users", users);

        return "documents/documentForm";
    }

    @PostMapping("/addDocument")
    public String addDocument(@RequestParam("file") MultipartFile file, @RequestParam("senderId") Long senderId, @RequestParam("recipientId") Long recipientId) throws IOException {
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setUploadDate(new Date());
        document.setFileType(file.getContentType());
        document.setFileContent(file.getBytes());
        document.setSender(userRepository.findById(senderId).orElse(null));
        document.setRecipient(userRepository.findById(recipientId).orElse(null));

        documentRepository.save(document);
        return "redirect:/documents";
    }

    @GetMapping("/downloadDocument")
    public ResponseEntity<byte[]> downloadDocument(@RequestParam("Id") Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Document not found"));

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"").contentType(MediaType.parseMediaType(document.getFileType())).body(document.getFileContent());
    }

    @GetMapping("deleteDocument")
    public String deleteDocument(@RequestParam("Id") Long id) {
        documentRepository.deleteById(id);
        return "redirect:/documents";
    }
}
