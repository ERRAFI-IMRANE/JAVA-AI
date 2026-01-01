package application;

import ai.onnxruntime.*;

import java.util.Map;

public class SpamClassifier {

    private OrtEnvironment env;
    private OrtSession session;
    private String inputName;

    public SpamClassifier(String modelPath) throws Exception {
        env = OrtEnvironment.getEnvironment();
        session = env.createSession(modelPath, new OrtSession.SessionOptions());
        inputName = session.getInputNames().iterator().next();
    }

    public int predict(String text) {
        try {
            // Entrée du modèle (String)
            OnnxTensor inputTensor = OnnxTensor.createTensor(
                    env,
                    new String[][]{{text}}
            );

            OrtSession.Result result = session.run(
                    Map.of(inputName, inputTensor)
            );

            long[] output = (long[]) result.get(0).getValue();

            return (int) output[0];

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
