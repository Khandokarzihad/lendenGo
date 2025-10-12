module bd.edu.seu.lendengo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;


    opens bd.edu.seu.lendengo to javafx.fxml;
    exports bd.edu.seu.lendengo;

    opens bd.edu.seu.lendengo.controllers to javafx.fxml;
    exports bd.edu.seu.lendengo.controllers;
}