import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {


        while(true) {


            System.out.println("\n===== ONLINE QUIZ APPLICATION =====");
            System.out.println("1. User Login");
            System.out.println("2. User Register");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");


            int choice = sc.nextInt();
            sc.nextLine();



            switch(choice) {


                case 1:
                    userLogin();
                    break;


                case 2:
                    registerUser();
                    break;


                case 3:
                    adminLogin();
                    break;


                case 4:
                    System.out.println("Thank You!");
                    System.exit(0);


                default:
                    System.out.println("Invalid Choice");

            }

        }

    }




    // USER LOGIN

    static void userLogin() {


        UserDAO userDAO = new UserDAO();


        System.out.print("Username: ");
        String username = sc.nextLine();


        System.out.print("Password: ");
        String password = sc.nextLine();



        if(userDAO.login(username,password)) {


            System.out.println("Login Successful");


            QuestionDAO questionDAO = new QuestionDAO();



            int score = questionDAO.startQuiz();



            int total = questionDAO.getAllQuestions().size();



            double percentage = (score * 100.0) / total;



            String status;



            if(percentage >= 50) {

                status = "PASS";

            }
            else {

                status = "FAIL";

            }




            System.out.println("\n===== RESULT =====");

            System.out.println("Score: " + score + "/" + total);

            System.out.println("Percentage: " + percentage + "%");

            System.out.println("Status: " + status);




            // Save old score table

            ScoreDAO scoreDAO = new ScoreDAO();

            scoreDAO.saveScore(username,score,total);




            // Save result table

            ResultDAO resultDAO = new ResultDAO();

            resultDAO.saveResult(username,score,percentage,status);



        }
        else {


            System.out.println("Invalid Username or Password");


        }


    }







    // USER REGISTER

    static void registerUser() {


        UserDAO userDAO = new UserDAO();


        System.out.print("Enter Username: ");

        String username = sc.nextLine();



        System.out.print("Enter Password: ");

        String password = sc.nextLine();



        userDAO.register(username,password);


    }








    // ADMIN LOGIN

    static void adminLogin() {


        AdminDAO adminDAO = new AdminDAO();



        System.out.print("Admin Username: ");

        String username = sc.nextLine();



        System.out.print("Admin Password: ");

        String password = sc.nextLine();




        if(adminDAO.login(username,password)) {


            System.out.println("Admin Login Successful");


            adminMenu();



        }
        else {


            System.out.println("Invalid Admin Login");


        }


    }







    // ADMIN MENU

    static void adminMenu() {


        AdminDAO adminDAO = new AdminDAO();



        while(true) {


            System.out.println("\n===== ADMIN PANEL =====");

            System.out.println("1. Add Question");

            System.out.println("2. View Questions");

            System.out.println("3. Update Question");

            System.out.println("4. Delete Question");

            System.out.println("5. View Scores");

            System.out.println("6. Logout");



            System.out.print("Enter Choice: ");


            int choice = sc.nextInt();

            sc.nextLine();




            switch(choice) {



                case 1:

                    adminDAO.addQuestion();

                    break;



                case 2:

                    adminDAO.viewQuestions();

                    break;



                case 3:

                    adminDAO.updateQuestion();

                    break;



                case 4:

                    adminDAO.deleteQuestion();

                    break;



                case 5:

                    adminDAO.viewScores();

                    break;



                case 6:

                    return;



                default:

                    System.out.println("Invalid Choice");


            }

        }


    }


}