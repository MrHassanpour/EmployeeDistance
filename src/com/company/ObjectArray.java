package com.company;

/**
 * Sharif Uni Employees Program ( Maktab59.Mr.Falahati )
 *
 * Group 3: Alireza HasanPour & Mahdi Leilaz Mehrabadi
 */

public class ObjectArray {

    private Employee[] inputArray;
    private int lengthOfArray = 0;

    public ObjectArray() {
    }

    public Employee[] getInputArray() {
        return this.inputArray;
    }

    public int getLengthOfArray() {
        return this.lengthOfArray;
    }

    public void setInputArray(Employee[] inputArray) {
        this.inputArray = inputArray;
        this.lengthOfArray = inputArray.length;
    }

    public void setLengthOfArray(int lengthOfArray) {
        if (this.lengthOfArray == 0) {
            this.lengthOfArray = lengthOfArray;
            this.inputArray = new Employee[lengthOfArray];
        } else
            System.out.println("The length of Array is already determined!");
    }

    public void printing() {
        System.out.printf("InputArray = {");
        for (int i = 0; i < lengthOfArray - 1; i++) {
            System.out.printf("%d, ", inputArray[i]);
        }
        System.out.printf("%d}\n", inputArray[lengthOfArray - 1]);

        System.out.printf("Thr length of the Array is %d\n", lengthOfArray);
    }

    public void AddElementWithIndex(Employee input, int index) {
        this.lengthOfArray++;
        int tempCounter = 0;
        Employee[] inputArray = new Employee[this.lengthOfArray];
        for (int i = 0; i < lengthOfArray - 1; i++) {
            inputArray[tempCounter] = this.inputArray[i];
            tempCounter++;
            if (tempCounter == index) {
                inputArray[tempCounter] = input;
                tempCounter++;
            }
        }
        if (index == lengthOfArray - 1)
            inputArray[lengthOfArray - 1] = input;
        this.inputArray = inputArray;
    }

    public void AddNewElement(Employee input) {
        AddElementWithIndex(input, lengthOfArray);
    }

    public void DeleteElement(int index) {
        this.lengthOfArray--;
        Employee[] inputArray = new Employee[this.lengthOfArray];
        int tempCounter = 0;
        for (int i = 0; i < lengthOfArray; i++) {
            inputArray[i] = this.inputArray[tempCounter];
            tempCounter++;
            if (tempCounter == index)
                tempCounter++;
        }
        this.inputArray = inputArray;
    }

    public void DeletLastElement() {
        DeleteElement(lengthOfArray - 1);
    }

    public Employee ShowElementofIndex(int index) {
        return inputArray[index];
    }

    public boolean Contains(Employee input) {
        boolean isItContained = false;
        for (int i = 0; i < lengthOfArray; i++) {
            if (inputArray[i].getId() == input.getId())
                isItContained = true;
        }
        return isItContained;
    }

}
