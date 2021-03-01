import java.util.*; 
import java.io.*;

public class Main {
    public static void main(String[] args)throws Exception 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama file (beserta ekstensinya-- .txt) : ");
        String namafile = input.next();
        ArrayList<ArrayList<String>> matkulPrereqList = Graph.ReadFile(namafile);
        ArrayList<ArrayList<String>> matkulPrereqListconsts = Graph.ReadFile(namafile);
        System.out.println("-----------------GRAPH------------------");
        Graph.makeGraph(matkulPrereqList);
        ArrayList<Integer> banyakEdge = Graph.HitungEdgeMasuk(matkulPrereqList);
        ArrayList<String> listMatkul = Graph.ListMatkul(matkulPrereqList);
        ArrayList<String> rencanakuliah = Graph.DecreaseandConquer(matkulPrereqList, listMatkul, banyakEdge);
        System.out.println("-------------RENCANA KULIAH--------------");
        Graph.printRencanaKuliah(rencanakuliah, matkulPrereqListconsts);
        System.out.println("\n-----------------------------------------");

    }
}
