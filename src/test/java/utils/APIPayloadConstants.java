package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Alexey\",\n" +
                "  \"emp_lastname\": \"Sitalo\",\n" +
                "  \"emp_middle_name\": \"Sk\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1983-02-19\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"QA Tester\"\n" +
                "}";

        return createEmployeePayload;
    }

    // Creating a new Employee using JSON
    public static String createEmployeeBodyJson(){

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Alexey");
        obj.put("emp_lastname", "Sitalo");
        obj.put("emp_middle_name", "MS");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "1983-02-19");
        obj.put("emp_status", "Worker");
        obj.put("emp_job_title", "Super Tester");

        return obj.toString();
    }

    // Creating a new Employee using Dynamic data
    public static String payloadMoreDynamic(String emp_firstname, String emp_lastname, String emp_middle_name, String emp_gender,
                                            String emp_birthday, String emp_status, String emp_job_title){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", emp_firstname);
        obj.put("emp_lastname", emp_lastname);
        obj.put("emp_middle_name", emp_middle_name);
        obj.put("emp_gender", emp_gender);
        obj.put("emp_birthday", emp_birthday);
        obj.put("emp_status", emp_status);
        obj.put("emp_job_title", emp_job_title);

        return obj.toString();
    }

    // Updating particular Employee's data
    public static String updateEmployeeBody(){
        JSONObject obj = new JSONObject();
        obj.put("employee_id", "28360A");
        obj.put("emp_firstname", "Alexey");
        obj.put("emp_lastname", "Sitalo");
        obj.put("emp_middle_name", "MS");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "1983-02-19");
        obj.put("emp_status", "Worker");
        obj.put("emp_job_title", "Super Tester");

        return obj.toString();
    }


}
