// to find param up_status="200" and get value 200 in int
class Param_value {

    int getUpStatus(String param_name, String line){

        int paramPosition = line.indexOf(param_name);

        int start = line.indexOf('"', paramPosition); // расположение 1й ковычки
        int end = line.indexOf(' ', paramPosition); // расположение пробела
        String val = line.substring(start+1, end-1); // вырезали циферку в формате строки

        int param_val = Integer.parseInt(val);

        return  param_val;
    }

    float getTime(String param_name, String line){

        int paramPosition = line.indexOf(param_name);

        int start = line.indexOf('"', paramPosition); // расположение 1й ковычки
        int end = line.indexOf(' ', paramPosition); // расположение пробела
        String val = line.substring(start+1, end-3); // вырезали циферку в формате строки

        float param_val = Float.parseFloat(val);

        return  param_val;
    }

    String getLogDate(String line){

        int start = line.indexOf('['); // расположение 1й квадратной скобки
        int end = line.indexOf(' ', start); // расположение пробела
        String val = line.substring(start+1, end); // from substring [18/May/2016:12:04:07 +0300] we will get date 18/May/2016:12:04:07

        return  val;
    }

}