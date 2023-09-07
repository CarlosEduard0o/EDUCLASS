package br.com.educlass.view.student.grades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeTable {
    private String subject;
    private String grade;
}
