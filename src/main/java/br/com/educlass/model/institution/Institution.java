package br.com.educlass.model.institution;

import br.com.educlass.model.course.Course;
import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institution {
    private String name;
    private String cnpj;
    private String email;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<ArrayList<Subject>> subjects;
    private ArrayList<Course> courses;
}
