package br.com.educlass.model.course;

import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.model.team.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String id;
    private String nome;
    private ArrayList<Subject> subjects;
    private ArrayList<Teacher> teachers;
}
