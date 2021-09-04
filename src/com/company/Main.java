package com.company;


/**
 * Sharif Uni Employees Program ( Maktab59.Mr.Falahati )
 *
 * Group 3: Alireza HasanPour & Mahdi Leilaz Mehrabadi
 */

import java.util.Scanner;

public class Main {

    public static int counter;
    public static Scanner scan = new Scanner(System.in);
    public static ObjectArray employeesArray = new ObjectArray();
    public static Screen screen = new Screen();

    public static void main(String[] args) {
        counter = 0;
        boolean isTrue = true;
        int input = 0;

        screen.MessageShow(":: Sharif University Employees ::");

        while (isTrue) {
            scan.nextLine();
            screen.MessagesInsideMethodeShow("press enter to see program menu");
            scan.nextLine();
            screen.MessageShow("Menu");
            PrintList();
            input = scan.nextInt();
            switch (input) {
                case (1):
                    addEmployee();
                    break;
                case (2):
                    addSpouse();
                    break;
                case (3):
                    isMarried();
                    break;
                case (4):
                    isInvited();
                    break;
                case (5):
                    showEmployee();
                    break;
                case (6):
                    showEmployeesList();
                    break;
                case (7):
                    invitedList();
                    break;
                case (8):
                    isTrue = false;
            }

        }
        scan.close();
        return;

    }

    public static void PrintList() {
        String list = "1.add employee\n" + "2.employee[id] add spouse\n" + "3.employee[id] is married?\n"
                + "4.employee[id] is invited?\n" + "5.employee[id] print informations\n" + "6.list of employees\n"
                + "7.list of invited employees\n" + "8.exit\n";
        System.out.println(list);
    }

    public static void addEmployee() {
        String message;

        Employee employee = new Employee();
        employeesArray.AddNewElement(employee);
        double[] location = new double[2];
        boolean isContinue = true;
        int genderDetermination = 0;

        String header = ":: Add Employee ::";
        screen.MessageShow(header);

        do {
            System.out.println("Enter employee's ID :");
            try {
                int id = scan.nextInt();
                employee.setId(id);
                if (findIndex(id) == -1 || findIndex(id) >= 0) {
                    isContinue = false;
                    break;
                } else
                    System.out.println("This ID is entered before!\n");
            } catch (Exception e) {
                System.out.println("Your input is not valid!\n");
                scan.nextLine();
            }
        } while (isContinue);

        isContinue = true;

        System.out.println("Enter employee's first name :");
        employee.setFirstName(scan.next());
        System.out.println("Enter employee's last name :");
        employee.setLastName(scan.next());

        while (isContinue) {
            System.out.println("Enter employee's gender(1.male / 2.female) :");
            try {
                genderDetermination = scan.nextInt();
            } catch (Exception e) {
                scan.nextLine();
                //System.out.println("Your input is not valid!\n");
            }
            if (genderDetermination == 1 || genderDetermination == 2) {
                if (genderDetermination == 1)
                    employee.setGender(true);
                else
                    employee.setGender(false);
                isContinue = false;
            } else
                System.out.println("Your input is not valid!\n");
        }

        isContinue = true;

        boolean isLatitudeValid;

        while (isContinue) {
            System.out.println("Enter latitude of employee's location : ");
            try {
                location[0] = scan.nextDouble();
                isLatitudeValid = employee.setLocation(location[0], true);
                if (isLatitudeValid)
                    isContinue = false;
            } catch (Exception e) {
                System.out.println("Your input is not valid!\n");
                scan.nextLine();
            }
        }

        isContinue = true;

        boolean isLongitudeValid;

        while (isContinue) {
            System.out.println("Enter longtitude of employee's location : ");
            try {
                location[1] = scan.nextDouble();
                isLongitudeValid = employee.setLocation(location[1], false);
                if (isLongitudeValid)
                    isContinue = false;
            } catch (Exception e) {
                System.out.println("Your input is not valid!\n");
                scan.nextLine();
            }
        }
        message = String.format("\"%s %s\" is sucsessfully added to the employees list!\n", employee.getFirstName(),
                employee.getLastName());
        screen.MessagesInsideMethodeShow(message);
        counter++;
    }

    public static void addSpouse() {

        String header = ":: Add Spouse ::";
        screen.MessageShow(header);

        int[] employeeArray = getIdReturnIndex();
        int index = employeeArray[0];

        if (index == -1)
            return;
        if (employeesArray.getInputArray()[index].isMarried()) {
            System.out.printf("\"%s %s\" is already Married!\n", employeesArray.getInputArray()[index].getFirstName(),
                    employeesArray.getInputArray()[index].getLastName());
            return;
        }
        int employeeID = employeeArray[1];

        boolean isContinue = true;

        int[] spouseArray = new int[2];
        String message2;
        int spouseIndex = -1;
        int spouseID = -1;
        while (isContinue) {
            System.out.println();
            String message = "Enter employee's spouse information";
            System.out.println(message);
            System.out.print("   : ");

            spouseArray = getIdReturnIndex();
            spouseIndex = spouseArray[0];
            if (spouseIndex == -1)
                return;
            boolean isSpouseMarried = false;
            boolean condition = employeesArray.getInputArray()[spouseIndex].isMarried();
            if (condition) {
                System.out.printf("\"%s %s\" is already Married!\n",
                        employeesArray.getInputArray()[spouseIndex].getFirstName(),
                        employeesArray.getInputArray()[spouseIndex].getLastName());
                isSpouseMarried = true;
            }
            spouseID = spouseArray[1];
            if (spouseID == employeeID || isSpouseMarried) {
                System.out.print("Your input is not valid!\n");
            } else
                isContinue = false;
        }

        employeesArray.getInputArray()[index].setSpouse(spouseID, spouseIndex);
        employeesArray.getInputArray()[spouseIndex].setSpouse(employeeID, index);
        message2 = String.format("The relationship between\n\"%s %s\" and \"%s %s\"\nis setet!\n",
                employeesArray.getInputArray()[index].getFirstName(),
                employeesArray.getInputArray()[index].getLastName(),
                employeesArray.getInputArray()[spouseIndex].getFirstName(),
                employeesArray.getInputArray()[spouseIndex].getLastName());
        screen.MessagesInsideMethodeShow(message2);

    }

