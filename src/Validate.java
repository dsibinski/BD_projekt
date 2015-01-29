public class Validate {
 
        //date[0] = rok, date[1] = miesiac...
        public static Boolean peselDate(String PESEL, int year, int month, int day){
                Boolean pes = true;
                int year2 = year;
                if(year2 >= 100)
                        year2 -= 100;
                System.out.println(PESEL.substring(0, 2));
                System.out.println(year2);
                System.out.println(PESEL.substring(2, 4));
                System.out.println(month + 1);
                System.out.println(PESEL.substring(4, 6));
                System.out.println(day);
                if (Integer.parseInt(PESEL.substring(0, 2)) != (year2)) {
                        pes = false;
                }
                if (Integer.parseInt(PESEL.substring(2, 4)) != (month + 1)) {
                        pes = false;
                }
                if (Integer.parseInt(PESEL.substring(4, 6)) != day) {
                        pes = false;
                }
                return pes;
        }
       
}