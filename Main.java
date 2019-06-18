package com.company;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PhoneBook {
    public PhoneBook() {
        this.phonebook = new HashMap<String, String>();
    }

    void addNewContact(String name, String phone) {
        if (this.phonebook.containsKey(name)) {
            System.out.println("The contact already exists");
        } else {
            if (this.phonebook.get(name) == phone) {
                System.out.println("The phone number already exists");
            } else {
                this.phonebook.put(name, phone);
            }
        }
    }

    void deleteContact(String name){
        if (this.phonebook.containsKey(name)){
            this.phonebook.remove(name);
        }
        else{
            System.out.println("Invalid name");
        }
    }

    String getPhoneNumber(String name){
        if (this.phonebook.containsKey(name)){
            return this.phonebook.get(name);
        }
        return "Non existing contact";
    }

    void print(){
        Map<String, String> sorted = new Map<String, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(String key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<String> values() {
                return null;
            }

            @Override
            public Set<Entry<String, String>> entrySet() {
                return null;
            }
        };
        this.phonebook.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));

        sorted.forEach(((k,v) -> System.out.println(k + " " + v)));

    }



    private
    HashMap<String, String> phonebook;
    HashMap<String, Integer> outgoing;


}

public class Main {

    public static void main(String[] args) throws IOException {

        PhoneBook phoneBook = new PhoneBook();
        File file = new File("C:\\Users\\Ivan\\Desktop\\phonebook.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String p1 = "^(\\+359)((87)|(88)|(89))[2-9][0-9]{6}$";
        String p2 = "^(0)((87)|(88)|(89))[2-9][0-9]{6}$";
        String p3 = "^(00359)((87)|(88)|(89))[2-9][0-9]{6}$";

        String st;
        while ((st = br.readLine()) != null){
            String[] s = st.split("\\s+");
            if (s.length > 1){
                Pattern r = Pattern.compile(p1);
                Matcher m = r.matcher(s[1].trim());
                if (m.find()){
                    phoneBook.addNewContact(s[0], s[1]);
                    continue;
                }
                r = Pattern.compile(p2);
                m = r.matcher(s[1].trim());
                if (m.find()){
                    phoneBook.addNewContact(s[0], s[1]);
                    continue;
                }
                r = Pattern.compile(p3);
                m = r.matcher(s[1].trim());
                if (m.find()){
                    phoneBook.addNewContact(s[0], s[1]);
                }
            }
        }
    }
}

