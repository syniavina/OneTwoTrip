import java.util.ArrayList;

// group by param and count repeated params
class Group_by_param {

    ArrayList <Integer> split_param = new ArrayList<Integer>(); // split by codes
    ArrayList <Integer> count_split = new ArrayList<Integer>(); // count of splited codes


    void groupByInput(int param){
        boolean equal_count = false;


        for (int j=0; j < split_param.size(); j++) {

            if(split_param.size() == 0) {
                break;
            }


            if (param == (Integer) split_param.get(j)){
                equal_count = true; // already have this code in arrayList
                count_split.set(j, ( Integer ) count_split.get(j) + 1); // break;
            }
        }

        if (equal_count == false) {
            split_param.add(param);
            count_split.add(1);
        }
    }
}
