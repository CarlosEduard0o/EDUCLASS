package TestesEduclass;

import br.com.educlass.model.person.student.Student;
import br.com.educlass.service.student.StudentService;

import java.util.ArrayList;

public class Admnistadorr {
    public static void main(String[] args) {
        Administator administator = new Administator();
        administator.setName("ADM 1");
        administator.setEmail("administracao@email.com");
        administator.setRegistration("157147117");
        administator.setAddress("endereço 1212");

        Instituicao instituicao = new Instituicao();
        instituicao.setCnpj("1571-5817-24-54");
        instituicao.setName("Instituicao 1");

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student());
        instituicao.setStudents(students);

        administator.setInstituicao(instituicao);
        System.out.println(administator);


    }
}
