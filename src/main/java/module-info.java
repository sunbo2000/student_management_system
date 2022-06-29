module org.snbo.ssms {
    requires javafx.controls;
    requires javafx.fxml;


    exports org.snbo.ssms.view;
    opens org.snbo.ssms.view to javafx.fxml;
    exports org.snbo.ssms.view.studentview;
    opens org.snbo.ssms.view.studentview to javafx.fxml;
}