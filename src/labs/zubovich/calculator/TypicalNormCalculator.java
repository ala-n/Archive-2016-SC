package labs.zubovich.calculator;

import labs.zubovich.calculator.util.DificultyCategoryEnum;
import labs.zubovich.calculator.util.ManHoursCounter;
import labs.zubovich.calculator.util.TimeEntity;
import labs.zubovich.calculator.util.TimeService;
import labs.zubovich.dbutil.GlobalCache;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 25.04.2016.
 */
public class TypicalNormCalculator implements Calculator {

	private Integer getT_n(Integer loc, DificultyCategoryEnum dificultyCategoryEnum) {
		if(loc <= 50000){
			Map<Integer, List<Integer>> LOCMap = (Map<Integer, List<Integer>>) GlobalCache.get(GlobalCache.Key.KLOC_Map);

			Integer targKey = LOCMap.keySet()
					.stream()
					.sorted()
					.filter(x -> (x >= loc))
					.findFirst().orElse(null);

			return LOCMap.get(targKey).get(dificultyCategoryEnum.getValue());
		}
		switch (dificultyCategoryEnum) {
			case FIRST:
				return (int)(0.12 * Math.pow(loc, 0.92));
			case SECOND:
				return (int)(0.105 * Math.pow(loc, 0.915));
			case THIRD:
				return (int)(0.092 * Math.pow(loc, 0.91));
			default:
				return 1;
		}
	}

	@Override
	public Object calculate(Map<RowParam, Object> params) {

		DificultyCategoryEnum dificultyCategoryEnum = (DificultyCategoryEnum) params.get(RowParam.DIFICULTY);
		Integer loc = (Integer) params.get(RowParam.LOC);
		if(loc == 0 || dificultyCategoryEnum == null ) {
			return "Введены не все данные";
		}

		double T_n = getT_n(loc, dificultyCategoryEnum); // нормативная трудоемкость

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////

		double teamNumber = 4.0;
		double averageSalary = 5.0; // $$$ много бачей $$$


		double K_c = 1.11; // коэфицент повышения сложности
		double K_n = 0.63; // коэфициент новизны
		double K_ur = 1; // коэфицент средств разработки по
		double K_t = 0.9; // коэфицент использования стандартных модулей
		double K_t_default = 1.0; // нет K_t

		// K_ti - K_тз,K_эп,K_тп,K_рп,K_вн - коэфиценты соответствующих стадий разработки
		double K_tz = 0.08;
		double K_ep = 0.19;
		double K_tp = 0.28;
		double K_rp = 0.34;
		double K_vn = 0.11;

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////


		ManHoursCounter manHoursCounter = new ManHoursCounter();
		TimeService timeService = new TimeService();

		double T_utz = timeService.countT_phase(T_n, K_tz, K_c, K_n, K_t_default, K_ur);
		double T_uep = timeService.countT_phase(T_n, K_ep, K_c, K_n, K_t_default, K_ur);
		double T_utp = timeService.countT_phase(T_n, K_tp, K_c, K_n, K_t_default, K_ur);
		double T_urp = timeService.countT_phase(T_n, K_rp, K_c, K_n, K_t, K_ur);
		double T_uvn = timeService.countT_phase(T_n, K_vn, K_c, K_n, K_t_default, K_ur);

		TimeEntity timeEntity = new TimeEntity(T_utz, T_uep, T_utp, T_urp, T_uvn);

		double result = manHoursCounter.countForSinglePerson(timeEntity);

		System.out.println(result);
		return params.toString();
	}
}
