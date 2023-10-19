module br.com.educlass.educlass {
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires json.simple;

    opens br.com.educlass to javafx.fxml;
    exports br.com.educlass;

    opens br.com.educlass.view.student.grades to javafx.fxml;
    exports br.com.educlass.view.student.grades;

    opens br.com.educlass.view.student.frequency to javafx.fxml;
    exports br.com.educlass.view.student.frequency;

    opens br.com.educlass.view.student.template to javafx.fxml;
    exports br.com.educlass.view.student.template;

    opens br.com.educlass.view.student.template.informations to javafx.fxml;
    exports br.com.educlass.view.student.template.informations;


    opens br.com.educlass.view.teacher.grades to javafx.fxml;
    exports br.com.educlass.view.teacher.grades;

    opens br.com.educlass.view.teacher.frequency to javafx.fxml;
    exports br.com.educlass.view.teacher.frequency;

    opens br.com.educlass.view.teacher.template to javafx.fxml;
    exports br.com.educlass.view.teacher.template;

    opens br.com.educlass.view.student.extra to javafx.fxml;
    exports br.com.educlass.view.student.extra;

    opens br.com.educlass.view.configuration to javafx.fxml;
    exports br.com.educlass.view.configuration;

    opens br.com.educlass.view.adm.template to javafx.fxml;
    exports br.com.educlass.view.adm.template;

    opens br.com.educlass.view.adm.subjects to javafx.fxml;
    exports br.com.educlass.view.adm.subjects;

    opens br.com.educlass.view.adm.subjects.createSubjects to javafx.fxml;
    exports br.com.educlass.view.adm.subjects.createSubjects;

    opens br.com.educlass.view.adm.student to javafx.fxml;
    exports br.com.educlass.view.adm.student;

    opens br.com.educlass.view.adm.student.editStudent to javafx.fxml;
    exports br.com.educlass.view.adm.student.editStudent;

    opens br.com.educlass.view.adm.student.editStudent.studentEditConfirmationController to javafx.fxml;
    exports br.com.educlass.view.adm.student.editStudent.studentEditConfirmationController;

    opens br.com.educlass.view.adm.student.addStudent to javafx.fxml;
    exports br.com.educlass.view.adm.student.addStudent;

    opens br.com.educlass.view.adm.student.addStudent.studentConfirmation to javafx.fxml;
    exports br.com.educlass.view.adm.student.addStudent.studentConfirmation;

    opens br.com.educlass.view.adm.teacher to javafx.fxml;
    exports br.com.educlass.view.adm.teacher;

    opens br.com.educlass.view.adm.teacher.addTeacher to javafx.fxml;
    exports br.com.educlass.view.adm.teacher.addTeacher;

    opens br.com.educlass.view.adm.teacher.addTeacher.teacherConfirmation to javafx.fxml;
    exports br.com.educlass.view.adm.teacher.addTeacher.teacherConfirmation;

    opens br.com.educlass.view.adm.teacher.editTeacher to javafx.fxml;
    exports br.com.educlass.view.adm.teacher.editTeacher;

    opens br.com.educlass.view.adm.teacher.editTeacher.teacherEditConfirmation to javafx.fxml;
    exports br.com.educlass.view.adm.teacher.editTeacher.teacherEditConfirmation;

    opens br.com.educlass.view.login to javafx.fxml;
    exports br.com.educlass.view.login;

    opens br.com.educlass.images to javafx;
}