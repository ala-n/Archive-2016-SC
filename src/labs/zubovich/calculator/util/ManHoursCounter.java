package labs.zubovich.calculator.util;

public class ManHoursCounter {

    public double countForSinglePerson(TimeEntity time){
        return time.getT_uep() + time.getT_urp() + time.getT_utp() + time.getT_utz() + time.getT_uvn();
    }

    public double countForTeam(TimeEntity time, int employeeNumber){
        return countForSinglePerson(time) / employeeNumber;
    }

}
