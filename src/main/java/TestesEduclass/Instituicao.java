package TestesEduclass;

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
public class Instituicao {
    private  String name;
    private String cnpj;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Subject> subjects;
}
