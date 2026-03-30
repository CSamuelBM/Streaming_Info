module org.app.editor_texto {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.app.editor_texto to javafx.fxml;
    exports org.app.editor_texto;
    exports org.app.editor_texto.Controller;
    opens org.app.editor_texto.Controller to javafx.fxml;
}