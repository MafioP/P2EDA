package uva.eda.main;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserInterface {
    private static DataAnalyzer dataAnalyzer;
    private static DataReader dataReader;
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void init() throws IOException {
        System.out.println("Introduzca el nombre del fichero: ");
        String filename = scanner.nextLine();
        dataReader = new DataReader(filename);
        ArrayList<Persona> data = dataReader.readData();
        dataAnalyzer = new DataAnalyzer(data);
        System.out.println("Numero de nombres a mostrar: ");
        dataAnalyzer.setShowNames(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Nombre del usuario: ");
        dataAnalyzer.setUserName(scanner.nextLine());
        startQueryLoop();
    }

    private void startQueryLoop() {
        while (true) {
            System.out.println("Introduzca el intervalo de fechas de nacimiento: ");
            String dateInterval = scanner.nextLine();
            System.out.println("Empezando la busqueda...");
            System.out.println();
            long time = System.nanoTime();
            String[] dates = dataReader.splitDateInput(dateInterval);
            HashMap<String, Integer> interval = dataAnalyzer.filterByDate(dates[0], dates[1]);
            HashMap<Integer, String> topNames = dataAnalyzer.getTopNames(interval);
            int[] userData = dataAnalyzer.findUsername(interval);
            Results results = dataAnalyzer.getResults();
            results.setOperationTime(System.nanoTime() - time);
            printTop(topNames);
            printUser(userData);
            printResults(results);
            System.out.println("Quiere continuar? (S/N) ");
            if (scanner.nextLine().equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    private void printTop(HashMap<Integer, String> topNames) {
        AtomicInteger i = new AtomicInteger();
        topNames.forEach((integer, string) -> {
            i.getAndIncrement();
            System.out.println(i + ". " + string + " " + integer);
        });
    }
    private void printUser(int[] userData) {
        System.out.println((userData[0] + 1) + ". " + dataAnalyzer.getUserName() + " " + userData[1]);
    }

    private void printResults(Results results) {
        System.out.println("*********** RESULTADOS ***********");
        System.out.println("Tiempo de operacion: " + results.getOperationTime() + " sec");
        System.out.println("Personas en el intervalo: " + results.getIntervalPeople());
        System.out.println("Nombres distintos en el intervalo: " + results.getDifferentNames());
        System.out.println("Dias en el intervalo: " + results.getIntervalDays() + "\n");
    }
}
