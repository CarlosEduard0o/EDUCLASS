package br.com.educlass.model.person.student;

import br.com.educlass.model.person.Person;
import br.com.educlass.model.subjects.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person {
   private ArrayList<Subject> subjects;
   private String teamId;
   private Long period;
   private String Situation;
   private String courseId;
}
