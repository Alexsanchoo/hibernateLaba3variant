package bsuir.laba5;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class CountryManager {
    private static SessionFactory sessionFactory = null;
    private static Scanner scanner = null;
    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        scanner = new Scanner(System.in);


        int choice = 0;
        while(choice != 5) {
            System.out.println();
            System.out.println("Меню");
            System.out.println("1. Добавить запись");
            System.out.println("2. Просмотреть записи");
            System.out.println("3. Редактировать запись");
            System.out.println("4. Удалить запись");
            System.out.println("5. Выход");
            System.out.print("Сделайте выбор: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCountry();
                    break;

                case 2:
                    if(getSize() != 0) {
                        showCountries();
                    }
                    else
                    {
                        System.out.println("\nНет записей в БД!");
                    }
                    break;

                case 3:
                    if(getSize() != 0) {
                        editCountry();
                    }
                    else
                    {
                        System.out.println("\nНет записей в БД!");
                    }
                    break;

                case 4:
                    if(getSize() != 0) {
                        deleteCountry();
                    }
                    else
                    {
                        System.out.println("\nНет записей в БД!");
                    }
                    break;

                case 5:
                    break;

                default:
                    System.out.println("\nНеверный выбор!");
            }
        }
    }

    public static void addCountry() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        System.out.println();
        transaction = session.beginTransaction();
        Country country = new Country();
        scanner.nextLine();
        System.out.print("Введите название страны: "); country.setName(scanner.nextLine());
        System.out.print("Введите столицу: "); country.setCapital(scanner.nextLine());
        System.out.print("Введите население: "); country.setPopulation(scanner.nextInt());
        System.out.print("Введите площадь страны: "); country.setArea(scanner.nextDouble());
        scanner.nextLine();
        System.out.print("Введите форму правления: "); country.setGovermentForm(scanner.nextLine());
        session.save(country);
        transaction.commit();
        session.close();
    }

    public static int getSize() {
        List<Country> countries = listCountries();
        return countries.size();
    }

    public static void showCountries() {
        System.out.println();
        List<Country> countries = listCountries();
        for (Country country : countries) {
            System.out.println(country);
        }
    }

    public static  List<Country> listCountries() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List <Country> countries = session.createQuery("FROM Country").list();

        transaction.commit();
        session.close();
        return countries;
    }


    public static void editCountry() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        showCountries();
        System.out.println();
        int id;
        System.out.print("Выберите id записи, которую хотите отредактировать: ");
        id = scanner.nextInt();

        Country country = (Country) session.get(Country.class, id);

        int choice = 0;
        while(choice != 6) {
            System.out.println();
            System.out.println("Поля: ");
            System.out.println("1. Название");
            System.out.println("2. Столица");
            System.out.println("3. Население");
            System.out.println("4. Площадь");
            System.out.println("5. Форма правления");
            System.out.println("6. Назад");
            System.out.print("Сделайте выбор: "); choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Название: " + country.getName());
                    scanner.nextLine();
                    System.out.print("Введите новое название: ");
                    country.setName(scanner.nextLine());
                    break;

                case 2:
                    System.out.println("Столица: " + country.getCapital());
                    scanner.nextLine();
                    System.out.print("Введите новую столицу: ");
                    country.setCapital(scanner.nextLine());
                    break;

                case 3:
                    System.out.println("Население: " + country.getPopulation());
                    System.out.print("Введите новое население: ");
                    country.setPopulation(scanner.nextInt());
                    break;

                case 4:
                    System.out.println("Площадь: " + country.getArea());
                    System.out.print("Введите новую площадь: ");
                    country.setArea(scanner.nextDouble());
                    break;

                case 5:
                    System.out.println("Форма правления: " + country.getGovermentForm());
                    scanner.nextLine();
                    System.out.print("Введите новую форму правления: ");
                    country.setGovermentForm(scanner.nextLine());
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Неверный выбор!");
            }
        }

        session.update(country);
        transaction.commit();
        session.close();
    }


    public static void deleteCountry() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        showCountries();
        System.out.println();
        int id;
        System.out.print("Выберите id записи, которую хотите удалить: ");
        id = scanner.nextInt();

        Country country = (Country) session.get(Country.class, id);

        session.delete(country);
        transaction.commit();
        session.close();
    }
}
