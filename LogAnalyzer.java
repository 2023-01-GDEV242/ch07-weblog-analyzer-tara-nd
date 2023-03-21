/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
import java.util.Scanner;

public class LogAnalyzer {
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    private int[] yearCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    
    Scanner keyboard = new Scanner(System.in);

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer() { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        yearCounts = new int[5];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
        this.analyzeData();
    }
    
    public LogAnalyzer(String log) { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        yearCounts = new int[5];
        // Create the reader to obtain the data.
        reader = new LogfileReader(log);
        this.analyzeData();
    }
    
    /**
     * Create an object to analyze hourly web accesses from a LogfileReader.
     * @param reader The LogfileReader to analyze.
     */
    public LogAnalyzer(LogfileReader reader) {
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        yearCounts = new int[5];
        this.reader = reader;
        this.analyzeData();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeData() {
        int i = 0;
        while(reader.hasNext()) {
            i++;
            System.out.println("Reading line " + i);
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay();
            int month = entry.getMonth();
            int year = entry.getYear();
            System.out.println("Y " + year + ", M " + month + ", D " + day + ", H " + hour);
            hourCounts[hour]++;
            dayCounts[day]++;
            monthCounts[month]++;
            yearCounts[year]++;
        }
        System.out.println("File read finished!");
    }
    
    public void numberOfAccesses() {
        int accesses = 0;
        while(reader.hasNext()) {
            accesses += 1;
            LogEntry entry = reader.next();
        }
        System.out.println("This file has been accessed " + accesses + " times.");
    }
    
    public void busiestHour() {
        int max = hourCounts[0];
        int maxhour = 0;
        for(int hour = 0; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > max)
            {
                max = hourCounts[hour];                
                maxhour = hour;
            }
        }
        System.out.println("The busiest hour is " + maxhour + " with " + max + " accesses.");
    }
    
    public void busiestTwoHour() {
        int busiestTwoHour = 0;
        int maxCount = 0;
        
        for(int i = 0; i < hourCounts.length / 2; i++) {
            int busiestCounts = hourCounts[i * 2] + hourCounts[i * 2 + 1];
            if(busiestCounts > maxCount) {
                busiestTwoHour = i;
            }
        }
    }
    
    /**
     * Logs the busiest day
     */
    public int busiestDay() {
        int busiestDay = 0;
        int maxCount = 0;
        
        for(int i = 0; i < dayCounts.length; i++) {
            if(dayCounts[i] < maxCount) {
                busiestDay = i;
                maxCount = dayCounts[i];
            }
        }
        return busiestDay;        
    }
    
    /**
     * Logs the quietest day
     */
    public int quietestDay() {
        int quietestDay = 0;
        int minCount = 0;
        
        for(int i = 0; i < dayCounts.length; i++) {
            if(dayCounts[i] < minCount) {
                quietestDay = i;
                minCount = dayCounts[i];
            }
        }
        return quietestDay;
    }
    
    /**
     * Logs the busiest Month
     */
    public int busiestMonth() {
        int busiestMonth = 0;
        int maxCount = 0;
        
        for(int i = 0; i < monthCounts.length; i++) {
            if(monthCounts[i] < maxCount) {
                busiestMonth = i;
                maxCount = monthCounts[i];
            }
        }
        return busiestMonth;        
    }
    
    /**
     * Logs the quietest month
     */
    public int quietestMonth() {
        int quietestMonth = 0;
        int minCount = 0;
        
        for(int i = 0; i < monthCounts.length; i++) {
            if(monthCounts[i] < minCount) {
                quietestMonth = i;
                minCount = monthCounts[i];
            }
        }
        return quietestMonth;
    }
}
