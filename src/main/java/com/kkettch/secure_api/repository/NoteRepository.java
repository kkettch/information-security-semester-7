package com.kkettch.secure_api.repository;

import com.kkettch.secure_api.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
