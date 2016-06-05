package labs.zubovich.calculator;

import labs.zubovich.calculator.util.*;
import labs.zubovich.dbutil.GlobalCache;

import java.util.HashMap;
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

    public Map<String, Double> getStageCoefficients(String noveltyCoefficient){
        int index = 0;
        switch (noveltyCoefficient){
            case "ABSOLUTELY_NEW":
                index = 0;
                break;
            case "MIDDLE_NEW":
                index = 1;
                break;
            case "LOW_NEW":
                index = 2;
                break;
        }
        Map<String, Double> result = new HashMap<>();
        double[] K_tz = {0.12, 0.10, 0.08};
        double[] K_ep = {0.21, 0.20, 0.19};
        double[] K_tp = {0.32, 0.30, 0.28};
        double[] K_rp = {0.26, 0.30, 0.34};
        double[] K_vn = {0.09, 0.10, 0.11};
        result.put("K_tz", K_tz[index]);
        result.put("K_ep", K_ep[index]);
        result.put("K_tp", K_tp[index]);
        result.put("K_rp", K_rp[index]);
        result.put("K_vn", K_vn[index]);
        return result;
    }

	@Override
	public Object calculate(Map<RowParam, Object> params) {

		DificultyCategoryEnum dificultyCategoryEnum = (DificultyCategoryEnum) params.get(RowParam.DIFICULTY);
		Integer loc = (Integer) params.get(RowParam.LOC);
		if(loc == null || loc == 0 || dificultyCategoryEnum == null ) {
			return "Введены не все данные";
		}

		Integer T_n = getT_n(loc, dificultyCategoryEnum); // нормативная трудоемкость

		Double K_c = 1.0 + DifficultyUpperCoefficientEnum.getValue(
				(List<DifficultyUpperCoefficientEnum>) params.get(RowParam.DIFFICULTY_UPPER_COEF)
		);

		//TODO: need to be verified and updated
		Double K_ur = ((DevelopmentTools)params.get(RowParam.PROGRAM_LANGUAGE)).getIBM_PC_OR_WIN(); // коэфицент средств разработки по

		Double K_t = ((StandartModuleUsageEnum)params.get(RowParam.STANDART_USAGES_K)).getK();

        //TODO: getCofficient params should be replaced with real values
        //TODO: need to be updated : Calc of this value are more difficult then it expected. need to be reviewed is all values exists on UI
		Double K_n = ((Novelty)params.get(RowParam.NOVELTY_COEFFICIENT)).getCofficient(false, false);

        Map<String, Double> stageCoefficients = getStageCoefficients(((Novelty)params.get(RowParam.NOVELTY_COEFFICIENT)).name());
		double K_tz = stageCoefficients.get("K_tz");
		double K_ep = stageCoefficients.get("K_ep");
		double K_tp = stageCoefficients.get("K_tp");
		double K_rp = stageCoefficients.get("K_rp");
		double K_vn = stageCoefficients.get("K_vn");

        double K_t_default = 1.0;

		Integer teamNumber = (Integer) params.get(RowParam.TEAM_SIZE);
		Double  averageSalary = (Double) params.get(RowParam.AVG_SALARY);

		ManHoursCounter manHoursCounter = new ManHoursCounter();
		TimeService timeService = new TimeService();

		double T_utz = timeService.countT_phase(T_n, K_tz, K_c, K_n, K_t_default, K_ur);
		double T_uep = timeService.countT_phase(T_n, K_ep, K_c, K_n, K_t_default, K_ur);
		double T_utp = timeService.countT_phase(T_n, K_tp, K_c, K_n, K_t_default, K_ur);
		double T_urp = timeService.countT_phase(T_n, K_rp, K_c, K_n, K_t, K_ur);
		double T_uvn = timeService.countT_phase(T_n, K_vn, K_c, K_n, K_t_default, K_ur);

		TimeEntity timeEntity = new TimeEntity(T_utz, T_uep, T_utp, T_urp, T_uvn);

		double result = manHoursCounter.countForSinglePerson(timeEntity);

        //TODO: team size & salary usage
        result = result/teamNumber * averageSalary;

		return result;
	}
}
