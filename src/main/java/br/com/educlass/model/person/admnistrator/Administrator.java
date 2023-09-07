package br.com.educlass.model.person.admnistrator;

import br.com.educlass.model.institution.Institution;
import br.com.educlass.model.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator  extends Person {
    private Institution institution;
}
