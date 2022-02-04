package com.project.notes.repository;

import com.project.notes.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
    List<Note> findByTitleContainingIgnoreCase(String flag);
    List<Note> findByTextContainingIgnoreCase(String flag);

    List<Note> findByTitleOrTextContainingIgnoreCase(String flag, String flag1);
}