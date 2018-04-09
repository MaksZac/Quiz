import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame implements ActionListener {

    private JButton yesButton;
    private JButton noButton;
    private JLabel questionLabel;
    private List<Question> questions = new ArrayList<>();
    private QuestionGenerator questionGenerator;


    public Main(QuestionGenerator questionGenerator){
        setQuestionGenerator(questionGenerator);
        setDefaultFrameView();
        addComponentsToFrame();
    }

    public void setQuestionGenerator(QuestionGenerator questionGenerator) {
        this.questionGenerator = questionGenerator;
        this.questions = questionGenerator.generate(); // inicjalizuje listę
    }

    private void setDefaultFrameView() {
        setTitle("Milionerzy");
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
    }

    private void addComponentsToFrame() {
        yesButton = new JButton("TAK");
        yesButton.addActionListener(this);
        questionLabel = new JLabel(questions.get(0).getContent(), SwingConstants.CENTER);
        questionLabel.setForeground(Color.blue);
        noButton = new JButton("NIE");
        noButton.addActionListener(this);
        add(questionLabel);
        add(yesButton);
        add(noButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m = new Main(new SimpleQuestionGenerator());
            }
        });
    }

    private static int counter = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clikedButton = (JButton) e.getSource();
        counter++;
        if (counter == questions.size()) {
            yesButton.setEnabled(false);
            noButton.setEnabled(false);
        } else {
            questionLabel.setText(questions.get(counter).getContent());
        }
    }
}
