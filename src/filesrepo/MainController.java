/*
 * This proyect is only an example of Drag and Drop with a file.
 * 
 * The file should be copied to the local App directory
 * 
 * visit http://afelipelc.wordpress.com
 * 
 * This example idea is taken on http://www.ex-javamonday.appspot.com/Code/Java/JavaFX/Demonstratesadraganddropfeature.htm
 * 
 * 
 * by @afelipelc
 */
package filesrepo;

import filesrepo.utils.IconsCell;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;

/**
 *
 * @author afelipelc
 */
public class MainController implements Initializable {

    @FXML
    private Label textoSoltar;
    @FXML
    private ListView<String> lista;
    
    
    ObservableList<String> list = FXCollections.observableArrayList(
            "000","001", "100", "101", "111", "100", "101", "111", "100", "101", "111");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node 
                 * and if it has a File data */
                if (event.getGestureSource() != lista
                        && event.getDragboard().hasFiles()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY);
                }
                event.consume();
            }
        });

        lista.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != lista
                        && event.getDragboard().hasFiles()) {
                    //archivo.setFill(Color.GREEN);
                    textoSoltar.setVisible(true);
                }
                event.consume();
            }
        });

        lista.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                textoSoltar.setVisible(false);
                event.consume();
            }
        });

        lista.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a File data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    //archivoTextView.setText(db.getFiles().get(0).getPath());
                    try {
                        //Copy file to the local App directory /
                        copyFile(db.getFiles().get(0), new File("" + db.getFiles().get(0).getName()));
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    success = true;
                }
                /* let the source know whether the File was successfully 
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        lista.setItems(list);
        
        
        
        
        lista.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new IconsCell();
            }
        });
        
        

    }

    //Copy files From - To
    private static void copyFile(File inFile, File outFile) throws IOException {
        if (inFile.getCanonicalPath().equals(outFile.getCanonicalPath())) {
            // inFile and outFile are the same;
            // hence no copying is required.
            return;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(inFile));
            out = new BufferedOutputStream(new FileOutputStream(outFile));
            for (int c = in.read(); c != -1; c = in.read()) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
