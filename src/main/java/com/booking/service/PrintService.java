package com.booking.service;

import java.util.List;

import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.models.Person;
import com.booking.models.Employee;
import com.booking.models.Customer;

public class PrintService {
    public static void printMenu(String title, String[] menuArr) {
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);
            num++;
        }
    }

    public static String printServices(List<Service> serviceList) {
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s |\n",
                "No.", "ID", "Nama", "Harga");
        System.out
                .println("+========================================================================================+");
        for (Service service : serviceList) {
            System.out.printf("| %-4s | %-4s | %-11s | %-15s |\n",
                    num, service.getServiceId(), service.getServiceName(),
                    service.getPrice());
            num++;
        }
        return "";
    }

    public static String listServices(List<Service> serviceList) {
        String result = "";
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    public static void showRecentReservation(List<Reservation> reservationList) {
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out
                .println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting")
                    || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(),
                        listServices(reservation.getServices()), reservation.getReservationPrice(),
                        reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public static void showAllCustomer(List<Person> personList) {
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out
                .println("+========================================================================================+");
        for (Person person : personList) {
            if (person.getId().contains("Cust")) {
                Customer customer = (Customer) person;
                // Uang nya belum
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                        num, customer.getId(), customer.getName(),
                        customer.getAddress(), customer.getMember().getMembershipName(),
                        customer.getId());
                num++;
            }
        }
    }

    public static void showAllEmployee(List<Person> personList) {
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman");
        System.out
                .println("+========================================================================================+");
        for (Person person : personList) {
            if (person.getId().contains("Emp")) {
                Employee employee = (Employee) person;
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s |\n",
                        num, employee.getId(), employee.getName(),
                        employee.getAddress(), employee.getExperience());
                num++;
            }
        }
    }

    public static void showHistoryReservation(List<Reservation> reservationList) {
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Workstage");
        System.out
                .println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Finish")
                    || reservation.getWorkstage().equalsIgnoreCase("Cancel")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(),
                        printServices(reservation.getServices()), reservation.getReservationPrice(),
                        reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }
}
