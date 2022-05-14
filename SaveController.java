package chaowang_liling.picturemangement;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveController {

    @FXML ChoiceBox formats;
    @FXML TextField fileName;
    ImageView theImageview ;

    public void choiceFormat() {
        changeFormat();
    }

    public String changeFormat(){
        return (String)formats.getValue();
    }

    public void fileName(){
        getFileName();
        System.out.println(getFileName()+" file name ");
    }

    public String getFileName(){
        return fileName.getText();
    }

    public void buttonAction( ) {
        saveImage();
    }

    public void saveImage(){

        theImageview= EditController.edit;
        String format = this.changeFormat();
        String fileName = this.getFileName();

        //routine
        File outputFile = new File("/Users/chaowang/Desktop/NEU_IS/courses/INFO5100_Application_Engineer_and_Development/homework/pictureMangement/output"+fileName+format);
        BufferedImage bImage = SwingFXUtils.fromFXImage(theImageview.snapshot(null, null),null);

        try {ImageIO.write(bImage,"png", outputFile);
        } catch (IOException e) {throw new RuntimeException(e);}
        System.out.println("The picture has been successfully saved!");
    }
}