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

    opens br.com.educlass.view.configuration to javafx.fxml;
    exports br.com.educlass.view.configuration;

    opens br.com.educlass.view.adm.template to javafx.fxml;
    exports br.com.educlass.view.adm.template;

    opens br.com.educlass.view.login to javafx.fxml;
    exports br.com.educlass.view.login;

    opens br.com.educlass.images to javafx;
}