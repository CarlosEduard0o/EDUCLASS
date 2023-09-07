package br.com.educlass.model.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private String cpf;
    private String registration;
    private String email;
    private String address;
}
