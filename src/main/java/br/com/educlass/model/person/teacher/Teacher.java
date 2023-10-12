package br.com.educlass.model.person.teacher;

import br.com.educlass.model.person.Person;
import br.com.educlass.model.subjects.Subject;
import br.com.educlass.model.team.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Person {
    private ArrayList<Subject> subjects;
    private String situation;
}