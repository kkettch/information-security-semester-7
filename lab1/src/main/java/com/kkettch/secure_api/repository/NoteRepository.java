package com.kkettch.secure_api.repository;

import com.kkettch.secure_api.entity.Note;
import com.kkettch.secure_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByProfile(Profile profile);
}
