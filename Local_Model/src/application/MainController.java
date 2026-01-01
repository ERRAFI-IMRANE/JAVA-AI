package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MainController {

    @FXML
    private TextArea inputText;

    @FXML
    private Label resultLabel;
    
    @FXML
    private Label statusLabel;

    private SpamClassifier classifier;

    @FXML
    public void initialize() {
        try {
            classifier = new SpamClassifier("spam_model.onnx");
            statusLabel.setText("Modèle IA chargé avec succès");
            statusLabel.setStyle("-fx-text-fill: #10b981;");
        } catch (Exception e) {
            statusLabel.setText("Erreur lors du chargement du modèle");
            statusLabel.setStyle("-fx-text-fill: #ef4444;");
            e.printStackTrace();
        }
    }

    @FXML
    private void detectSpam() {
        String message = inputText.getText().trim();

        if (message == null || message.isEmpty()) {
            resultLabel.setText("Veuillez entrer un message");
            resultLabel.getStyleClass().removeAll("result-ham", "result-spam");
            return;
        }

        try {
            int prediction = classifier.predict(message);
            
            if (prediction == 0) {
                resultLabel.setText("HAM");
                resultLabel.getStyleClass().removeAll("result-spam");
                resultLabel.getStyleClass().add("result-ham");
            } else if (prediction == 1) {
                resultLabel.setText("SPAM");
                resultLabel.getStyleClass().removeAll("result-ham");
                resultLabel.getStyleClass().add("result-spam");
            } else {
                resultLabel.setText("Résultat inconnu");
                resultLabel.getStyleClass().removeAll("result-ham", "result-spam");
            }
            
        } catch (Exception e) {
            resultLabel.setText("Erreur lors de l'analyse");
            resultLabel.getStyleClass().removeAll("result-ham", "result-spam");
            e.printStackTrace();
        }
    }
}