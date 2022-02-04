package com.project.notes.service;

import com.project.notes.domain.Note;
import com.project.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;

    public void createNote(String title, String text) {
        Note note = new Note(title, text);

        notesRepository.save(note);
    }

    public void changeNote(String title, String text, long id) {
        Optional<Note> note = notesRepository.findById(id);

        if (note.isPresent()) {
            note.get().setTitle(title);
            note.get().setText(text);
            note.get().setModified();

            notesRepository.save(note.get());
        }
    }

    public void deleteNoteById(long id) {
        notesRepository.deleteById(id);
    }

    public List<Note> getAllNotes() {
        List<Note> notes = notesRepository.findAll();

        Collections.reverse(notes);

        return notes;
    }

    public Note getNoteById(long id) {
        Optional<Note> note = notesRepository.findById(id);

        if(note.isPresent()) {
            return note.get();
        }

        return null;
    }

    public List<Note> getNotesByTitle(String flag) {
        List<Note> notes = notesRepository.findByTitleContainingIgnoreCase(flag);

        Collections.reverse(notes);

        return notes;
    }
}
