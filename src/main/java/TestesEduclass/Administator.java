package TestesEduclass;

import br.com.educlass.model.person.Person;
import br.com.educlass.model.person.student.Student;
import br.com.educlass.model.person.teacher.Teacher;
import br.com.educlass.model.subjects.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administator extends Person  {
    private Instituicao instituicao;

    public void createStudent(Student student) {
        instituicao.getStudents().add(student);
    }

    public void editStudent(String registration, Student newStudent) {
        int i = 0;
        for(Student student: instituicao.getStudents()) {
            if(student.getRegistration().equalsIgnoreCase(registration)) {
                this.instituicao.getStudents().set(i, newStudent);
            }
            i++;
        }
    }

    public void listAllStudents() {
        ArrayList< HashMap<String, Student>> listOfStudents = new ArrayList<>();
        HashMap<String, Student> atualStudent = new HashMap<>();
        for(Student student: this.instituicao.getStudents()){
            atualStudent.put(student.getName(), student);
            listOfStudents.add(atualStudent);
        }
    }
}
