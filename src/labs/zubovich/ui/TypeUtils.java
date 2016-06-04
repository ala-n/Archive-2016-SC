package labs.zubovich.ui;

import java.util.Collections;
import java.util.List;

/**
 * Created by Alexey on 04.06.2016.
 */
public abstract class TypeUtils {
	public static Object getDefaultByClass(Class<?> clazz) {
		if(clazz.isArray()) {
			clazz = clazz.getComponentType();
		}
		if(clazz.isEnum() && clazz.getEnumConstants().length > 0) {
			return clazz.getEnumConstants()[0];
		}
		if(clazz.isInstance(List.class)) {
			try {
				return clazz.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		if(clazz.equals(List.class)) {
			return Collections.emptyList();
		}
		if(clazz.equals(Integer.class) || clazz.equals(Short.class)) {
			return 0;
		}
		if(clazz.equals(Long.class)) {
			return 0l;
		}
		if(clazz.equals(Double.class)) {
			return 0.;
		}
		if(clazz.equals(Float.class)) {
			return 0f;
		}
		if(clazz.equals(String.class)) {
			return "";
		}
		return null;
	}
}
