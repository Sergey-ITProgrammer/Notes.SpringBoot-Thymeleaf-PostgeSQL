package com.project.notes.controller;

import com.project.notes.domain.Note;
import com.project.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @PostMapping
    public String createNote(@ModelAttribute Note note, Model model) {
        if (note.getTitle().isEmpty()) {
            return "redirect:/notes";
        }

        notesService.createNote(note.getTitle(), note.getText());

        model.addAttribute("note", note);

        return "redirect:/notes/";
    }

    @PostMapping("/{id}/change")
    public String changeNote(@ModelAttribute Note note, Model model, @PathVariable long id) {
        if (note.getTitle().isEmpty()) {
            return "redirect:/notes";
        }

        notesService.changeNote(note.getTitle(), note.getText(), id);

        model.addAttribute("note", note);

        return "redirect:/notes/" + id;
    }

    @PostMapping("/delete")
    public String deleteNoteById(@RequestParam long id) {
        notesService.deleteNoteById(id);

        return "redirect:/notes";
    }

    @GetMapping("/new")
    public String newNote(Model model) {
        model.addAttribute("note", new Note());

        return "new";
    }

    @GetMapping("/{id}/change")
    public String changeNote(@PathVariable long id, Model model) {
        Note note = notesService.getNoteById(id);

        model.addAttribute("note", note);

        return "change";
    }

    @GetMapping
    public String getAllNotes(Model model) {
        List<Note> notes = notesService.getAllNotes();

        model.addAttribute("notes", notes);

        return "notes";
    }

    @GetMapping("/{id}")
    public String getNoteById(@PathVariable long id, Model model) {
        Note note = notesService.getNoteById(id);

        model.addAttribute("note", note);

        return "note";
    }

    @GetMapping("/search")
    public String getNotesByTitle(@RequestParam String flag, Model model) {
        if (flag.isEmpty()) {
            return "redirect:/notes";
        }

        List<Note> notes = notesService.getNotesByTitle(flag);

        model.addAttribute("flag", flag);
        model.addAttribute("notes", notes);

        return "notes";
    }
}
