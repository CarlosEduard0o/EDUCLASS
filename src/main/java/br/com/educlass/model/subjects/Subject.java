package br.com.educlass.model.subjects;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data

/**
 * Aqui podemos ter a disciplina cursada pelo aluno
 */
public class Subject {
    private String id;
    private String name;
    private Long time;
    private ArrayList<String> frequency;
    private ArrayList<String> grades;
    private String situation;
    private ArrayList<Teacher> teachers;
    private Long period;
    private Teacher uniqueTeacher;
    private ArrayList<Student> enrolledStudents; // Alunos matriculados nessa disciplina
}
