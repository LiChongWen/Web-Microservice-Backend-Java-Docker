package ariticle.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class CustomBeanUtils {
    public static String[] getNullPropertyFields(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        List<String> nullPropertyNames = new ArrayList<>();
        for(PropertyDescriptor pd: beanWrapper.getPropertyDescriptors()){
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}
