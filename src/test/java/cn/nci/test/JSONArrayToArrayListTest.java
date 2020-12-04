package cn.nci.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: centispacesg
 * @description:
 * @author: xiejianfeng
 * @create: 2020-09-28 16:07
 */
public class JSONArrayToArrayListTest {
    public static void main(String[] args) {
        JSONArray studentJSONArray = new JSONArray();
        List<Student> studentList = new ArrayList<Student>();

        Map<String, Object> JohnMap = new HashMap<String, Object>();
        JohnMap.put("name", "John");
        JohnMap.put("age", 16);
        JohnMap.put("gender", "boy");
        JSONObject John = new JSONObject(JohnMap);

        Map<String, Object> LilyMap = new HashMap<String, Object>();
        LilyMap.put("name", "Lily");
        LilyMap.put("age", 17);
        LilyMap.put("gender", "girl");
        JSONObject Lily = new JSONObject(LilyMap);

        Map<String, Object> JackMap = new HashMap<String, Object>();
        JackMap.put("name", "Jack");
        JackMap.put("age", 18);
        JackMap.put("gender", "boy");
        JSONObject Jack = new JSONObject(JackMap);

        studentJSONArray.add(John);
        studentJSONArray.add(Lily);
        studentJSONArray.add(Jack);

//        System.out.println("\n=============== studentJSONArray info ================");
//        System.out.println(studentJSONArray);
//
//        System.out.println("\n=============== JSONArray to ArrayList(方式 1) ================");
//        studentList = studentJSONArray.toJavaList(Student.class);
//        studentList.forEach(student -> System.out.println("stundet info: " + student));
//
//        System.out.println("\n=============== JSONArray to ArrayList(方式 2) ================");
//        studentList = JSON.parseArray(studentJSONArray.toJSONString(), Student.class);
//        studentList.forEach(student -> System.out.println("stundet info: " + student));
//
//        System.out.println("\n=============== JSONArray to ArrayList(方式 3) ================");
//        studentList = JSONObject.parseArray(studentJSONArray.toJSONString(), Student.class);
//        studentList.forEach(student -> System.out.println("stundet info: " + student));
//
//        System.out.println("\n=============== JSONArray to ArrayList(方式 4) ================");
//        studentList = JSONArray.parseArray(studentJSONArray.toJSONString(), Student.class);
//        studentList.forEach(student -> System.out.println("stundet info: " + student));
//
//        System.out.println("\n=============== JSONArray to ArrayList(方式 5) ================");
//        final ArrayList<Student> tmpList = new ArrayList<Student>();
//        studentJSONArray.forEach(studentJson -> {
//            JSONObject jsonObject = (JSONObject)studentJson;
//            Student student = new Student(jsonObject.getString("name"), jsonObject.getInteger("age"), jsonObject.getString("gender"));
//            tmpList.add(student);
//        });
//
//        studentList = tmpList;
//        studentList.forEach(student -> System.out.println("stundet info: " + student));
//
//        System.out.println("\n=============== JSONArray to ArrayList(方式 6) ================");
//        studentList.clear();
//        for (Object object : studentJSONArray) {
//            JSONObject jsonObject = (JSONObject)object;
//            Student student = new Student(jsonObject.getString("name"), jsonObject.getInteger("age"), jsonObject.getString("gender"));
//            studentList.add(student);
//        }
//        studentList.forEach(student -> System.out.println("stundet info: " + student));
//
//        System.out.println("\n=============== JSONArray to ArrayList(方式 7) ================");
//        studentList.clear();
//        for (int i = 0; i < studentJSONArray.size(); i++) {
//            JSONObject jsonObject = (JSONObject)studentJSONArray.get(i);
//            Student student = new Student(jsonObject.getString("name"), jsonObject.getInteger("age"), jsonObject.getString("gender"));
//            studentList.add(student);
//        }
//        studentList.forEach(student -> System.out.println("stundet info: " + student));
    }
}
