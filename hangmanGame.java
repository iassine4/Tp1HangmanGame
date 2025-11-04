import java.util.Random;
import java.util.Scanner;
//import java.util.ArrayList;
import java.util.HashSet;

public class hangmanGame {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		HashSet<Character> guessedLetters = new HashSet<Character>();
		
		String[] wrdList = { "lettre", "java", "code", "console", "ordinateur"};
		
		Random rand = new Random();
		String wrdChoice = wrdList[rand.nextInt(wrdList.length)];
		
		String hiddenWrd = createHiddenWord(wrdChoice.length());
		
		int errors = 0;
        final int MAX_ERRORS = 10; //avec (final) MAX_ERRORS est une constante ne peux pas la modifier plus tard dans le code
        
		System.out.println("=== Bienvenue dans le jeu du Pendu ===");
        System.out.println("Le mot à deviner comporte " + wrdChoice.length() + " lettres.");
        System.out.println(hiddenWrd);

        
        while (errors < MAX_ERRORS && hiddenWrd.contains("_")) {
            System.out.println("\nMot actuel : " + hiddenWrd);
            System.out.print("Proposez une lettre : ");
            
            String guess = input.next().toLowerCase();
            
            // Vérifier que la saisie est bien UNE seule lettre
            if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) { //guess.charAt(0) récupère le 1er caractère 
            	System.out.println(" Veuillez saisir une seule lettre valide !");
                continue;
            }
            
            
            char letter = guess.charAt(0);
            


            // Vérifier si la lettre est présente dans le mot
            if (wrdChoice.indexOf(letter) >= 0) {
                System.out.println(" Bonne lettre !");
                hiddenWrd = revealLetter(wrdChoice, hiddenWrd, letter);
            } else {
                errors++;
                System.out.println(" Mauvaise lettre ! Il vous reste " + (MAX_ERRORS - errors) + " essais.");
            }
            
            displayListOfLetters(guessedLetters, letter);
         
        }
        // Fin de partie
        if (!hiddenWrd.contains("_")) {
            System.out.println("\nBravo ! Vous avez trouvé le mot : " + wrdChoice);
        } else {
            System.out.println("\nGame Over ! Le mot était : " + wrdChoice);
        }
            
        

        
        input.close(); // 
		
	}
	
	 // Méthode pour révéler une lettre trouvée
    public static String revealLetter(String word, String hidden, char letter) {
        StringBuilder sb = new StringBuilder(hidden);// instancier une copie de la chaîne cachée(hidden)
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                sb.setCharAt(i, letter); // on remplace le tiret par la lettre trouvée
            }
        }
        return sb.toString();
    }

	public static String createHiddenWord(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("_"); // un tiret pour chaque lettre
        }
        return sb.toString();
    }
	
	public static void displayListOfLetters(HashSet<Character> guessedLetters, char letter) {
		guessedLetters.add(letter);

		System.out.print("Lettres proposées : ");
			for ( char c : guessedLetters) {
				System.out.print(c +" ");
			}
		System.out.println(); // saut de ligne
	}
}
