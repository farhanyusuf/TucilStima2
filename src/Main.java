import java.util.*; 
import java.io.*;

public class Main {
    public static void main(String[] args)throws Exception 
    {
        ArrayList<ArrayList<String>> matkulPrereqList = Graph.ReadFile("test2.txt");
        System.out.println("-----------------GRAPH------------------");
        Graph.makeGraph(matkulPrereqList);
        ArrayList<Integer> banyakEdge = Graph.HitungEdgeMasuk(matkulPrereqList);
        ArrayList<String> listMatkul = Graph.ListMatkul(matkulPrereqList);
        ArrayList<String> rencanakuliah = Graph.DecreaseandConquer(matkulPrereqList, listMatkul, banyakEdge);
        System.out.println("-------------RENCANA KULIAH--------------");
        Graph.printRencanaKuliah(rencanakuliah);
        System.out.println("-----------------------------------------");

    }
}
