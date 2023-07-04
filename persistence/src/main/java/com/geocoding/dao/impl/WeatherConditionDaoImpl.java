package com.geocoding.dao.impl;

import com.geocoding.dao.WeatherConditionDao;
import com.geocoding.model.Stay;
import com.geocoding.model.WeatherCondition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class WeatherConditionDaoImpl implements WeatherConditionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WeatherCondition createWeather(int[] relativeHumidity, Double[] responseMinTemperature, Double[] responseMaxTemperature, int[] responseWeathercode, int stayId) {
        Stay stayPersistence = entityManager.find(Stay.class, stayId);
        if(stayPersistence != null) {
            // Calcola la media dell'umidità
            int humidity = 0;
            for(int i = 0; i < relativeHumidity.length; i++) {
                humidity += relativeHumidity[i];
            }
            humidity /= 24;

            WeatherCondition weatherCondition = new WeatherCondition();
            weatherCondition.setHumidity(humidity);
            weatherCondition.setMinTemperature(responseMinTemperature[0]);
            weatherCondition.setMaxTemperature(responseMaxTemperature[0]);
            weatherCondition.setSkyConditionByCode(responseWeathercode[0]);
            weatherCondition.setStay(stayPersistence);
            weatherCondition.setStayDate(stayPersistence.getStayDate());

            entityManager.persist(weatherCondition);
            stayPersistence.setWeatherCondition(weatherCondition);

            return weatherCondition;
        }
        return null;
    }
}
