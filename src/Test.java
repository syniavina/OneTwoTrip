import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Test
{

    public static void main(String[] args) {
        try {
            int count_status = 0; //счетчик для up_status>200 during appropriate date
            int count_lines = 0; //счетчик кол-ва строк в файле
            float resp_sum = 0; // будем считать сумму  x_resp_time
            ArrayList<Integer> status_codes = new ArrayList(); //разбивка по кодам
            ArrayList<Integer> count_status_codes = new ArrayList();// счетчик для кодов
            Param_value log_param = new Param_value(); // with lo_param we'll get substrings from each line in file
            int status_200 = 0;

            //для построчного чтения использеум
            File file = new File("/Users/oksana/Downloads/nginx.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                count_lines = count_lines + 1; //count of lines in file

                    int num_val = log_param.getUpStatus("up_status", line);

                    if (num_val>200) {

                        //work with date
                        String str_log_date = log_param.getLogDate(line); // get date from line for ex 18/May/2016:12:31:05

                        Date_define compare_date = new Date_define();

                        //find out if log_date was from last 24 hours
                        if (compare_date.forceDate(str_log_date) == true) { // compare date from log with fixed date - data for testing
                        //if (compare_date.duringDay(str_log_date) == true) { // compare date from log with current date

                            count_status = count_status + 1; //count of string with up_status>200 and appropriate date

                            Group_by_param groupping = new Group_by_param();
                            groupping.split_param = status_codes;
                            groupping.count_split = count_status_codes;

                            groupping.groupByInput(num_val);

                            status_codes = groupping.split_param;
                            count_status_codes = groupping.count_split;
                        }
                    }
                    if (num_val == 200) {
                        //about x_resp_time
                        status_200 = status_200+1;
                        float resp_val = log_param.getTime("x_resp_time", line);
                        resp_sum = resp_sum + resp_val;
                    }
            }

            fileReader.close();

            float resp_average = resp_sum/ (float) status_200;
            System.out.println(count_status + " out of " + count_lines + " requests returned non 200 code:" + "\n");
            System.out.println("split by codes " + status_codes + "\n");
            System.out.println("count split " + count_status_codes + "\n");
            System.out.println("Average response with 200 code: " + resp_average + " ms" + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}