package chaowang_liling.picturemangement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class ShowController {

    static String string;
    @FXML public TextArea textArea;
    @FXML private Pane pane;
    @FXML private Pane pane2;
    @FXML public ImageView view1;
    @FXML public ImageView view2;
    @FXML private HBox hbox;

// when click upload, choose a photo from local file and display the photo.
    @FXML void showPicture() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/Users/chaowang/Desktop/NEU_IS/courses/INFO5100_Application_Engineer_and_Development/homework/pictureMangement/source"));
        File selectedFile = fc.showOpenDialog(null);

        //show image Thumbnail 100x100 to users on GUI
        if(selectedFile!=null) {
            Image p = new Image("file:///"+selectedFile.getAbsolutePath());
            view1.setImage(p);
            view2.setImage(p);
            string ="file:///"+selectedFile.getAbsolutePath();
            this.pixels(p);

            pane.getChildren().add(view1);
            hbox.getChildren().add(view2);
        }
    }

    //present the information of the picture
    @FXML void pixels(Image image){
        PixelReader pixelReader = image.getPixelReader();
        if(pixelReader == null){System.out.println("Sorry,the picture's pixels are not available.");}
        this.textArea.appendText("Image Width: " + image.getWidth() +"\n" + "Image Height " + image.getHeight() + "\n" +  "Image URL" + image.getUrl() + "\n");
    }

    // click NEXT button to change scene
    public void theNextButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Edit.fxml"));

        Parent editView = loader.load();
        Scene edit = new Scene(editView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();

        Image image = new Image(new File(string).toString());
        System.out.println(image.getHeight());
        EditController editController =loader.getController();
        editController.changePicture(image);
    }
}
