import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    // Ana sınıfımız, programın başlangıç noktası olan main metoduyla başlar.
    public static void main(String[] args) {
        // Graph sınıfından bir nesne oluşturuyoruz.
        Graph myGraph = new Graph();

        // Grafımıza dört tane köşe (vertex) ekliyoruz.
        myGraph.addVertex("IST");
        myGraph.addVertex("AMS");
        myGraph.addVertex("CDG");
        myGraph.addVertex("JFK");

        // Köşeler arasında kenar (edge) ekliyoruz.
        myGraph.addEdge("IST", "AMS");
        myGraph.addEdge("IST", "CDG");
        myGraph.addEdge("IST", "JFK");
        myGraph.addEdge("AMS", "CDG");
        myGraph.addEdge("AMS", "JFK");
        myGraph.addEdge("CDG", "JFK");

        // Grafı basıyoruz.
        myGraph.printGraph();

        // Kenarları kaldırıyoruz.
        myGraph.removeEdge("IST", "CDG");
        myGraph.removeEdge("CDG", "JFK");

        // Değişiklik yapılmış grafi tekrar basıyoruz.
        myGraph.printGraph();
    }
}

// Graph sınıfı, grafiği temsil eder.
class Graph {

    // Her köşenin komşularını tutacak bir veri yapısı kullanıyoruz.
    private Map<String, ArrayList<String>> adjList;

    // Graph sınıfının kurucu metodu.
    public Graph() {
        // Köşelerin ve komşuluk ilişkilerinin tutulacağı veri yapısını oluşturuyoruz.
        this.adjList = new HashMap<>();
    }

    // Yeni bir köşe eklemek için kullanılan metot.
    public boolean addVertex(String vertex) {
        // Eğer köşe zaten varsa, eklemiyoruz.
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
            return true; // Yeni köşe eklendiği için true döndürüyoruz.
        }
        return false; // Köşe zaten varsa false döndürüyoruz.
    }

    // Yeni bir kenar eklemek için kullanılan metot.
    public boolean addEdge(String v1, String v2) {
        // Eğer belirtilen köşeler varsa, kenarı ekliyoruz.
        if (adjList.containsKey(v1) && adjList.containsKey(v2)) {
            adjList.get(v1).add(v2); // v1 köşesinin listesine v2'yi ekliyoruz.
            adjList.get(v2).add(v1); // v2 köşesinin listesine v1'i ekliyoruz.
            return true; // Kenar eklendiği için true döndürüyoruz.
        }
        return false; // Köşelerden en az biri yoksa false döndürüyoruz.
    }

    // Bir kenarı kaldırmak için kullanılan metot.
    public boolean removeEdge(String v1, String v2) {
        // Eğer belirtilen köşeler varsa, kenarı kaldırıyoruz.
        if (adjList.containsKey(v1) && adjList.containsKey(v2)) {
            adjList.get(v1).remove(v2); // v1 köşesinin listesinden v2'yi kaldırıyoruz.
            adjList.get(v2).remove(v1); // v2 köşesinin listesinden v1'i kaldırıyoruz.
            return true; // Kenar kaldırıldığı için true döndürüyoruz.
        }
        return false; // Köşelerden en az biri yoksa false döndürüyoruz.
    }

    // Bir köşeyi kaldırmak için kullanılan metot.
    public boolean removeVertex(String vertex) {
        // Eğer belirtilen köşe varsa, köşeyi ve ona bağlı olan kenarları kaldırıyoruz.
        if (adjList.containsKey(vertex)) {
            ArrayList<String> neighbors = adjList.get(vertex);
            for (String neighbor : neighbors) {
                adjList.get(neighbor).remove(vertex);
            }
            adjList.remove(vertex); // Köşeyi kaldırıyoruz.
            return true; // Köşe kaldırıldığı için true döndürüyoruz.
        }
        return false; // Köşe yoksa false döndürüyoruz.
    }

    // Grafiği ekrana basmak için kullanılan metot.
    public void printGraph() {
        for (String vertex : adjList.keySet()) {
            System.out.print(vertex + " -> ");
            ArrayList<String> neighbors = adjList.get(vertex);
            for (String neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}
