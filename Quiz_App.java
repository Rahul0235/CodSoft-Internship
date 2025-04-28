import java.util.*;

class Question {
    String question;
    String[] options;
    int correctOption; 

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class Quiz_App {

    static Scanner scanner = new Scanner(System.in);
    static List<Question> questions = new ArrayList<>();
    static int score = 0;
    static List<String> summary = new ArrayList<>();

    public static void main(String[] args) {
        loadQuestions();
        System.out.println("Welcome to the Quiz!");
        System.out.println("----------------------");
        for (int i = 0; i < questions.size(); i++) {
            askQuestion(i);
        }
        showResult();
    }

    static void loadQuestions() {
        questions.add(new Question(
            "What is the capital of France?",
            new String[]{"Berlin", "Madrid", "Paris", "Rome"},
            3
        ));

        questions.add(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"Earth", "Mars", "Jupiter", "Saturn"},
            2
        ));

        questions.add(new Question(
            "What is the largest mammal?",
            new String[]{"Elephant", "Blue Whale", "Giraffe", "Shark"},
            2
        ));

        questions.add(new Question(
            "Who wrote 'Romeo and Juliet'?",
            new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"},
            2
        ));

        questions.add(new Question(
            "What is the boiling point of water?",
            new String[]{"90°C", "100°C", "110°C", "120°C"},
            2
        ));
    }

    static void askQuestion(int index) {
        Question q = questions.get(index);
        System.out.println("\nQuestion " + (index + 1) + ": " + q.question);
        for (int i = 0; i < q.options.length; i++) {
            System.out.println((i + 1) + ". " + q.options[i]);
        }
        System.out.println("You have 10 seconds to answer...");

        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Moving to next question.");
                synchronized(scanner) {
                    scanner.notify();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 10000); // 10 seconds

        int answer = -1;
        synchronized(scanner) {
            try {
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    timer.cancel();
                }
                scanner.wait(10000); // Wait for 10 seconds 3
            } catch (Exception e) {

            }
        }

        if (answer == q.correctOption) {
            System.out.println("Correct!");
            score++;
            summary.add("Q" + (index + 1) + ": Correct");
        } else if (answer == -1) {
            System.out.println("No answer submitted.");
            summary.add("Q" + (index + 1) + ": No Answer");
        } else {
            System.out.println("Wrong! Correct answer was: " + q.correctOption + ". " + q.options[q.correctOption - 1]);
            summary.add("Q" + (index + 1) + ": Wrong");
        }
    }

    static void showResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        System.out.println("Summary:");
        for (String s : summary) {
            System.out.println(s);
        }
    }
}
