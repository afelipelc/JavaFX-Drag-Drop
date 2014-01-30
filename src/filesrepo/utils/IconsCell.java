/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filesrepo.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author afelipelc
 */
public class IconsCell extends ListCell<String> {

    HBox hbox = new HBox();
    Label nameFile = new Label("");
    Button commentButton = new Button();
    Button deleteButton = new Button();
    Button shareButton = new Button();
   
    ImageView commentImageV;
    ImageView shareImageV;
    ImageView deleteImageV;
    
    //boolean showCommentBtn = false, shorDeleteBtn = false, showShareBtn = false;

    public IconsCell() {
        super();
        this.commentImageV = new ImageView(new Image("filesrepo/images/comment.png"));
        this.shareImageV = new ImageView(new Image("filesrepo/images/share.png"));
        this.deleteImageV = new ImageView(new Image("filesrepo/images/delete.png"));
        
        hbox.setSpacing(8);
        commentButton.setGraphic(commentImageV);
        shareButton.setGraphic(shareImageV);
        deleteButton.setGraphic(deleteImageV);
        
       
        commentButton.getStyleClass().add("optfilesbutton");
        commentButton.setPrefHeight(26);
        shareButton.getStyleClass().add("optfilesbutton");
        shareButton.setPrefHeight(26);
        deleteButton.getStyleClass().add("optfilesbutton");
        deleteButton.setPrefHeight(26);
        
        hbox.setAlignment(Pos.CENTER_LEFT);
                
        hbox.getChildren().addAll(nameFile, commentButton, shareButton, deleteButton);
        HBox.setHgrow(commentButton, Priority.ALWAYS);
        
        commentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Presiono Comentarios");
            }
        });

        shareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Presiono Comentarios");
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Presiono Comentarios");
            }
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            //commentButton.setVisible(false);
            //shareButton.setVisible(false);
            //deleteButton.setVisible(false);
            setGraphic(null);
        } else {

            nameFile.setText(item != null ? item : "<null>");
            commentButton.setVisible(item.substring(0, 1).equals("1") ? true : false );
            shareButton.setVisible(item.substring(1, 2).equals("1") ? true : false );
            deleteButton.setVisible(item.substring(2, 3).equals("1") ? true : false );
//            commentButton.setPrefWidth(commentButton.isVisible() ? USE_COMPUTED_SIZE : 0);
//            shareButton.setPrefWidth(shareButton.isVisible() ? USE_COMPUTED_SIZE : 0);
//            deleteButton.setPrefWidth(deleteButton.isVisible() ? USE_COMPUTED_SIZE : 0);
            setGraphic(hbox);
        }
    }
}
