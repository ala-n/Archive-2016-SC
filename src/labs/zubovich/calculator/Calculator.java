package labs.zubovich.calculator;

import java.util.Map;

/**
 * Created by Alexey on 25.04.2016.
 */
public interface Calculator {
	Object calculate(Map<RowParam, Object> params);
}
