package labs.zubovich.calculator.util;

public class TimeEntity {

    private double T_utz; // трудоемкость на стадии разработки тз
    private double T_uep; // трудоемкость на стадии анализа требований
    private double T_utp; // трудоемкость на стадиипроектирования
    private double T_urp; // трудоемкость на стадии разработки (кодирования)
    private double T_uvn; // трудоемкость на стадии ввод в действие

    TimeEntity(){
        this.T_utz = 0;
        this.T_uep = 0;
        this.T_utp = 0;
        this.T_urp = 0;
        this.T_uvn = 0;
    }

    public TimeEntity(double t_utz, double t_uep, double t_utp, double t_urp, double t_uvn) {
        T_utz = t_utz;
        T_uep = t_uep;
        T_utp = t_utp;
        T_urp = t_urp;
        T_uvn = t_uvn;
    }

    public double getT_utz() {
        return T_utz;
    }

    public void setT_utz(double t_utz) {
        T_utz = t_utz;
    }

    public double getT_uep() {
        return T_uep;
    }

    public void setT_uep(double t_uep) {
        T_uep = t_uep;
    }

    public double getT_utp() {
        return T_utp;
    }

    public void setT_utp(double t_utp) {
        T_utp = t_utp;
    }

    public double getT_urp() {
        return T_urp;
    }

    public void setT_urp(double t_urp) {
        T_urp = t_urp;
    }

    public double getT_uvn() {
        return T_uvn;
    }

    public void setT_uvn(double t_uvn) {
        T_uvn = t_uvn;
    }
}
