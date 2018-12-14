/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursinisShowcase;

import java.util.Random;

/**
 *
 * @author audri
 */
class Passanger {

    private static int idGenerator = 1;
    private String name;
    private float weight;
    private int age;
    private int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Passanger(String name, float weight, int age) {
        id = idGenerator++;
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    @Override
    public String toString() {
        return name+id;
//        return "Passanger{" + "name=" + name + ", id=" + id +F '}';
    }

    public static Passanger GenerateRandom() {
        String generatorString = "asdjasdfjaeiofjaslkfasdfasdfjoiewtorytsudxnbxcvbxc";
        Random rd = new Random();
        String name = "";
        for (int i = 0; i < 6; ++i) {
            name += generatorString.charAt(rd.nextInt(generatorString.length()));
        }
        return new Passanger(name, rd.nextFloat() * 100 + 10, rd.nextInt(100));
    }
}
