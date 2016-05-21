package labs.zubovich.calculator.util;

public class TimeService {

    // T_n - нормативная трудоемкость
    // K_ti - K_тз,K_эп,K_тп,K_рп,K_вн - коэфиценты соответствующих стадий разработки
    // K_c - коэфицент повышения сложности
    // K_n - коэфициент новизны
    // K_t - коэфицент использования стандартных модулей
    // K_ur - коэфицент средств разработки по

    public double countT_phase(double T_n, double K_ti, double K_c, double K_n, double K_t, double K_ur){
        return T_n * K_ti * K_c * K_n * K_t * K_ur;
    }

}
