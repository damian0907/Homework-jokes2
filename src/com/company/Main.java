package com.company;


import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Joke> jokes = new LinkedList<>();



        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("How many jokes do you want to download?");
            jokes.addAll(downloadJokes(Integer.parseInt(scanner.nextLine())));
            System.out.println("Now you have " + jokes.size() + " jokes at your list.");

            SortJokeListByLength(jokes);
            System.out.println("The shortest joke is: ");
            System.out.println(jokes.get(0));


            List<Joke> religiousJokes = getReligiousJokes(jokes);

            if (!religiousJokes.isEmpty()) {
                String religious;
                System.out.println("Do you want to see downloaded religious jokes? YES or NO");
                religious = scanner.nextLine();
                if (religious.equals("yes")) {
                    System.out.println("Downloaded religious jokes: ");
                    System.out.println(religiousJokes);
                }
            }

            System.out.println("Do you want to know how many two-part jokes were downloaded? YES or NO");
            String twoPart;
            twoPart = scanner.nextLine();
            if (twoPart.equals("yes")) {
                int twoPartJokesCounter = countTwoPartJokes(jokes);
                System.out.println("You downloaded " + twoPartJokesCounter + " two-part jokes:");
                System.out.println(jokes);
            }

            String delete;
            System.out.println("Do you want to delete downloaded jokes? YES or NO");
            delete = scanner.nextLine();
            if (delete.equals("YES")) {

                jokes.clear();
            }
            System.out.println("Do you want to download more jokes? YES or NO");
            String downloadMore = scanner.nextLine();
            if (!downloadMore.equals("YES")) {
                break;
            }
        }
    }


    private static List<Joke> downloadJokes(int size) {
        JokeAPI connector = new JokeAPI();
        List<Joke> jokes = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            try {
                jokes.add(connector.getJoke("Any"));
            } catch (Exception e) {
                System.out.println("Sorry, there is an error");
                e.printStackTrace();
            }
        }
        return jokes;
    }

    private static void SortJokeListByLength(List<Joke> jokes) {
        Comparator<Joke> jokeComparator = Comparator.comparing(Joke::getJokeLength);

        Collections.sort(jokes, jokeComparator);

    }


    private static List<Joke> getReligiousJokes(List<Joke> jokes) {
        List<Joke> religiousJokes = new LinkedList<>();

        for(Iterator<Joke> iter = jokes.iterator();iter.hasNext();) {
            Joke element = iter.next();
            if( element.religious ) {
                religiousJokes.add(element);
            }
        }

        return religiousJokes;
    }


    private static int countTwoPartJokes(List<Joke> jokes) {
        int twoPartJokesCounter = 0;
        for(Iterator<Joke> iter = jokes.iterator();iter.hasNext();) {
            Joke element = iter.next();
            if( element.setup != null ) {
                twoPartJokesCounter++;
            }
        }
        return twoPartJokesCounter;
    }
}