    public static void isMarried() {
        String message;

        String header = ":: Employee Maraige Status ::";
        screen.MessageShow(header);

        int index = getIdReturnIndex()[0];
        if (index == -1)
            return;
        if (employeesArray.getInputArray()[index].isMarried())
            message = String.format("\"%s %s\" is Marrid!\n", employeesArray.getInputArray()[index].getFirstName(),
                    employeesArray.getInputArray()[index].getLastName());
        else
            message = String.format("\"%s %s\" is not Marrid!\n", employeesArray.getInputArray()[index].getFirstName(),
                    employeesArray.getInputArray()[index].getLastName());
        screen.MessagesInsideMethodeShow(message);

    }

    public static void isInvited() {
        String message;

        String header = ":: Employee Invitation Status ::";
        screen.MessageShow(header);

        int index = getIdReturnIndex()[0];
        if (index == -1)
            return;
        employeesArray.getInputArray()[index].setIsInvited();
        if (employeesArray.getInputArray()[index].isInvited()) {
            message = String.format("\"%s %s\" is invited!\n", employeesArray.getInputArray()[index].getFirstName(),
                    employeesArray.getInputArray()[index].getLastName());
        } else
            message = String.format("\"%s %s\" is not invited!\n", employeesArray.getInputArray()[index].getFirstName(),
                    employeesArray.getInputArray()[index].getLastName());
        screen.MessagesInsideMethodeShow(message);
    }

    public static void invitedList() {
        int count = 1;

        String header = ":: Invited Employees List ::";
        screen.MessageShow(header);

        System.out.println();
        System.out.printf("%-2s  %-5s  %-8s  %-17s\n", "No.", "ID", "F.Name", "L.Name");
        for (int i = 0; i < 37; i++)
            System.out.print("-");
        System.out.println();
        for (int i = 0; i < counter; i++) {
            employeesArray.getInputArray()[i].setIsInvited();
            if (employeesArray.getInputArray()[i].isInvited()) {
                System.out.printf("%2s  %5s  %8s  %17s\n", count, employeesArray.getInputArray()[i].getId(),
                        employeesArray.getInputArray()[i].getFirstName(),
                        employeesArray.getInputArray()[i].getLastName());
                count++;
            }
        }

    }

    public static void showEmployeesList() {

        String header = ":: Employees List ::";
        screen.MessageShow(header);

        for (int i = 0; i < counter; i++) {
            System.out.println(employeesArray.getInputArray()[i]);
        }
        boolean lessThanTwo = false;
        if (counter == 1 || counter == 0)
            lessThanTwo = true;
        String message = "there " + (lessThanTwo ? "is " : "are ") + counter + (lessThanTwo ? " person " : " persons ")
                + "in the list!\n";
        screen.MessagesInsideMethodeShow(message);
    }

    public static void showEmployee() {

        String header = ":: Show Employee Information ::";
        screen.MessageShow(header);

        int index = getIdReturnIndex()[0];
        if (index == -1)
            return;
        System.out.println(employeesArray.getInputArray()[index]);
    }

    public static int[] getIdReturnIndex() {
        boolean isContinue = true;
        int index = -1;
        int employeeID = -1;
        int[] output = new int[2];
        while (isContinue) {
            employeeID = getEmployeeID();
            index = findIndex(employeeID);
            if (index != -1)
                isContinue = false;
            else {
                System.out.println("Do you want to try again?(1: yes) :");
                output[0] = -1;
                output[1] = -1;

                try {
                    if (scan.nextInt() != 1)
                        return output;
                } catch (Exception e) {
                    // scan.nextLine();
                    return output;
                }
            }
        }
        output[0] = index;
        output[1] = employeeID;
        return output;
    }

    public static int findIndex(int id) {
        int index = -1;
        int count = -1;
        int length = employeesArray.getLengthOfArray();
        for (int i = 0; i < length; i++) {
            if (employeesArray.getInputArray()[i].getId() == id) {
                index = i;
                count--;
            }
        }

        if (index == -1) {
            System.out.println("This ID is not in our database");
        }

        if (count < -2)
            return count;

        return index;
    }

    public static int getEmployeeID() {
        boolean isContinue = true;
        int employeeID = -1;
        do {
            System.out.println("Enter employee's ID : ");
            try {
                employeeID = scan.nextInt();
                isContinue = false;
                break;
            } catch (Exception e) {
                System.out.println("Your input is not valid!\n");
                scan.nextLine();
            }
        } while (isContinue);

        return employeeID;
    }

}
