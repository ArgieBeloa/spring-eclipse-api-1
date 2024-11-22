package springDemo.io.springMongoDB.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import springDemo.io.springMongoDB.model.QandaModel;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qanda")
public class QandaController {

    @Autowired
    private springDemo.io.springMongoDB.repository.QandaRepository qandaRepository;

    // Get All Q&A
    @GetMapping
    public ResponseEntity<?> getAllQanda() {
        List<QandaModel> qandaList = qandaRepository.findAll();
        if (!qandaList.isEmpty()) {
            return new ResponseEntity<>(qandaList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Q&A data found", HttpStatus.NOT_FOUND);
        }
    }

 // Get Q&A by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getQandaById(@PathVariable String id) {
        QandaModel qanda = qandaRepository.findById(id).orElse(null);
        if (qanda != null) {
            return new ResponseEntity<>(qanda, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Q&A not found", HttpStatus.NOT_FOUND);
        }
    }


    // Create New Q&A
    @PostMapping
    public ResponseEntity<?> createQanda(@RequestBody QandaModel qandaModel) {
        try {
            QandaModel savedQanda = qandaRepository.save(qandaModel);
            return new ResponseEntity<>(savedQanda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating Q&A", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Existing Q&A
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQanda(@PathVariable String id, @RequestBody QandaModel updatedQanda) {
        Optional<QandaModel> existingQanda = qandaRepository.findById(id);
        if (existingQanda.isPresent()) {
            QandaModel qanda = existingQanda.get();
            qanda.setQuestion(updatedQanda.getQuestion());
            qanda.setAnswer(updatedQanda.getAnswer());
            qanda.setSubjectId(updatedQanda.getSubjectId());
            QandaModel savedQanda = qandaRepository.save(qanda);
            return new ResponseEntity<>(savedQanda, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Q&A not found", HttpStatus.NOT_FOUND);
        }
    }

    // Delete Q&A by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQanda(@PathVariable String id) {
        Optional<QandaModel> qanda = qandaRepository.findById(id);
        if (qanda.isPresent()) {
            qandaRepository.deleteById(id);
            return new ResponseEntity<>("Q&A deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Q&A not found", HttpStatus.NOT_FOUND);
        }
    }
}
