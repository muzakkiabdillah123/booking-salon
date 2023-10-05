package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.models.Employee;

public class ReservationService {
    public static void createReservation(Scanner input, List<Person> personList, List<Service> serviceList) {
        List<Service> serviceBooked = new ArrayList<>();

        // panggil fitur menambahkan reservation
        PrintService.showAllCustomer(personList);
        System.out.println("Silahkan Masukkan Customer Id:");
        String customerId = input.nextLine();
        Customer customer = ReservationService.getCustomerByCustomerId(customerId,
                personList);

        PrintService.showAllEmployee(personList);
        System.out.println("Silahkan Masukkan Employee Id:");
        String employeeId = input.nextLine();
        // get employee by id
        Employee employee = ReservationService.getEmployeeByEmployeeId(employeeId, personList);

        PrintService.printServices(serviceList);
        System.out.println("Silahkan Masukkan Service Id:");
        // Store it in an array or list
        String[] serviceId = new String[100];
        serviceId[0] = input.nextLine();
        Service service = ReservationService.getServiceByServiceId(serviceId[0], serviceList);
        serviceBooked.add(service);

        String anotherService = "";
        int i = 1;

        do {
            System.out.println("Ingin pilih service lain (Y/T)?");
            anotherService = input.nextLine();
            if (anotherService.equalsIgnoreCase("Y")) {
                System.out.println("Silahkan Masukkan Service Id:");
                serviceId[i] = input.nextLine();
                service = ReservationService.getServiceByServiceId(serviceId[i], serviceList);
                serviceBooked.add(service);
                i++;
            } else {
                break;
            }
        } while (anotherService.equalsIgnoreCase("Y"));

        System.out.println("Booking Berhasil!");
        Reservation reservation = new Reservation(String.valueOf(generateReservationId()),
                customer, employee,
                serviceBooked, "In Process");
        MenuService.reservationList.add(reservation);
        System.out.printf("Total Biaya Booking: Rp.%.0f%n", reservation.getReservationPrice());
    }

    public static Customer getCustomerByCustomerId(String customerId, List<Person> personList) {
        Customer customer = new Customer();
        for (int i = 0; i < MenuService.personList.size(); i++) {
            if (MenuService.personList.get(i) instanceof Customer) {
                if (MenuService.personList.get(i).getId().equalsIgnoreCase(customerId)) {
                    customer = (Customer) MenuService.personList.get(i);
                }
            }
        }
        return customer;
    }

    public static Employee getEmployeeByEmployeeId(String employeeId, List<Person> personList) {
        Employee employee = new Employee();
        for (int i = 0; i < MenuService.personList.size(); i++) {
            if (MenuService.personList.get(i) instanceof Employee) {
                if (MenuService.personList.get(i).getId().equalsIgnoreCase(employeeId)) {
                    employee = (Employee) MenuService.personList.get(i);
                }
            }
        }
        return employee;
    }

    public static Service getServiceByServiceId(String serviceId, List<Service> serviceList) {
        Service service = new Service();
        for (int i = 0; i < MenuService.serviceList.size(); i++) {
            if (MenuService.serviceList.get(i).getServiceId().equalsIgnoreCase(serviceId)) {
                service = MenuService.serviceList.get(i);
            }
        }
        return service;
    }

    public static void editReservationWorkstage(Scanner input) {

    }

    private static int generateReservationId() {
        int reservationId = 0;
        reservationId++;
        return reservationId;
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
