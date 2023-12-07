import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class SpamDetector {
    private static final List<String> emailBodies = new ArrayList<>();
    private static final double SPAM_THRESHOLD = 0.5; // You can adjust this threshold

    public static void main(String[] args) {

        System.out.println("Spam Detector is running");

        addEmailBody("I want to play everyday");
        addEmailBody("i sleep everyday at night");
        addEmailBody("I will sleep at night everyday");

        calculateSpamProbability();
    }

private static void addEmailBody(String emailBody) {
        emailBodies.add(emailBody);
    }
    private static double calculateSimilarity(String emailBody1, String emailBody2) {
        Set<String> words1 = new HashSet<> (Arrays.asList(emailBody1.split(" ")));
        Set<String> words2 = new HashSet<>(Arrays.asList(emailBody2.split(" ")));

        Set<String> commonWords = new HashSet<>(words1);
        commonWords.retainAll(words2);

        return (double) commonWords.size() / (words1.size() + words2.size() - commonWords.size());
    }
    private static void calculateSpamProbability() {
        for (int i = 0; i < emailBodies.size(); i++) {
            double totalSimilarity = 0.0;
            for (int j = 0; j < emailBodies.size(); j++) {
                if (i != j) {
                    totalSimilarity += calculateSimilarity(emailBodies.get(i), emailBodies.get(j));
                }
            }
            double averageSimilarity = totalSimilarity / (emailBodies.size() - 1);
            String spamStatus = averageSimilarity > SPAM_THRESHOLD ? "Likely Spam" : "Unlikely Spam";
            System.out.println("Email " + (i + 1) + " is " + spamStatus + " (Spam Probability: " + averageSimilarity + ")");
        }
    }
}