import java.util.*; 
import java.io.*;
  
class Graph { 
    //Prosedur untuk menge-print Graph dengan representasi list of list adjacent nodes
    public static void makeGraph(ArrayList<ArrayList<String>> matkulPrereqList) 
    { 
        for (int i = 0 ; i < matkulPrereqList.size(); i++) { 
            for (int j = 0 ; j < matkulPrereqList.get(i).size(); j++) {
                if (matkulPrereqList.get(i).size() == 1) {
                    System.out.print(matkulPrereqList.get(i).get(j) + ".");
                }
                else{
                    if (j+1 < matkulPrereqList.get(i).size()){
                        if(j+1 == matkulPrereqList.get(i).size()-1){
                            System.out.print(matkulPrereqList.get(i).get(0) + " <- " + matkulPrereqList.get(i).get(j+1) + ".");
                        }
                        else{
                            System.out.print(matkulPrereqList.get(i).get(0) + " <- " + matkulPrereqList.get(i).get(j+1) + ", ");
                        }
                    }
                    
                }
            }
            System.out.print('\n');
        }
    }

    
    //Fungsi untuk membaca file.txt dan data dalam file disimpan menjadi list of list matkul dan prerequisite
    public static ArrayList<ArrayList<String>> ReadFile(String filename) throws IOException{
        File file = new File(filename); 
        ArrayList<String> arrayaku = new ArrayList<String>(100);
        ArrayList<ArrayList<String>> matkulPrereqList = new ArrayList<ArrayList<String>>(100);
        Scanner sc = new Scanner(file).useDelimiter(System.getProperty("line.separator")); 
    
        
        while(sc.hasNextLine()){
            arrayaku.add(sc.nextLine());
        }

        for(int i = 0 ; i < arrayaku.size() ; i++){
            ArrayList<String> tempList = new ArrayList<>(Arrays.asList(arrayaku.get(i).split(",")));
            matkulPrereqList.add(tempList);
        }
        return matkulPrereqList;   
    }


    //Fungsi untuk menghitung banyaknya edge yang masuk ke node matkul
    public static ArrayList<Integer> HitungEdgeMasuk(ArrayList<ArrayList<String>> matkulPrereqList){
        ArrayList<Integer> banyakEdge = new ArrayList<Integer>(100);
        for(int i = 0;i < matkulPrereqList.size();i++){
            banyakEdge.add(matkulPrereqList.get(i).size()-1);
        }
        return banyakEdge;
    }


    //Prosedur untuk menghapus node matakuliah dari Graph
    public static void deleteFromGraph(ArrayList<ArrayList<String>> matkulPrereqList, String node){
        for(int m = 0; m < matkulPrereqList.size() ; m++){
            for(int n = 0; n < matkulPrereqList.get(m).size() ; n++){
                if (node.equals(matkulPrereqList.get(m).get(n))){
                    matkulPrereqList.get(m).remove(n);
                }
            }
            if(matkulPrereqList.get(m).isEmpty()){
                matkulPrereqList.remove(m);
                m = m-1;
            }
        }
    }


    //Fungsi yang mengembalikan List mata kuliah dari Graph
    public static ArrayList<String> ListMatkul(ArrayList<ArrayList<String>> matkulPrereqList){
        ArrayList<String> listMatkul = new ArrayList<String>(100);
        for(int i = 0; i < matkulPrereqList.size() ; i++){
            listMatkul.add(matkulPrereqList.get(i).get(0));
        }
        return listMatkul;
    }


    //Fungsi yang memproses Graph mata kuliah dengan pendekatan decrease and conquer melalui topological sort
    public static ArrayList<String> DecreaseandConquer(ArrayList<ArrayList<String>> _matkulPrereqList, ArrayList<String> _listMatkul, 
    ArrayList<Integer> _banyakEdge) {
        int a = 1;
        ArrayList<String> rencanakuliah = new ArrayList<String>(100);
        while(!_matkulPrereqList.isEmpty()){
            int i = 0;
            boolean found = false;
            while (i < _banyakEdge.size() && !found){
                found = _banyakEdge.get(i) == 0;
                if(found == true){
                    break;
                }
                else{
                i++;
                }
            }
            deleteFromGraph(_matkulPrereqList,_listMatkul.get(i));
            rencanakuliah.add(_listMatkul.get(i));
            _banyakEdge = HitungEdgeMasuk(_matkulPrereqList);
            _listMatkul = ListMatkul(_matkulPrereqList);
        }
        return rencanakuliah;
    }


    //Prosedur untuk menge-print rencana kuliah
    public static void printRencanaKuliah(ArrayList<String> rencanakuliah, ArrayList<ArrayList<String>> _matkulPrereqListconsts){
        int x = 1;
        for (int i = 0 ; i < rencanakuliah.size() ; i++ ){
            if (i == 0){
                System.out.print("Semester " + x + " : " + rencanakuliah.get(i));
                x++;}
            if (i>0){
                if (!isKuliahdiambilbersamaPrerequisite(rencanakuliah.get(i),rencanakuliah.get(i-1), _matkulPrereqListconsts)){
                    System.out.print(", " + rencanakuliah.get(i));
                }
                else{
                    System.out.print("\nSemester " + x + " : " + rencanakuliah.get(i));
                    x++;
                }
            }
        }
    }
    

    //Fungsi yang menge-check apakah kuliah yang diambil pada suatu semester bersamaan dengan prerequisitenya
    public static boolean isKuliahdiambilbersamaPrerequisite(String kuliah, String apakahPrereq, ArrayList<ArrayList<String>>
     _matkulPrereqListconsts){
        boolean found = false;
        for (int i = 0 ; i < _matkulPrereqListconsts.size() ; i++){
            if (kuliah.equals(_matkulPrereqListconsts.get(i).get(0))){
                int iterator = 0;
                while (iterator < _matkulPrereqListconsts.get(i).size() && !found){
                    found = apakahPrereq.equals(_matkulPrereqListconsts.get(i).get(iterator));
                    iterator++;
                }
            }
        }
        return found;
    }
} 