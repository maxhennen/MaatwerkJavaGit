package sample;



import java.util.ArrayList;

/**
 * Created by maxhe on 11-4-2017.
 */
public class Calender {

    private int Year;
    private int Month;
    private int Day;

    public void setYear(int year){Year = year;}
    public void setMonth(int month){Month = month;}
    public void setDay(int day){Day = day;}

    public Integer getYear(){return Year;}
    public Integer getMonth(){return Month;}
    public Integer getDay(){return Day;}

    private ArrayList<Integer> CountDaysOfTheMonth = new ArrayList<Integer>();

    public Enum CalculateDay(int month, int day, int year){
        int y = year - (14-month)/12;
        int x = y +y/4 - y/100 + y/400;
        int m = month + 12 *((14-month)/12)-2;
        int d = (day + x + (31*m)/12)%7;

        return Days.values()[d];
    }


    public boolean IsLeapYear(int year){
        if((year%4 ==0)&&(year % 100 != 0)&&(year%400 == 0)){
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Integer> GetCountDaysOfMonth(boolean leapYear){
        CountDaysOfTheMonth.clear();
        if(!leapYear){
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(28);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(30);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(30);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(30);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(30);
            CountDaysOfTheMonth.add(31);
        }
        else {
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(29);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(30);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(30);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(30);
            CountDaysOfTheMonth.add(31);
            CountDaysOfTheMonth.add(30);
            CountDaysOfTheMonth.add(31);
        }
        return CountDaysOfTheMonth;
    }


}
