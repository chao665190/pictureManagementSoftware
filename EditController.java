package chaowang_liling.picturemangement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.*;


public class EditController {

    @FXML javafx.scene.image.ImageView iv = new javafx.scene.image.ImageView();
    static ImageView edit;
    @FXML ChoiceBox  choice_box;
    @FXML public Image pictures;
    @FXML private Pane pane;

    @FXML void changePicture(Image picture) {
        pictures = picture;
        iv.setImage(picture);
        pane.getChildren().add(iv);
    }

    @FXML public void clickChoices() {
        choice_box.getSelectionModel().selectedItemProperty().addListener((v,oldVal,newVal)-> SpecialEffect(pictures,(String) newVal));
    }

    //the speical effect
    //optional: allow user to apply various filters
    //Lighting & Box Blur
    public void SpecialEffect(Image picture,String effect){
        iv.setImage(picture);
        switch(effect){
            case "Lighting":    Lighting lightingView = new Lighting();
                iv.setEffect(lightingView);
                break;
            case "BoxBlur":  BoxBlur boxBlur= new BoxBlur();
                iv.setEffect(boxBlur);
                break;
            //the exception handling
            default:
                System.out.println("This is not a valid special effect.");
                break;
        }
    }

    //save the picture
    public void saveImage(MouseEvent mouseEvent) throws IOException {
        EditController.edit = iv;
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("Save.fxml"));

        Parent save = fxmlloader.load();
        Scene sceneSave = new Scene(save);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneSave);
        window.show();
    }
}
